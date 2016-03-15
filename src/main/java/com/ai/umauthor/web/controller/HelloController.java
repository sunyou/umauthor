package com.ai.umauthor.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
public class HelloController {
		
	@RequestMapping(value="/index", method = RequestMethod.GET)
	@ResponseBody
	public Object index(ModelMap model ,HttpServletRequest request) throws Exception{
		
    	Map<String,Object> result = new HashMap<String,Object>();
    	result.put("name", "h1");
    	return result;
    }
}
