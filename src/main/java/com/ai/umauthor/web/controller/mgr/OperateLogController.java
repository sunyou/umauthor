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
import com.ai.umauthor.server.model.TableCols;
import com.ai.umauthor.server.model.UmOperateLog;
import com.ai.umauthor.server.service.mrg.interfaces.OperateLogSV;
import com.ai.umauthor.web.converter.StringToTimestampConverter;

@Controller
@RequestMapping("/operatelog")
public class OperateLogController {
	private static final Logger log = LoggerFactory.getLogger(OperateLogController.class);
	
	@Autowired
	private OperateLogSV operateLogSV;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, Model model) throws Exception{
		return "operatelog/index";
	}
	
	@RequestMapping("/operateLogList")
	@ResponseBody
	public AjaxPageBean operateLogList(HttpServletRequest request, 
								@RequestParam(value="iDisplayStart", defaultValue="0") int pageNo, 
								@RequestParam(value="iDisplayLength", defaultValue="10")int pageSize) throws Exception{
		AjaxPageBean result = new AjaxPageBean();
		
		int sEcho = Integer.valueOf(request.getParameter("sEcho"));
		result.setOther(sEcho);
		int page = (pageNo / pageSize)+1;
		log.debug("------iDisplayStart:"+request.getParameter("iDisplayStart")+"  sEcho:"+request.getParameter("sEcho")+"------");
		log.debug("------pageNo:"+pageNo+"  pageSize:"+pageSize+"------");
		TableCols.UmOperateLogCols t = TableCols.UmOperateLogCols;
		SqlCondition cond = new SqlCondition();
		
		//操作员编码
		String operatorCode = request.getParameter("operatorCode");
		if(!StringUtils.isEmpty(operatorCode)){
			cond.like(t.operatorCode, operatorCode);
		}
		
		//操作类型
		String operatorType = request.getParameter("operatorType");
		if(!StringUtils.isEmpty(operatorType)){
			cond.eq(t.operatorType, operatorType);
		}
		
		StringToTimestampConverter converter = new StringToTimestampConverter();
		//操作时间--开始
		String operatorDateBegin = request.getParameter("operatorDateBegin");
		if(!StringUtils.isEmpty(operatorDateBegin)){
			cond.ge(t.operatorDate, converter.convert(operatorDateBegin));
		}
		
		//操作时间--结束
		String operatorDateEnd = request.getParameter("operatorDateEnd");
		if(!StringUtils.isEmpty(operatorDateEnd)){
			cond.le(t.operatorDate, converter.convert(operatorDateEnd));
		}
		
		cond.orderByDesc(t.operatorDate);
		
		try {
			int total = operateLogSV.selectLogCountBySqlCond(cond);
			cond.page(page, pageSize);
			log.debug("-----total:"+total+"-------");
			List<UmOperateLog> logList = operateLogSV.selectLogBySqlCond(cond);
			result.setCurrentPage(page);
			result.setTotal(total);
			result.setData(logList);
		} catch (Exception e) {
			result.setCode(AjaxPageBean.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	}
}
