package com.msb.config;

import com.msb.interceptor.SimpleInterceptorAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * description : WebMvc配置
 * @author kunlunrepo
 * date :  2023-05-24 11:14
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 配置拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SimpleInterceptorAdapter()) // 拦截器
                .addPathPatterns("/login"); // 拦截路径
    }
}
