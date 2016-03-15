/**
 * OrgAPIController.java	  V1.0   2016年2月18日 上午11:34:04
 *
 * Copyright (c) 2016 AsiaInfo, All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.ai.umauthor.web.controller.open.api.operator;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.common.domain.AjaxBean;
import com.ai.common.exception.BusinessException;
import com.ai.umauthor.server.model.UmOrg;
import com.ai.umauthor.server.service.mrg.interfaces.OrgSV;
import com.ai.umauthor.web.controller.open.api.BaseAPIController;

@Controller
@RequestMapping("/api/org")
public class OrgAPIController extends BaseAPIController {
	private static final Logger log = LoggerFactory.getLogger(OrgAPIController.class);
	
	@Autowired
	private OrgSV orgSV;
	
	/**
	 * 
	 * 功能描述：根据组织ID获取组织信息
	 *
	 * @author  zhangly
	 * <p>创建日期 ：2016年2月18日 上午11:41:42</p>
	 *
	 * @param orgId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping("/qryOrgById")
	@ResponseBody
	public AjaxBean qryOrgById(@RequestParam(value="orgId") String orgId){
		 AjaxBean result = new AjaxBean();
		 result.setCode(AjaxBean.SUCCESS_CODE);
		 try {
			 UmOrg org = orgSV.queryOrgById(orgId);
			 result.setData(org);
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
	 * 功能描述：根据当前组织ID，获取父组织信息
	 *
	 * @author  zhangly
	 * <p>创建日期 ：2016年2月18日 下午5:06:32</p>
	 *
	 * @param orgId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping("/qryParentOrgById")
	@ResponseBody
	public AjaxBean qryParentOrgById(@RequestParam(value="orgId") String orgId){
		 AjaxBean result = new AjaxBean();
		 result.setCode(AjaxBean.SUCCESS_CODE);
		 try {
			 UmOrg org = orgSV.qryParentOrgByOrgId(orgId);
			 result.setData(org);
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
	 * 功能描述：根据父组织ID，获取子组织对象列表
	 *
	 * @author  zhangly
	 * <p>创建日期 ：2016年2月18日 下午5:32:10</p>
	 *
	 * @param parentOrgId
	 * @param isRecursion 是否递归
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping("/qryChildOrgByParentId")
	@ResponseBody
	public AjaxBean qryChildOrgByParentId(@RequestParam(value="parentOrgId") String parentOrgId,
			boolean isRecursion){
		 AjaxBean result = new AjaxBean();
		 result.setCode(AjaxBean.SUCCESS_CODE);
		 try {
			 List<UmOrg> orgs = orgSV.qryChildOrgByParentId(parentOrgId, isRecursion);
			 result.setData(orgs);
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
