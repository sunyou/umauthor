package com.ai.umauthor.web.interceptor;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: chengzj
 * Date: 15-3-12
 * Time: 下午5:10
 * To change this template use File | Settings | File Templates.
 */
public class ParamInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(ParamInterceptor.class);
    private static final String VERSION = DateFormatUtils.format(new Date(System.currentTimeMillis()), "yyyyMMddHH");
    private String domain;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object o) throws Exception {
        String url = request.getRequestURI().toString();
        logger.debug(" ------------------- domain=  " + domain + " " + url + " 进入ParamInterceptor拦截器 ------------------------");
        // 每次重启tomcat后所有的自己写的js/css会更新缓存
        request.setAttribute("staticVersion", VERSION);//静态资源版本号；
        request.setAttribute("mpsDomain", "http://" + domain);//Url域名前缀；
        request.setAttribute("domain", domain);//域名；
        request.setAttribute("tt_now", DateFormatUtils.format(new Date(), "yyyyMMdd_HHmmss"));
        String menuId = request.getParameter("menuId");
        request.setAttribute("currentMenuId", menuId);
        logger.info("===========================params================================");
        logger.info("===========================currentMenuId:"+menuId);
        logger.info("===========================domain:"+domain);
        logger.info("===========================staticVersion:"+VERSION);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object o,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object o,
                                Exception e) throws Exception {

    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
