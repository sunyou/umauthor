package com.ai.umauthor.web.controller.mgr;

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
import com.ai.common.util.WebUtils;
import com.ai.umauthor.server.model.TableCols;
import com.ai.umauthor.server.model.UmAreas;
import com.ai.umauthor.server.model.UmDictitem;
import com.ai.umauthor.server.model.UmMenu;
import com.ai.umauthor.server.model.UmOperator;
import com.ai.umauthor.server.model.UmOrg;
import com.ai.umauthor.server.model.UmStationtype;
import com.ai.umauthor.server.service.mrg.interfaces.AreasSV;
import com.ai.umauthor.server.service.mrg.interfaces.DictitemSV;
import com.ai.umauthor.server.service.mrg.interfaces.OperatorSV;
import com.ai.umauthor.server.service.mrg.interfaces.OrgSV;
import com.ai.umauthor.server.service.mrg.model.QryUmOperator;
import com.ai.umauthor.server.service.mrg.model.QryUmOrg;
import com.ai.umauthor.server.service.mrg.model.SessionUser;

@Controller
@RequestMapping("/org")
public class OrgController {
	
	private static final Logger log = LoggerFactory.getLogger(OrgController.class);
	
	@Autowired
	private OrgSV orgSV;
	
	@Autowired
	private OperatorSV operatorSV;
	
	@Autowired
	private DictitemSV dictitemSV;
	
