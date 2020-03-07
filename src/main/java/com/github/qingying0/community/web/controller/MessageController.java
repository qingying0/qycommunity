package com.github.qingying0.community.web.controller;

import com.github.qingying0.community.dto.ResultDTO;
import com.github.qingying0.community.entity.Message;
import com.github.qingying0.community.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private IMessageService messageService;

    @PostMapping
    @ResponseBody
    public ResultDTO addMessage(Message message) {
        System.out.println("message =" + message);
        messageService.addMessage(message);
        return ResultDTO.okOf();
    }

}
