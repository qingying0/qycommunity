package com.github.qingying0.community.service;

import com.github.qingying0.community.dto.QuestionDTO;
import com.github.qingying0.community.entity.Question;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qingying0
 * @since 2020-03-02
 */
public interface IQuestionService {


    List<QuestionDTO> getNewQuestion();

    void addQuestion(Question question);

    QuestionDTO getById(Long questionId);

    List<Question> getByUserId(Long userId);

    void updateScore(Long questionid, double score);

    List<Question> getHostQuestion();
}
