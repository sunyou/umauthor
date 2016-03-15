package com.ai.umauthor.web.controller.mgr;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.common.domain.AjaxPageBean;
import com.ai.common.dynasql.SqlCondition;
import com.ai.common.exception.BusinessException;
import com.ai.common.util.WebUtils;
import com.ai.umauthor.server.model.TableCols;
import com.ai.umauthor.server.model.UmDictitem;
import com.ai.umauthor.server.model.UmMenu;
import com.ai.umauthor.server.model.UmOperator;
import com.ai.umauthor.server.model.UmOrg;
import com.ai.umauthor.server.model.UmStation;
import com.ai.umauthor.server.model.UmStationtype;
import com.ai.umauthor.server.service.mrg.interfaces.DictitemSV;
import com.ai.umauthor.server.service.mrg.interfaces.Operator2StationSV;
import com.ai.umauthor.server.service.mrg.interfaces.OperatorSV;
import com.ai.umauthor.server.service.mrg.interfaces.OrgSV;
import com.ai.umauthor.server.service.mrg.interfaces.StationSV;
import com.ai.umauthor.server.service.mrg.model.QryUmOperator;
import com.ai.umauthor.server.service.mrg.model.QryUmOperator2Station;
import com.ai.umauthor.server.service.mrg.model.SessionUser;

@Controller
@RequestMapping("/operator")
public class OperatorController {
	
	private static final Logger log = LoggerFactory.getLogger(OperatorController.class);
	
	@Autowired
	private OperatorSV operatorSV;

	@Autowired
	private OrgSV orgSV;
	
    @Autowired
    private StationSV stationSV;
    @Autowired
    private DictitemSV dictitemSV;
    
	@Autowired
	private Operator2StationSV operator2StationSV;
	
	@RequestMapping(value="/index")
	public String index(HttpServletRequest request, Model model) throws Exception{
		SessionUser<UmOperator,UmStationtype,UmMenu,Object>  sessionUserInfo= WebUtils.getSessionUserInfo();
		UmOperator user = sessionUserInfo.getLoginUser();
		model.addAttribute("orgId", user.getOrgId());
		UmOrg umOrg = orgSV.queryOrgById(user.getOrgId());
		model.addAttribute("orgName", umOrg.getOrgName());
    	return "operator/index";
    }
	
