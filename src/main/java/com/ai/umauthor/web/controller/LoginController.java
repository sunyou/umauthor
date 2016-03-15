package com.ai.umauthor.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.common.domain.AjaxBean;
import com.ai.common.util.WebUtils;
import com.ai.umauthor.server.model.UmMenu;
import com.ai.umauthor.server.model.UmOperator;
import com.ai.umauthor.server.model.UmStation;
import com.ai.umauthor.server.service.mrg.interfaces.MenuSV;
import com.ai.umauthor.server.service.mrg.interfaces.StationSV;
import com.ai.umauthor.server.service.mrg.model.SessionUser;
import com.ai.umauthor.web.shiro.authc.AgricultureToken;

@Controller
//@RequestMapping("/login")
public class LoginController {
    private static final Logger log = LoggerFactory
	    .getLogger(LoginController.class);

    @Autowired
    StationSV stationSV;
    
    @Autowired
    MenuSV menuSV;
    
   
    
    
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request, Model model) throws Exception{
	return "login";
    }
    
    /**
     * 功能描述：退出、清除session
     */
    @RequestMapping("/doLogout")
    public String doLogout(HttpServletRequest request, HttpServletResponse response){
	SecurityUtils.getSubject().logout();  
	return "login";
    }
    
    /**
     * 功能描述：登录、session
     * 
     * @param name
     * @param password
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/doLogin")
    @ResponseBody
    public AjaxBean doLogin(String name, String password, String checkCode,
	    String domain, Model model, HttpServletRequest request,
	    HttpServletResponse response) {
	String code = "-1";
	String message = "";
	boolean isSuccess = false;
	AjaxBean resultBean = new AjaxBean();
	/*String sessionCheckCode = (String) WebUtils
		.getSession("sessionCheckCode");

	log.info("sessionCheckCode:" + sessionCheckCode);
	log.info("checkCode:" + checkCode);
	if (sessionCheckCode == null
		|| !sessionCheckCode.toLowerCase().equals(
			checkCode.toLowerCase())) {
	    resultBean.setCode(AjaxPageBean.SUCCESS_CODE);
	    resultBean.setMessage("验证码不正确，请重新输入！");
	    return resultBean;
	}*/
	if(domain==null){
	    domain="1";
	}
	AgricultureToken token = new AgricultureToken(name, password, null,
		domain);
	token.setRememberMe(true);
	log.debug("为了验证登录用户而封装的token为"
		+ ReflectionToStringBuilder.toString(token,
			ToStringStyle.MULTI_LINE_STYLE));
	// 获取当前的Subject
	Subject currentUser = SecurityUtils.getSubject();
	try {
	    // 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
	    // 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
	    // 所以这一步在调用login(token)方法时,它会走到doGetAuthenticationInfo()方法中,具体验证方式详见此方法
	    log.debug("对用户[" + name + "]进行登录验证..验证开始");
	    currentUser.login(token);

	    log.debug("对用户[" + name + "]进行登录验证..验证通过");
	    code = "0";
	    message = "验证通过";
	    // resultPageURL = "main";
	} catch (UnknownAccountException uae) {
	    log.debug("对用户[" + name + "]进行登录验证..验证未通过,未知账户");
	    code = "-1";
	    message = "未知账户";
	    // request.setAttribute("message_login", "未知账户");
	} catch (IncorrectCredentialsException ice) {
	    log.debug("对用户[" + name + "]进行登录验证..验证未通过,错误的凭证");
	    code = "-2";
	    message = "密码不正确";
	    // request.setAttribute("message_login", "密码不正确");
	} catch (LockedAccountException lae) {
	    log.debug("对用户[" + name + "]进行登录验证..验证未通过,账户已锁定");
	    code = "-3";
	    message = "账户已锁定";
	    // request.setAttribute("message_login", "账户已锁定");
	} catch (ExcessiveAttemptsException eae) {
	    log.debug("对用户[" + name + "]进行登录验证..验证未通过,错误次数过多");
	    code = "-4";
	    message = "用户名或密码错误次数过多";
	    // request.setAttribute("message_login", "用户名或密码错误次数过多");
	} catch (AuthenticationException ae) {
	    // 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
	    log.debug("对用户[" + name + "]进行登录验证..验证未通过,堆栈轨迹如下");
	    code = "-5";
	    message = "用户名或密码不正确";
	    ae.printStackTrace();
	    // request.setAttribute("message_login", "用户名或密码不正确");
	}
	 if(currentUser.isAuthenticated()){  
	     log.debug("用户[" + name + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
	     SessionUser<UmOperator,UmStation,UmMenu,Object>  sessionUserInfo= WebUtils.getSessionUserInfo();
	     UmOperator umOperator=sessionUserInfo.getLoginUser();
	     
	     List<UmStation> stationtypes= stationSV.queryStationsByOperator(umOperator.getOperatorId(), umOperator.getDomainId());
	     sessionUserInfo.setStations(stationtypes);
	     List<UmMenu> menus = menuSV.queryMenusAllByOperator(umOperator.getOperatorId(), umOperator.getDomainId());
	     sessionUserInfo.setMenus(menus);
	   /*  List<UmPermission> permissions=permissionSV.queryPermissionsByOperator(umOperator.getOperatorId(), umOperator.getDomainId());
	     sessionUserInfo.setPermissions(permissions);*/
	     /**/
	     //List<>
	     
	 }else {
		token.clear();
	}
	resultBean.setCode(code);
	resultBean.setMessage(message);
	return resultBean;

    }
}
