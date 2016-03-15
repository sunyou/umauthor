/**
 * StationAPIController.java	  V1.0   2016年2月23日 上午10:45:24
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.common.domain.AjaxBean;
import com.ai.common.exception.BusinessException;
import com.ai.umauthor.server.model.UmStation;
import com.ai.umauthor.server.service.mrg.interfaces.StationSV;
import com.ai.umauthor.web.controller.open.api.BaseAPIController;

@Controller
@RequestMapping("/api/station")
public class StationAPIController extends BaseAPIController {
	
	@Autowired
	private StationSV stationSV;
	
	/**
	 * 
	 * 功能描述：根据operatorCode获取操作员岗位信息
	 *
	 * @author  zhangly
	 * <p>创建日期 ：2016年2月23日 上午11:13:20</p>
	 *
	 * @param operatorCode
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping("/qryStationByOperatorCode")
	@ResponseBody
	public AjaxBean qryStationByOperatorCode(String operatorCode) {
		AjaxBean result = new AjaxBean();
		 if(StringUtils.isEmpty(operatorCode)) {
			 result.setCode(AjaxBean.ERROR_CODE);
			 result.setMessage("参数operatorCode不能为空");
			 return result;
		 }		 
		 result.setCode(AjaxBean.SUCCESS_CODE);
		 try {
			 List<UmStation> stations =  stationSV.qryStationByOperatorCode(operatorCode);
			 result.setData(stations);
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
	 * 功能描述：根据operatorId获取操作员岗位信息
	 *
	 * @author  zhangly
	 * <p>创建日期 ：2016年2月23日 上午11:12:57</p>
	 *
	 * @param operatorId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping("/qryStationByOperatorId")
	@ResponseBody
	public AjaxBean qryStationByOperatorCode(Long operatorId) {
		AjaxBean result = new AjaxBean();
		 if(null==operatorId) {
			 result.setCode(AjaxBean.ERROR_CODE);
			 result.setMessage("参数operatorId不能为空");
			 return result;
		 }		 
		 result.setCode(AjaxBean.SUCCESS_CODE);
		 try {
			 List<UmStation> stations =  stationSV.qryStationByOperatorId(operatorId);
			 result.setData(stations);
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
