package com.ai.umauthor.web.controller.mgr;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.common.domain.AjaxBean;
import com.ai.common.domain.AjaxPageBean;

/**
 * @author Typhon Chens
 * 2016年2月1日
 */
@Controller
public class PermissionDemoController {
    
    @RequestMapping(value="/permission-demo-1")
    @ResponseBody
    @RequiresPermissions("permission-demo-1")
    public AjaxBean permissionDemo1(){
	AjaxBean resultBean = new AjaxBean();
	resultBean.setCode( AjaxPageBean.SUCCESS_CODE);
	resultBean.setData("有权限");
	return resultBean;
    }
    
    
    @RequestMapping(value="/permission-demo-2")
    @ResponseBody
    @RequiresPermissions("permission-demo-2")
    public AjaxBean permissionDemo2(){
	AjaxBean resultBean = new AjaxBean();
	resultBean.setCode( AjaxPageBean.SUCCESS_CODE);
	resultBean.setData("无权限");
	return resultBean;
    }
}