	@RequestMapping("/operateList")
	@ResponseBody
	public AjaxPageBean operateList(HttpServletRequest request,String orgId,
								@RequestParam(value="iDisplayStart", defaultValue="0") int pageNo, 
								@RequestParam(value="iDisplayLength", defaultValue="10")int pageSize) throws Exception{
		AjaxPageBean result = new AjaxPageBean();
		
		int sEcho = Integer.valueOf(request.getParameter("sEcho"));
		result.setOther(sEcho);
		int page = (pageNo / pageSize)+1;
		
		TableCols.UmOperatorCols t = TableCols.UmOperatorCols;
		SqlCondition cond = new SqlCondition();
		
		
		String operatorNameS = request.getParameter("operatorNameS");
		if(!StringUtils.isEmpty(operatorNameS)){
			cond.like(t.operatorName, operatorNameS);
		}
		
		String operatorCodeS = request.getParameter("operatorCodeS");
		if(!StringUtils.isEmpty(operatorCodeS)){
			cond.like(t.operatorCode, operatorCodeS);
		}
		
		String telNoS = request.getParameter("telNoS");
		if(!StringUtils.isEmpty(telNoS)){
			cond.like(t.telNo, telNoS);
		}
		
//		cond.eq(t.orgId, orgId).page(pageNo, pageSize); //sEcho
		try {
			int total = operatorSV.selectOperatorCountByOrgAndSqlCond(orgId,cond);

			cond.page(page, pageSize);
			List<QryUmOperator> operatorList = operatorSV.selectOperatorByOrgAndSqlCond(orgId,cond);
			result.setCurrentPage(page);
			result.setTotal(total);
			result.setData(operatorList);
		} catch (Exception e) {
			log.debug("operateList:"+e.getMessage());
			result.setCode(AjaxPageBean.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value="/editOperatorForm")
	public String editOperatorForm(Model model, HttpServletRequest request,String operatorCode,String orgId,String type) throws Exception{
		//用户所在组织
		SessionUser<UmOperator,UmStationtype,UmMenu,Object>  sessionUserInfo= WebUtils.getSessionUserInfo();
		UmOperator user = sessionUserInfo.getLoginUser();
		model.addAttribute("treeOrgId", user.getOrgId());
		long domainId = 1l;
		model.addAttribute("type", type);
		//账号类型
		List<UmDictitem> operatorTypes = dictitemSV.queryUmDictitemByDictKey("OPERATOR_TYPE");
		model.addAttribute("operatorTypes", operatorTypes);
		//账号级别
		List<UmDictitem> operatorLevels = dictitemSV.queryUmDictitemByDictKey("OPERATOR_LEVEL");
		model.addAttribute("operatorLevels", operatorLevels);
		if("add".equals(type)){
			//新增
			UmOperator umOperator = new UmOperator();
			umOperator.setDomainId(domainId);
			model.addAttribute("umOperator", umOperator);
			
		}else{
			//修改
			try {
				UmOperator umOperator = operatorSV.selectOperatorByCode(operatorCode, String.valueOf(domainId), orgId);
				model.addAttribute("umOperator", umOperator);
			} catch (Exception e) {
				log.error(e.getMessage());
	//			result.setCode(AjaxPageBean.ERROR_CODE);
	//			result.setMessage(e.getMessage());
			}
		}
		
		return "operator/editOperatorForm";
	}
	
	@RequestMapping(value="/operatorInfo")
	public String operatorInfo(Model model, HttpServletRequest request,String operatorCode,String orgId,String type) throws Exception{
		
		long domainId = 1l;
		try {
			UmOperator umOperator = operatorSV.selectOperatorByCode(operatorCode, String.valueOf(domainId), orgId);
			model.addAttribute("umOperator", umOperator);
			model.addAttribute("type", "update");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return "operator/operatorInfo";
	}
	
	
	
	@RequestMapping(value="/saveOperator")
	@ResponseBody
	public AjaxPageBean saveOperator(HttpServletRequest request,UmOperator umOperator) throws Exception{
		AjaxPageBean result = new AjaxPageBean();
		try {
			umOperator.setAddFrom("1");//系统创建
			operatorSV.insertOperator(umOperator);
		} catch (Exception e) {
			log.error("saveOperator:"+e.getMessage());
			result.setCode(AjaxPageBean.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value="/updateOperator")
	@ResponseBody
	public AjaxPageBean updateOperator(HttpServletRequest request,UmOperator umOperator) throws Exception{
		AjaxPageBean result = new AjaxPageBean();
		try {
			operatorSV.modifyOperator(umOperator);
		} catch (Exception e) {
			log.error("updateOperator:"+e.getMessage());
			result.setCode(AjaxPageBean.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value="/deleteOperator")
	@ResponseBody
	public AjaxPageBean deleteOperator(HttpServletRequest request,Long operatorId,String operatorIds) throws Exception{
		AjaxPageBean result = new AjaxPageBean();
		log.debug("------"+operatorId+"------");
		
		
		
		try {
			if(StringUtils.isEmpty(operatorIds)){
				//删除单条
				operatorSV.deleteOperator(operatorId);
			}else{
				//删除多条
				String[] ids = operatorIds.split(",");
				for (String id : ids) {
					operatorSV.deleteOperator(Long.valueOf(id));
				}
			}
			
		} catch (Exception e) {
			result.setCode(AjaxPageBean.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value="/lockOperator")
	@ResponseBody
	public AjaxPageBean lockOperator(HttpServletRequest request,Long operatorId,String operatorIds) throws Exception{
		AjaxPageBean result = new AjaxPageBean();
		log.debug("------"+operatorId+"------");
		try {
			if(StringUtils.isEmpty(operatorIds)){
				//锁定单条
				operatorSV.lockOperator(operatorId);
			}else{
				//锁定多条
				String[] ids = operatorIds.split(",");
				for (String id : ids) {
					operatorSV.lockOperator(Long.valueOf(id));
				}
			}
		} catch (Exception e) {
			result.setCode(AjaxPageBean.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value="/resetPassword")
	@ResponseBody
	public AjaxPageBean resetPassword(HttpServletRequest request,Long operatorId,String operatorIds) throws Exception{
		AjaxPageBean result = new AjaxPageBean();
		log.debug("------"+operatorId+"------");
		try {
			if(StringUtils.isEmpty(operatorIds)){
				//重置单条
				operatorSV.resetPsw(operatorId);
			}else{
				//重置多条
				String[] ids = operatorIds.split(",");
				for (String id : ids) {
					operatorSV.resetPsw(Long.valueOf(id));
				}
			}
		} catch (Exception e) {
			result.setCode(AjaxPageBean.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value="/toChangePsw")
	public String toChangePsw(Model model, HttpServletRequest request,String operatorCode,String orgId,String type) throws Exception{
		
		return "operator/changePsw";
	}
	
	@RequestMapping(value="/savePassword")
	@ResponseBody
	public AjaxPageBean savePassword(HttpServletRequest request,String pswNew,String pswOld) throws Exception{
		AjaxPageBean result = new AjaxPageBean();
		
		SessionUser<UmOperator,UmStationtype,UmMenu,Object>  sessionUserInfo= WebUtils.getSessionUserInfo();
		UmOperator user = sessionUserInfo.getLoginUser();
		Long operatorId = user.getOperatorId();
		try {
			operatorSV.changePsw(operatorId, pswNew, pswOld);
		} catch (Exception e) {
			result.setCode(AjaxPageBean.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	/**
	 * @author syou 岗位授权
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/toGrantStation")
	public String toGrantStation(Model model,String toOperatorId) throws Exception{
		//授权操作人员，授权组织
		SessionUser<UmOperator,UmStation,UmMenu,Object>  sessionUserInfo= WebUtils.getSessionUserInfo();
		UmOperator umOperator = sessionUserInfo.getLoginUser();
		model.addAttribute("umOperator", umOperator);
		model.addAttribute("toOperatorId", toOperatorId);
		List<UmStation> stations= stationSV.queryStationsByOperator(Long.valueOf(toOperatorId), umOperator.getDomainId());
		StringBuffer buf = new StringBuffer();
		for (UmStation umStation : stations) {
			buf.append(umStation.getStationId()).append(",");
		}
		if(buf.length() > 0)buf.deleteCharAt(buf.length() - 1);
		model.addAttribute("oldStationIds", buf.toString());
		return "station/stationGrant";
	}
	
	@RequestMapping(value="/relaStations")
	@ResponseBody
	public AjaxPageBean saveRelaStations(String toOperatorId,String grantOrgId,String allowReauthor,String stationIds,String oldStationIds) throws Exception{
		AjaxPageBean result = new AjaxPageBean();
		SessionUser<UmOperator,UmStationtype,UmMenu,Object>  sessionUserInfo= WebUtils.getSessionUserInfo();
		UmOperator user = sessionUserInfo.getLoginUser();
		try {
			operator2StationSV.saveOperator2Station(toOperatorId, grantOrgId, stationIds, oldStationIds, user.getOperatorId(), Short.valueOf(allowReauthor));
		} catch (Exception e) {
			result.setCode(AjaxPageBean.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value="/toRelaStationList")
	public String toRelaStationList(Model model,String toOperatorId) throws Exception{
		//授权操作人员，授权组织
		SessionUser<UmOperator,UmStation,UmMenu,Object>  sessionUserInfo= WebUtils.getSessionUserInfo();
		UmOperator umOperator = sessionUserInfo.getLoginUser();
		model.addAttribute("umOperator", umOperator);
		model.addAttribute("toOperatorId", toOperatorId);
		
		return "operator/operator2station";
	}
	
	@RequestMapping(value="/relaStationList", method = RequestMethod.POST)
	@ResponseBody
	public AjaxPageBean queryRelaStationList(HttpServletRequest request,String operatorId,
			String stationName,String orgName,
			@RequestParam(value="iDisplayStart", defaultValue="0") int pageNo, 
			@RequestParam(value="iDisplayLength", defaultValue="10")int pageSize) throws Exception{
		AjaxPageBean result = new AjaxPageBean();
		
		int sEcho = Integer.valueOf(request.getParameter("sEcho"));
		result.setOther(sEcho);
		try {
			int count = operator2StationSV.queryRelaStationByOperatorCount(Long.valueOf(operatorId),stationName,orgName);
			if(count > 0){
				List<QryUmOperator2Station> list = operator2StationSV.queryRelaStationByOperator(Long.valueOf(operatorId),stationName,orgName,(pageNo / pageSize)+1,pageSize);
				result.setData(list);
			}else{
				result.setData(new ArrayList<QryUmOperator2Station>());
			}
			result.setCurrentPage(pageNo+1);
			result.setTotal(count);
		} catch (Exception e) {
			log.error(e.getMessage());
			result.setCode(AjaxPageBean.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value="/toStationGrant")
	public String toStationGrant(Model model,String toOperatorId) throws Exception{
		//授权操作人员，授权组织
		SessionUser<UmOperator,UmStation,UmMenu,Object>  sessionUserInfo= WebUtils.getSessionUserInfo();
		UmOperator umOperator = sessionUserInfo.getLoginUser();
		model.addAttribute("umOperator", umOperator);
		model.addAttribute("toOperatorId", toOperatorId);
		return "operator/grantStation";
	}
	
	@RequestMapping(value="/relaStations2")
	@ResponseBody
	public AjaxPageBean saveRelaStations2(String toOperatorId,String grantOrgId,String allowReauthor,String stationId) throws Exception{
		AjaxPageBean result = new AjaxPageBean();
		SessionUser<UmOperator,UmStationtype,UmMenu,Object>  sessionUserInfo= WebUtils.getSessionUserInfo();
		UmOperator user = sessionUserInfo.getLoginUser();
		try {
			operator2StationSV.saveOperator2Station(Long.valueOf(toOperatorId), grantOrgId, Long.valueOf(stationId), user.getOperatorId(), new Date(), Short.valueOf(allowReauthor));
		} catch (Exception e) {
			result.setCode(AjaxPageBean.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	@RequestMapping(value="/delRelaStation")
	@ResponseBody
	public AjaxPageBean delRelaStation(String id,String operatorId,String stationId,String orgId) throws Exception{
		AjaxPageBean result = new AjaxPageBean();
		try {
			if(StringUtils.isEmpty(id)){
				throw new BusinessException("", "参数错误！");
			}
			operator2StationSV.deleteRelaById(Long.valueOf(id));
//			if(StringUtils.isEmpty(operatorId) || StringUtils.isEmpty(stationId)){
//				throw new BusinessException("", "参数错误！");
//			}
//			operator2StationSV.deleteRelaStation(Long.valueOf(operatorId), Long.valueOf(stationId),orgId);
		} catch (Exception e) {
			result.setCode(AjaxPageBean.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value="/toRelaStationMod")
	public String toRelaStationMod(Model model,String id) throws Exception{
		if(StringUtils.isEmpty(id)){
			throw new BusinessException("", "参数错误！");
		}
		QryUmOperator2Station rela = operator2StationSV.queryRelaStationInfo(Long.valueOf(id));
		model.addAttribute("rela", rela);
		return "operator/grantStationMod";
	}
	
	
	@RequestMapping(value="/updateRelaStations")
	@ResponseBody
	public AjaxPageBean updateRelaStations(String id,String grantOrgId,String allowReauthor) throws Exception{
		AjaxPageBean result = new AjaxPageBean();
		try {
			if(StringUtils.isEmpty(id) || StringUtils.isEmpty(grantOrgId) || StringUtils.isEmpty(allowReauthor)){
				throw new BusinessException("", "参数错误！");
			}
			operator2StationSV.updateRelaStation(Long.valueOf(id), grantOrgId, Short.valueOf(allowReauthor));
		} catch (Exception e) {
			result.setCode(AjaxPageBean.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	}
}
