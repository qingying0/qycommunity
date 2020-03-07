package com.github.qingying0.community.web.controller;

import com.github.qingying0.community.dto.ResultDTO;
import com.github.qingying0.community.exception.CustomException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class BaseControler {

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResultDTO handler(CustomException e) {
        return ResultDTO.errorOf(e);
    }
}
