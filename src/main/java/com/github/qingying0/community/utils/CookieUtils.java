package com.github.qingying0.community.utils;

import com.github.qingying0.community.exception.CustomException;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtils {

    public static String getValue(HttpServletRequest request, String name) {
        if(request == null || StringUtils.isEmpty(name)) {
            throw new CustomException("参数为空");
        }
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals(name)) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