	@Autowired
	private AreasSV areasSV;
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, Model model) throws Exception{
		SessionUser<UmOperator,UmStationtype,UmMenu,Object>  sessionUserInfo= WebUtils.getSessionUserInfo();
		UmOperator user = sessionUserInfo.getLoginUser();
		model.addAttribute("orgId", user.getOrgId());
		UmOrg umOrg = orgSV.queryOrgById(user.getOrgId());
		model.addAttribute("orgName", umOrg.getOrgName());
		
		//获取省份信息
		List<UmAreas> provices = areasSV.getProvice();
		model.addAttribute("provices", provices);
		
		//组织类型字典
		List<UmDictitem> orgTypes=dictitemSV.queryUmDictitemByDictKey("ORG_TYPE");
		model.addAttribute("orgTypes", orgTypes);
    	return "org/index";
    }
	
	@RequestMapping(value="/getOrgNodes", method = RequestMethod.POST)
	@ResponseBody
	public AjaxPageBean getNodes(HttpServletRequest request,String orgId) throws Exception{
		AjaxPageBean result = new AjaxPageBean();
		
		String orgNameTree = request.getParameter("orgNameTree");
		String orgTypeTree = request.getParameter("orgTypeTree");
		
		try {
			List<UmOrg> orgs = orgSV.queryOrgsByParentId(orgId,orgNameTree,orgTypeTree);
			result.setData(orgs);
		} catch (Exception e) {
			result.setCode(AjaxPageBean.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	@RequestMapping("/orgList")
	@ResponseBody
	public AjaxPageBean orgList(HttpServletRequest request,String orgId,
								@RequestParam(value="iDisplayStart", defaultValue="0") int pageNo, 
								@RequestParam(value="iDisplayLength", defaultValue="10")int pageSize,
								String isAllOrg) throws Exception{
		AjaxPageBean result = new AjaxPageBean();
		
		int sEcho = Integer.valueOf(request.getParameter("sEcho"));
		result.setOther(sEcho);
		int page = (pageNo / pageSize)+1;
		
		SqlCondition cond = new SqlCondition();
		
		TableCols.UmOrgCols t = TableCols.UmOrgCols;
		String orgNameOrgS = request.getParameter("orgNameOrgS");
		if(!StringUtils.isEmpty(orgNameOrgS)){
			cond.like(t.orgName, orgNameOrgS);
		}
		String orgTypeOrgS = request.getParameter("orgTypeOrgS");
		if(!StringUtils.isEmpty(orgTypeOrgS)){
			cond.like(t.orgType, orgTypeOrgS);
		}
		String provinceCodeOrgS = request.getParameter("provinceCodeOrgS");
		if(!StringUtils.isEmpty(provinceCodeOrgS)){
			cond.like(t.provinceCode, provinceCodeOrgS);
		}
		String cityCodeOrgS = request.getParameter("cityCodeOrgS");
		if(!StringUtils.isEmpty(cityCodeOrgS)){
			cond.like(t.cityCode, cityCodeOrgS);
		}
		String districtCodeOrgS = request.getParameter("districtCodeOrgS");
		if(!StringUtils.isEmpty(districtCodeOrgS)){
			cond.like(t.districtCode, districtCodeOrgS);
		}
		
		try {
			
			//查询全部
			if("1".equals(isAllOrg)){
				int total = orgSV.selectOrgCountBySqlCond(orgId,cond);
				cond.page(page, pageSize);
				List<QryUmOrg> orgList = orgSV.selectOrgListBySqlCond(orgId,cond);
				result.setCurrentPage(page);
				result.setTotal(total);
				result.setData(orgList);
			}else{
				//查询下属
				int total = orgSV.selectOrgCountUnderParentBySqlCond(orgId,cond);
				cond.page(page, pageSize);
				List<QryUmOrg> orgList = orgSV.selectOrgListUnderParentBySqlCond(orgId,cond);
				result.setCurrentPage(page);
				result.setTotal(total);
				result.setData(orgList);
			}
			
			
		} catch (Exception e) {
			result.setCode(AjaxPageBean.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value="/getOrgInfo", method = RequestMethod.POST)
	@ResponseBody
	public AjaxPageBean getOrgInfo(HttpServletRequest request,String orgId) throws Exception{
		AjaxPageBean result = new AjaxPageBean();
		try {
			UmOrg org = orgSV.queryOrgById(orgId);
			result.setData(org);
		} catch (Exception e) {
			result.setCode(AjaxPageBean.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value="/editOrgForm", method = {RequestMethod.POST, RequestMethod.GET})
	public String editOrgForm(Model model, HttpServletRequest request,String orgId,String type,String orgName) throws Exception{
		long domainId = 1l;
		
		
		UmOrg umParentOrg = null;
		
		//获取省份信息
		List<UmAreas> provices = areasSV.getProvice();
		model.addAttribute("provices", provices);
		
		//组织类型字典
		List<UmDictitem> orgTypes=dictitemSV.queryUmDictitemByDictKey("ORG_TYPE");
		model.addAttribute("orgTypes", orgTypes);
		
		//行政级别字典
		List<UmDictitem> adminLevels=dictitemSV.queryUmDictitemByDictKey("ADMINISTRATIVE_LEVEL");
		model.addAttribute("adminLevels", adminLevels);
		
		if("add".equals(type)){
			//新增
			UmOrg umOrg = new UmOrg();
			umOrg.setParentOrgId(orgId);
			umOrg.setDomainId(domainId);
			umOrg.setOrderId(0l);
			model.addAttribute("umOrg", umOrg);
			
			try {
				//取上级组织名称
				umParentOrg = orgSV.queryOrgById(orgId);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			
		}else{
			//修改
			try {
				UmOrg umOrg = orgSV.queryOrgById(orgId);
				model.addAttribute("umOrg", umOrg);
				model.addAttribute("type", "update");
				
				//取上级组织名称
				umParentOrg = orgSV.queryOrgById(String.valueOf(umOrg.getParentOrgId()));
				
				//地市，区域
				if(umOrg!=null){
					if(!StringUtils.isEmpty(umOrg.getProvinceCode())){
						List<UmAreas> cities = areasSV.getUmAreasByParent(umOrg.getProvinceCode());
						model.addAttribute("cities", cities);
						
						if(!StringUtils.isEmpty(umOrg.getCityCode())){
							List<UmAreas> districts = areasSV.getUmAreasByParent(umOrg.getCityCode());
							model.addAttribute("districts", districts);
						}
					}
					
				}
				
				
			} catch (Exception e) {
				log.error(e.getMessage());
	//			result.setCode(AjaxPageBean.ERROR_CODE);
	//			result.setMessage(e.getMessage());
			}
		}
		
		//设置上级组织名称
		if(umParentOrg!=null){
			model.addAttribute("orgName", umParentOrg.getOrgName());
		}else{
			model.addAttribute("orgName", "无");
		}
		
		
		return "org/editOrgForm";
	}
	
	@RequestMapping(value="/saveOrg", method = RequestMethod.POST)
	@ResponseBody
	public AjaxPageBean saveOrg(HttpServletRequest request,UmOrg org) throws Exception{
		AjaxPageBean result = new AjaxPageBean();
		try {
			orgSV.insertOrg(org);
		} catch (Exception e) {
			log.error("insertOrg:"+e.getMessage());
			result.setCode(AjaxPageBean.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value="/updateOrg", method = RequestMethod.POST)
	@ResponseBody
	public AjaxPageBean updateOrg(HttpServletRequest request,UmOrg org) throws Exception{
		AjaxPageBean result = new AjaxPageBean();
		try {
			orgSV.modifyOrg(org);
		} catch (Exception e) {
			log.error("updateOrg:"+e.getMessage());
			result.setCode(AjaxPageBean.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value="/deleteOrg", method = RequestMethod.POST)
	@ResponseBody
	public AjaxPageBean deleteOrg(HttpServletRequest request,String orgId) throws Exception{
		AjaxPageBean result = new AjaxPageBean();
		log.debug("------"+orgId+"------");
		try {
			orgSV.deleteOrg(orgId);
		} catch (Exception e) {
			result.setCode(AjaxPageBean.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
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
		log.debug("------iDisplayStart:"+request.getParameter("iDisplayStart")+"  sEcho:"+request.getParameter("sEcho")+"------");
		log.debug("------orgId:"+orgId+"  pageNo:"+pageNo+"  pageSize:"+pageSize+"------");
		log.debug("------createTimeStart:"+request.getParameter("createTimeStart")+"  pageNo:"+pageNo+"  pageSize:"+pageSize+"------");
		TableCols.UmOperatorCols t = TableCols.UmOperatorCols;
		SqlCondition cond = new SqlCondition();
		
		
		String operatorNameS = request.getParameter("operatorNameS");
		if(!StringUtils.isEmpty(operatorNameS)){
			cond.like(t.operatorName, operatorNameS);
		}
		
//		cond.eq(t.orgId, orgId).page(pageNo, pageSize); //sEcho
		try {
			int total = operatorSV.selectOperatorCountByOrgAndSqlCond(orgId,cond);
			cond.page(page, pageSize);
			log.debug("-----total:"+total+"-------");
			List<QryUmOperator> operatorList = operatorSV.selectOperatorByOrgAndSqlCond(orgId,cond);
			result.setCurrentPage(page);
			result.setTotal(total);
			result.setData(operatorList);
		} catch (Exception e) {
			result.setCode(AjaxPageBean.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 根据orgId获取org信息
	 * @param request
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getOrgNode")
	@ResponseBody
	public AjaxPageBean getOrgNode(HttpServletRequest request,String orgId) throws Exception{
		AjaxPageBean result = new AjaxPageBean();
		
		try {
			UmOrg org = orgSV.queryOrgById(orgId);
			result.setData(org);
		} catch (Exception e) {
			result.setCode(AjaxPageBean.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value="/getAreaNodes", method = RequestMethod.GET)
	@ResponseBody
	public AjaxPageBean getAreaNodes(HttpServletRequest request,String parentId) throws Exception{
		AjaxPageBean result = new AjaxPageBean();
//		System.out.println("code:"+request.getParameter("code"));
//		System.out.println("name:"+request.getParameter("name"));
//		String type = request.getParameter("type");
//		if(StringUtils.isEmpty(type)){
//			type = "type";
//		}
		
		
		try {
			List<UmAreas> data = areasSV.getUmAreasByParent(parentId);
			result.setData(data);
		} catch (Exception e) {
			result.setCode(AjaxPageBean.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	}
}
