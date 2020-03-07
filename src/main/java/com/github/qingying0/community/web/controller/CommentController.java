package com.github.qingying0.community.web.controller;

import com.github.qingying0.community.annotation.RequiredLogin;
import com.github.qingying0.community.entity.Comment;
import com.github.qingying0.community.exception.CustomException;
import com.github.qingying0.community.service.ICommentService;
import com.github.qingying0.community.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @RequiredLogin
    @PostMapping("/{questionId}")
    public String comment(@PathVariable("questionId")Long questionId, @Validated Comment comment, BindingResult result, Model model) {
        try {
            System.out.println(comment);
            CommonUtils.validate(result);
            commentService.addComment(comment);
        } catch (CustomException e) {
            e.printStackTrace();
            model.addAttribute("message", e.getMessage());
            if(e.getCode().equals(2001)) {
                return "redirect:/login";
            } else {
                return "redirect:/question/" + questionId;
            }
        }
        return "redirect:/question/" + questionId;
    }
}
