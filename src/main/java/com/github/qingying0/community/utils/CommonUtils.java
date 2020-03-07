package com.github.qingying0.community.utils;

import com.github.qingying0.community.exception.CustomException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.validation.BindingResult;
import org.springframework.util.DigestUtils;
import org.springframework.validation.ObjectError;

import java.security.MessageDigest;
import java.util.UUID;

public class CommonUtils {

    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String MD5(String key) {
        if(StringUtils.isEmpty(key)) {
            throw new CustomException("md5加密字符串为空");
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }

    public static void validate(BindingResult result) {
        if(result.hasErrors()) {
            for(ObjectError error : result.getAllErrors()) {
                throw new CustomException(error.getDefaultMessage());
            }
        }
    }
}
