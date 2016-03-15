package com.ai.umauthor.web.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.ai.common.domain.UserHolder;
//import com.ai.common.util.CookieUtil;

/**
 * 解析cookie中的host
 * Created with IntelliJ IDEA.
 * User: chengzj
 * Date: 15-3-12
 * Time: 下午5:10
 * To change this template use File | Settings | File Templates.
 */

public class UserHolderInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(UserHolderInterceptor.class);

    private String domain;
    private String appCode;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURL().toString();

        logger.debug(" -------------------"+ url + " 进入UserHolderInterceptor拦截器 ------------------------");

//        if (url.indexOf(".ajax") > -1 || url.indexOf(".html") > -1) {//拦截器拦截所有的ajax请求及html页面；
//            String _erp_descd = CookieUtil.getCookie(request, UserHolder.COOKIE_KEY_USERNAME);//从Cookie中获取erp信息（编码后的）
//            String _pwd_descd = CookieUtil.getCookie(request, UserHolder.COOKIE_KEY_PWD);//从Cookie中获取密码信息（编码后的）
//
//            UserHolder hostHolder = new UserHolder();//Urm用户登录信息
//            if (StringUtils.isNotBlank(_erp_descd) && StringUtils.isNotBlank(_pwd_descd)) {//Cookie中是否存在erp,密码
//                try {
//                    Object empBean =  null;//empSV.login(_erp_descd, _pwd_descd);    //EmpInfo.getEmpInfo(request) ; 	  
//                    if (empBean != null) {
////                        hostHolder.setUser(empBean);
////                        hostHolder.setName(empBean.getLoginName());
//                        hostHolder.setLogin(true);//用户已登录
//                        if(url.indexOf(".html") > -1) {//如果是html请求将会走权限获取，如果非.html资源请求跳过。
//                            try {
//                                //("----------------------获取用户系统信息--------------------");
////                                UrmSysInfo sysInfo = userInterface.findUserSysInfoByErp(hostHolder.getErp());//用户系统信息
////                                hostHolder.setUserSysInfo(sysInfo);
//                            } catch (Exception e) {
//                                logger.error("进入UserHolderInterceptor拦截器 findUserSysInfoByErp 获取用户系统信息 失败：", e);
//                            }
//                        }
//                    }
//                } catch (Exception e) {
//                    logger.error("进入UserHolderInterceptor拦截器 findUserByCookie 失败：", e);
//                }
//
//            } else {
//                logger.debug(" --------------无Cookie信息-------------- ");
//            }
//
//            request.setAttribute(UserHolder.REQUEST_KEY_HOLDER, hostHolder);
//        }

        return true;
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
