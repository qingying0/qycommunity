package com.github.qingying0.community.web.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.qingying0.community.dto.QuestionDTO;
import com.github.qingying0.community.entity.Question;
import com.github.qingying0.community.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private IQuestionService questionService;

    @GetMapping(path = {"/", "/index"})
    public String index(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                        @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                        Model model) {
        PageHelper.startPage(currentPage, pageSize);
        List<QuestionDTO> listQuestion = questionService.getNewQuestion();
        PageInfo<QuestionDTO> pageInfo = new PageInfo<>(listQuestion, pageSize);
        List<Question> listHotQuestion = questionService.getHostQuestion();
        model.addAttribute("listQuestion", listQuestion);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("listHotQuestion", listHotQuestion);
        return "index";
    }
}
