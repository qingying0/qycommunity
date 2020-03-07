package com.github.qingying0.community.web.interceptor;


import com.github.qingying0.community.annotation.RequiredLogin;
import com.github.qingying0.community.utils.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class RequiredLoginInterceptor implements HandlerInterceptor {

    @Autowired
    private HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            RequiredLogin login = method.getAnnotation(RequiredLogin.class);
            if(login != null && hostHolder.get() == null) {
                response.sendRedirect("/login");
                return false;
            }
        }
        return true;
    }
}
