package com.ai.umauthor.web.controller.mgr;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.common.domain.AjaxPageBean;

@Controller
@RequestMapping("/group")
public class GroupController {
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request) throws Exception{
    	
    	return "group/index";
    }
	
	@RequestMapping(value="/getGroupInfo", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public AjaxPageBean getGroupInfo(HttpServletRequest request) throws Exception{
		AjaxPageBean ajax = new AjaxPageBean();
		Map<String,Object> result = new HashMap<String,Object>();
		
		int id = Integer.valueOf(request.getParameter("id"));
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		Map<String,Object> info = new HashMap<String,Object>();
		info.put("groupName", "组"+id);
		info.put("nickName", "昵称"+id);
		info.put("area", "0591");
		info.put("createTime", format.format(new Date()));
		info.put("remark", "备注"+id);
//		info.put("hobby", "2");
		
		List<Map<String,Object>> interest = new ArrayList<Map<String,Object>>();
		Map<String,Object> inte = new HashMap<String,Object>();
		inte.put("id", "1");
		inte.put("name", "n1");
		interest.add(inte);
		
		inte = new HashMap<String,Object>();
		inte.put("id", "2");
		inte.put("name", "n2");
		interest.add(inte);
		
		info.put("interest", interest);
		
		result.put("data", info);
		ajax.setData(result);
		return ajax;
	}
	
	@RequestMapping(value="/getNodes", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public AjaxPageBean getNodes(HttpServletRequest request) throws Exception{

		AjaxPageBean result = new AjaxPageBean();
		
		List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
		for(int i = 0;i<10;i++){
			Map<String,Object> val = new HashMap<String,Object>();
			val.put("id", "011"+i);
			val.put("name", "组织011"+i);
			if(i == 3){
				val.put("isParent", true);
			}else{
				val.put("isParent", false);
			}
			data.add(val);
		}
		result.setData(data);
		return result;
	}
	
	
	@RequestMapping(value="/list", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public AjaxPageBean list(HttpServletRequest request) throws Exception{
		AjaxPageBean ajax = new AjaxPageBean();

		int start = Integer.valueOf(request.getParameter("iDisplayStart"));
		int sEcho = Integer.valueOf(request.getParameter("sEcho"));
		int length = Integer.valueOf(request.getParameter("iDisplayLength"));
		
		String nickNameS = request.getParameter("nickNameS");
		System.out.println("nickNameS:"+nickNameS);
		System.out.println(String.format("start: %d length: %d", start, length));
		
		ajax.setCurrentPage(sEcho);
		ajax.setTotal(100);
		
		List<Object> record = new ArrayList<Object>();
		
		Map<String,Object> data = null;
		int index = start;
		for (int i = 0; i<length; i++) {
			data = new HashMap<String,Object>();
			data.put("id", index);
			if(!StringUtils.isEmpty(nickNameS)){
				data.put("username", nickNameS+index);
			}else{
				data.put("username", "用户"+index);
			}
			
			data.put("group", "组"+index);
			
			index++;
			record.add(data);
		}
		ajax.setData(record);
		
    	return ajax;
    }
}
