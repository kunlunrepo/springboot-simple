package com.msb.interceptor;

import com.msb.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * description : 拦截请求地址的拦截器
 * @author kunlunrepo
 * date :  2023-05-24 11:06
 */
public class SimpleInterceptorAdapter implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(SimpleInterceptorAdapter.class);

    /**
     * 请求处理之前
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("【拦截器-SimpleInterceptor】preHandle：--- {}", handler);


        Map<String, String[]> parameterMap = request.getParameterMap();
        parameterMap.forEach((key, value)-> {
            log.warn("参数：key: {} value: {}", key, value);
        });
        HttpUtil.showRequestBase(request);
        return true;
    }

    /**
     * 请求处理之后
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpUtil.showResponseBase(response);
        log.info("【拦截器-SimpleInterceptor】postHandle：--- {} {}", handler, modelAndView);
    }
}
