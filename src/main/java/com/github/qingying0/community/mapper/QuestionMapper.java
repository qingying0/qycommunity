package com.github.qingying0.community.mapper;

import com.github.qingying0.community.dto.QuestionDTO;
import com.github.qingying0.community.entity.Question;
import com.github.qingying0.community.mymapper.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionMapper extends MyMapper<Question> {


    List<QuestionDTO> selectNewQuestion();

    QuestionDTO selectById(Long questionId);

    void updateScoreById(@Param("questionId") Long questionid,@Param("score") double score);

    List<Question> selectHotQuestion();
}
