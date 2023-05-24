package com.msb.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

/**
 * description :
 *
 * @author kunlunrepo
 * date : 2023-05-24 11:59
 */
public class HttpUtil {

    private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * 显示http请求的基本信息
     */
    public static void showRequestBase(HttpServletRequest request) {
        log.debug("【--------request*开始--------】");
        Thread thread = Thread.currentThread();
        log.debug("[threadId]: {}", thread.getId());
        log.debug("[threadName]: {}", thread.getName());
        log.debug("[sessionId]: {}", request.getRequestedSessionId());
        log.debug("[protocol]: {}", request.getProtocol());
        log.debug("[ip]: {}", request.getRemoteHost());
        log.debug("[port]: {}", request.getRemotePort());
        log.debug("[method]: {}", request.getMethod());
        log.debug("[contentType]: {}", request.getContentType());
        log.debug("[url]: {}", request.getRequestURL());
        log.debug("[uri]: {}", request.getRequestURI());
        log.debug("[query]: {}", request.getQueryString());
        // header
        List<String> headerKeys = new ArrayList<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            headerKeys.add(headerNames.nextElement());
        }
        log.debug("[headerNames]: {}", headerKeys);
        // attribute
        List<String> attributeKeys = new ArrayList<>();
        Enumeration<String> attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            attributeKeys.add(attributeNames.nextElement());
        }
        log.debug("[attributeKeys]: {}", attributeKeys);
        // parameter
        List<String> parameterKeys = new ArrayList<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            parameterKeys.add(parameterNames.nextElement());
        }
        log.debug("[parameterKeys]: {}", parameterKeys);
        log.debug("[parameterMap]: {}", request.getParameterMap());
        // requestBody
        BufferedReader br;
        String str, wholeStr = "";
        try {
            br = request.getReader();
            while((str = br.readLine()) != null){
                wholeStr += str;
            }
        } catch (IOException e) {
            log.error("[RequestBody解析错误] " + e);
        }
        log.debug("[requestBody]: {}", wholeStr);

        log.debug("【--------request*结束--------】");
    }

    /**
     * 显示http响应的基本信息
     */
    public static void showResponseBase(HttpServletResponse response) {
        log.debug("【--------response*开始--------】");
        Thread thread = Thread.currentThread();
        log.debug("[threadId]: {}", thread.getId());
        log.debug("[threadName]: {}", thread.getName());
        Collection<String> headerNames = response.getHeaderNames();
        log.debug("[headerNames]: {}", headerNames);
        log.debug("[status]: {}", response.getStatus());
        // 暂时无法读取响应数据
        log.debug("【--------response*结束--------】");
    }

    /**
     * 获取真实ip
     */
    private String getRealIp(HttpServletRequest request) {
        // 1.声明真实IP
        String ip;

        // 2.遍历请求头， 并且通过req获取ip地址
        /**
         * x-forwarded-for 各层代理的服务器地址 nginx服务器做代理时
         * x-real-ip 真实发出请求的客户端IP nginx服务器做代理时
         * proxy-client-ip apache http服务器做代理时
         * http_client_ip 代理服务器
         * */
        String headers = "x-forwarded-for,x-real-ip,proxy-client-ip,http_client_ip";
        for (String header : headers.split(",")) {
            if (!StringUtils.isEmpty(header)) {
                // req获取ip地址
                ip = request.getHeader(header);
                // 如果获取到的ip不为null，不为空串，并且不为unknow 则返回
                if (!StringUtils.isEmpty(ip) || !StringUtils.equalsIgnoreCase("unknow", ip)) {
                    if (StringUtils.equalsIgnoreCase("x_forwarded_for", header) && ip.indexOf(",") > 0) {
                        ip = ip.substring(0, ip.indexOf(","));
                    }
                    return ip;
                }
            }
        }

        // 3.传统方式获取
        return request.getRemoteAddr();
    }

}
