package com.github.qingying0.community.config;

import com.github.qingying0.community.annotation.RequiredLogin;
import com.github.qingying0.community.web.interceptor.PassPortInterceptor;
import com.github.qingying0.community.web.interceptor.RequiredLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private PassPortInterceptor passPortInterceptor;

    @Autowired
    private RequiredLoginInterceptor requiredLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(passPortInterceptor);
        registry.addInterceptor(requiredLoginInterceptor);
    }
}
