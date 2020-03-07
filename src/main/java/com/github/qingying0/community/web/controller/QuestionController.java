package com.github.qingying0.community.web.controller;


import com.github.qingying0.community.annotation.RequiredLogin;
import com.github.qingying0.community.dto.CommentDTO;
import com.github.qingying0.community.dto.QuestionDTO;
import com.github.qingying0.community.dto.ReplyDTO;
import com.github.qingying0.community.dto.ResultDTO;
import com.github.qingying0.community.entity.Category;
import com.github.qingying0.community.entity.Question;
import com.github.qingying0.community.exception.CustomException;
import com.github.qingying0.community.service.*;
import com.github.qingying0.community.utils.CommonUtils;
import com.github.qingying0.community.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author qingying0
 * @since 2020-03-02
 */
@Controller
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private IQuestionService questionService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ICommentService commentService;

    @Autowired
    private ILikeService likeService;

    @RequiredLogin
    @GetMapping("/publish")
    public String publish(Model model) {
        List<Category> listCategory = categoryService.getAll();
        model.addAttribute("listCategory", listCategory);
        return "question/publish_question";
    }

    @PostMapping("/publish")
    @ResponseBody
    public ResultDTO doPublish(@Validated Question question, BindingResult result, Model model) {
        CommonUtils.validate(result);
        questionService.addQuestion(question);
        return ResultDTO.okOf();
    }

    @RequiredLogin
    @GetMapping("/{questionId}")
    public String toQuestion(@PathVariable("questionId") Long questionId, Model model) {
        QuestionDTO question = questionService.getById(questionId);
        List<CommentDTO> listComment = commentService.getByQuestionId(questionId);
        for(CommentDTO commentDTO : listComment) {
            commentDTO.setLikeCount(likeService.getLikeCount(Constant.ENTITY_TYPE_COMMENT, commentDTO.getId()));
            commentDTO.setLikeStatus(likeService.getLikeStatus(Constant.ENTITY_TYPE_COMMENT, commentDTO.getId()));
            for(ReplyDTO replyDTO : commentDTO.getListReply()) {
                replyDTO.setLikeCount(likeService.getLikeCount(Constant.ENTITY_TYPE_COMMENT, replyDTO.getId()));
                replyDTO.setLikeStatus(likeService.getLikeStatus(Constant.ENTITY_TYPE_COMMENT, replyDTO.getId()));
            }
        }
        model.addAttribute("question", question);
        model.addAttribute("listComment", listComment);
        return "question/question";
    }



}
