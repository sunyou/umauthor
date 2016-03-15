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

import com.ai.common.domain.AjaxBean;
import com.ai.common.domain.AjaxPageBean;
import com.ai.common.dynasql.SqlCondition;
import com.ai.common.exception.BusinessException;
import com.ai.common.util.WebUtils;
import com.ai.umauthor.server.model.TableCols;
import com.ai.umauthor.server.model.UmMenu;
import com.ai.umauthor.server.model.UmOperator;
import com.ai.umauthor.server.model.UmStation;
import com.ai.umauthor.server.model.UmStationtype;
import com.ai.umauthor.server.service.mrg.interfaces.OperateLogSV;
import com.ai.umauthor.server.service.mrg.interfaces.StationtypeSV;
import com.ai.umauthor.server.service.mrg.model.SessionUser;

@Controller
@RequestMapping("/stationtype")
public class StationtypeController {
	private static final Logger log = LoggerFactory.getLogger(StationtypeController.class);
	
	@Autowired
	private StationtypeSV stationtypeSV;
	
	@Autowired
	private OperateLogSV logSV;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, Model model) throws Exception{
		return "stationtype/index";
	}
	
	@RequestMapping("/stationtypeList")
	@ResponseBody
	public AjaxPageBean stationtypeList(HttpServletRequest request, 
								@RequestParam(value="iDisplayStart", defaultValue="0") int pageNo, 
								@RequestParam(value="iDisplayLength", defaultValue="10")int pageSize) throws Exception{
		AjaxPageBean result = new AjaxPageBean();
		
		int sEcho = Integer.valueOf(request.getParameter("sEcho"));
		result.setOther(sEcho);
		int page = (pageNo / pageSize)+1;
		log.debug("------iDisplayStart:"+request.getParameter("iDisplayStart")+"  sEcho:"+request.getParameter("sEcho")+"------");
		log.debug("------pageNo:"+pageNo+"  pageSize:"+pageSize+"------");
		TableCols.UmStationtypeCols t = TableCols.UmStationtypeCols;
		SqlCondition cond = new SqlCondition();
		
		//岗位类型编码
		String stationtypeCode = request.getParameter("stationtypeCode");
		if(!StringUtils.isEmpty(stationtypeCode)){
			cond.like(t.stationtypeCode, stationtypeCode);
		}
		
		//岗位类型名称
		String stationtypeName = request.getParameter("stationtypeName");
		if(!StringUtils.isEmpty(stationtypeName)){
			cond.like(t.stationtypeName, stationtypeName);
		}
		
		//有效状态
		String stationtypeState = request.getParameter("stationtypeState");
		if(!StringUtils.isEmpty(stationtypeState)){
			cond.eq(t.stationtypeState, stationtypeState);
		}
		
		//所属域ID，获取操作员domainId
		SessionUser<UmOperator, UmStation, UmMenu, Object> sessionUserInfo = WebUtils.getSessionUserInfo();
		UmOperator operator = sessionUserInfo.getLoginUser();
		Long domainId = operator.getDomainId();
		cond.eq(t.domainId, String.valueOf(domainId));
		
		try {
			int total = stationtypeSV.selectStationtypeCountBySqlCond(cond);
			cond.page(page, pageSize);
			log.debug("-----total:"+total+"-------");
			List<UmStationtype> stationtypeList = stationtypeSV.selectStationtypeBySqlCond(cond);
			result.setCurrentPage(page);
			result.setTotal(total);
			result.setData(stationtypeList);
		} catch (Exception e) {
			result.setCode(AjaxPageBean.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value = "/getStationtype", method = { RequestMethod.POST, RequestMethod.GET })
	public String getStationtype(HttpServletRequest request, Model model, Long stationtypeId) throws Exception {
		if (null != stationtypeId && stationtypeId > 0) {
			model.addAttribute("stationtype", stationtypeSV.queryStationtypeById(stationtypeId));
			return "stationtype/editStationtype";
		} else {
			return "stationtype/editStationtype";
		}
	}
	
	@RequestMapping(value="/saveStationtype", method = RequestMethod.POST)
	@ResponseBody
	public AjaxPageBean saveStationtype(HttpServletRequest request, UmStationtype stationtype){
		AjaxPageBean result = new AjaxPageBean();
		try {
			//所属域ID，获取操作员domainId
			SessionUser<UmOperator, UmStation, UmMenu, Object> sessionUserInfo = WebUtils.getSessionUserInfo();
			UmOperator operator = sessionUserInfo.getLoginUser();
			Long domainId = operator.getDomainId();
			stationtype.setDomainId(domainId);
			
			if(null != stationtype.getStationtypeId() && stationtype.getStationtypeId() > 0){
				stationtypeSV.modifyStationtype(stationtype);
			}else{
				stationtypeSV.insertStationtype(stationtype);
			}
			result.setCode(AjaxPageBean.SUCCESS_CODE);
			result.setMessage("操作成功！");
			
			logSV.saveOperateLog("fan", 1L, "SAVE_STATIONTYPE", "保存岗位类型");
		} catch (Exception e) {
			log.error("editMenu:"+e.getMessage());
			result.setCode(AjaxPageBean.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	} 
	
	@RequestMapping("/deleteStationtype")
	@ResponseBody
	public AjaxBean deleteStationtype(Model model, HttpServletRequest request) throws Exception {
		AjaxBean ajaxBean = new AjaxBean();
		String delIds = request.getParameter("delIds");
		
		try {
			stationtypeSV.deleteStationtype(delIds);

			ajaxBean.setCode(AjaxBean.SUCCESS_CODE);
			ajaxBean.setMessage("操作成功");
		} catch (BusinessException e) {
			e.printStackTrace();
			ajaxBean.setCode(AjaxBean.ERROR_CODE);
			ajaxBean.setMessage("操作失败" + e.getMessage());
			log.debug(e.getMessage());
		}
		return ajaxBean;

	}
}
