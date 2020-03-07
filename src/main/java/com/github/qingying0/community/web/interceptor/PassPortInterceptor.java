package com.github.qingying0.community.web.interceptor;

import com.github.qingying0.community.dao.RedisDao;
import com.github.qingying0.community.dto.UserDTO;
import com.github.qingying0.community.utils.CookieUtils;
import com.github.qingying0.community.utils.HostHolder;
import com.github.qingying0.community.utils.RedisKeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class PassPortInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisDao redisDao;

    @Autowired
    private HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = CookieUtils.getValue(request, "token");
        if(token != null) {
            UserDTO userDTO = (UserDTO) redisDao.get(RedisKeyUtils.getTokenKey(token));
            if(userDTO != null) {
                hostHolder.set(userDTO);
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        UserDTO userDTO = hostHolder.get();
        if(modelAndView != null && userDTO != null) {
            modelAndView.addObject("user", userDTO);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        hostHolder.clear();
    }
}
