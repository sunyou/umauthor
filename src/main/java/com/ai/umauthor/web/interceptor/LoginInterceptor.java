package com.ai.umauthor.web.interceptor;

import com.ai.common.domain.UserHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: chengzj
 * Date: 15-3-12
 * Time: 下午5:10
 * To change this template use File | Settings | File Templates.
 */
public class LoginInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
    private String domain;

    private String appCode;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	//先屏蔽认证登陆
    	if(true){
    		return true;
    	}
    	
        logger.debug("--------- 进入登录拦截器 LoginInterceptor ---------");
        String url = request.getRequestURI().toString();

        if(url.indexOf("/frame/login.html") > -1){
             return true;
        }
        if (url.indexOf(".ajax") == -1 && url.indexOf(".html") == -1) {//对于非.ajax,.html资源免认证
            return true;
        }
        
        

        String fullUrl = request.getRequestURL().toString();
        try {
            return execute(fullUrl,url, request, response, handler);//执行操作
        } catch (Exception e) {
            logger.error("执行登录拦截器失败LoginInterceptor：", e);
            logger.debug("--------- 退出登录拦截器 LoginInterceptor ---------");
            return false;
        }
    }

    /**
     * @param url      拦截URL
     * @param request  request
     * @param response response
     * @param handler  handler
     * @return boolean
     */
    private boolean execute(String fullUrl,String url, HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        UserHolder userHolder = (UserHolder) request.getSession().getAttribute(UserHolder.REQUEST_KEY_HOLDER);//获取用户登录信息；

        if (handler instanceof HandlerMethod) {

            if (userHolder != null &&userHolder.isLogin()) {
                //用户已登录
                request.setAttribute("userHolder",userHolder);
                return true;
            } else {
                //进行用户登录认证
                return goNeedLogin(request, response, url,fullUrl);
            }

        }
        return true;
    }

    /**
     * 登录认证方法
     *
     * @param request  request
     * @param response response
     * @param url      拦截URl
     * @return boolean
     */
    private boolean goNeedLogin(HttpServletRequest request, HttpServletResponse response, String url,String fullUrl) {
        logger.debug(url + "--------- 进入用户登录认证 goNeedLogin ---------");
        try {
            if (url.indexOf(".ajax") > -1) {
                //所有的ajax请求资源必须经过权限授权认证
                logger.debug(url + "--------- 您需要登录系统才能使用该功能！ ---------");
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json;charset=utf-8");
                PrintWriter printWriter = response.getWriter();
                printWriter.print("您需要登录系统才能使用该功能！");
                printWriter.flush();
                printWriter.close();
            } else {
                //跳转到用户登录页面；
                String authUrl= request.getContextPath() +"/frame/login.html";
                
                response.sendRedirect(authUrl);
            }
        } catch (Exception e) {
            logger.error("进入用户登录认证失败goNeedLogin", e);
        }
        logger.debug("--------- goNeedLogin 退出登录拦截器 LoginInterceptor ---------");
        return false;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }
}
