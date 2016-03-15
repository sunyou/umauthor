/**
 * OrgAPIMenuController.java	  V1.0   2016年2月19日 上午10:09:42
 *
 * Copyright (c) 2016 AsiaInfo, All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.ai.umauthor.web.controller.open.api.operator;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.common.domain.AjaxBean;
import com.ai.common.exception.BusinessException;
import com.ai.umauthor.server.model.UmMenu;
import com.ai.umauthor.server.model.UmOperator;
import com.ai.umauthor.server.service.mrg.interfaces.MenuSV;
import com.ai.umauthor.server.service.mrg.interfaces.OperatorSV;
import com.ai.umauthor.web.controller.open.api.BaseAPIController;

@Controller
@RequestMapping("/api/menu")
public class OrgAPIMenuController extends BaseAPIController {
	private static final Logger log = LoggerFactory.getLogger(OrgAPIMenuController.class);

	@Autowired
	private MenuSV menuSV;
	@Autowired
	private OperatorSV operatorSV;
	
	/**
	 * 
	 * 功能描述：根据操作员ID查询所有操作权限的菜单
	 *
	 * @author  zhangly
	 * <p>创建日期 ：2016年2月19日 上午10:18:12</p>
	 *
	 * @param operatorId
	 * @param domainId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping("/qryMenusAllByOperatorId")
	@ResponseBody
	public AjaxBean qryMenusAllByOperatorId(Long operatorId,
		    @RequestParam(value = "systemId") String systemId) {
		 AjaxBean result = new AjaxBean();
		 if(null==operatorId) {
			 result.setCode(AjaxBean.ERROR_CODE);
			 result.setMessage("参数operatorId不能为空");
			 return result;
		 }		 
		 result.setCode(AjaxBean.SUCCESS_CODE);
		 try {
			 UmOperator operator = operatorSV.selectOperatorById(String.valueOf(operatorId));
			 if(null==operator) {
				 result.setCode(AjaxBean.ERROR_CODE);
				 result.setMessage("未找到operatorId："+operatorId+"的操作员");
				 return result;
			 }
			 List<UmMenu> menus = menuSV.queryMenusAllByOperator(operatorId, operator.getDomainId(),Long.parseLong(systemId));
			 result.setData(menus);
		 } catch(BusinessException e) {
			 result.setCode(e.getErrorCode());
			 result.setMessage(e.getMessage());
		 } catch(Exception e) {
			 result.setCode(AjaxBean.ERROR_CODE);
			 result.setMessage(e.getMessage());
		 }
		 
		 return result;
	}
	
	/**
	 * 
	 * 功能描述：根据operatorCode查询所有操作权限的菜单
	 *
	 * @author  zhangly
	 * <p>创建日期 ：2016年2月22日 下午4:44:21</p>
	 *
	 * @param operatorCode
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping("/qryMenusAllByOperatorCode")
	@ResponseBody
	public AjaxBean qryMenusAllByOperatorCode(String operatorCode,  @RequestParam(value = "systemId") String systemId) {
		 AjaxBean result = new AjaxBean();
		 if(StringUtils.isEmpty(operatorCode)) {
			 result.setCode(AjaxBean.ERROR_CODE);
			 result.setMessage("参数operatorCode不能为空");
			 return result;
		 }		 
		 result.setCode(AjaxBean.SUCCESS_CODE);
		 try {
			 UmOperator operator = operatorSV.selectOperatorByCode(operatorCode);
			 if(null==operator) {
				 result.setCode(AjaxBean.ERROR_CODE);
				 result.setMessage("未找到operatorCode："+operatorCode+"的操作员或操作员已失效");
				 return result;
			 }
			 List<UmMenu> menus = menuSV.queryMenusByOperatorCode(operatorCode, operator.getDomainId(),Long.parseLong(systemId));
			 result.setData(menus);
		 } catch(BusinessException e) {
			 result.setCode(e.getErrorCode());
			 result.setMessage(e.getMessage());
		 } catch(Exception e) {
			 result.setCode(AjaxBean.ERROR_CODE);
			 result.setMessage(e.getMessage());
		 }
		 
		 return result;
	}
}
