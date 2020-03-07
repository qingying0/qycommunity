package com.github.qingying0.community.service.impl;

import com.github.qingying0.community.dao.RedisDao;
import com.github.qingying0.community.dto.QuestionDTO;
import com.github.qingying0.community.entity.Question;
import com.github.qingying0.community.exception.CustomCode;
import com.github.qingying0.community.exception.CustomException;
import com.github.qingying0.community.mapper.QuestionMapper;
import com.github.qingying0.community.service.IQuestionService;
import com.github.qingying0.community.utils.Constant;
import com.github.qingying0.community.utils.HostHolder;
import com.github.qingying0.community.utils.IdWorker;
import com.github.qingying0.community.utils.RedisKeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qingying0
 * @since 2020-03-02
 */
@Service
public class QuestionServiceImpl implements IQuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RedisDao redisDao;

    @Override
    public List<QuestionDTO> getNewQuestion() {
        return questionMapper.selectNewQuestion();
    }

    @Override
    public void addQuestion(Question question) {
        if(hostHolder.get() == null) {
            throw new CustomException(CustomCode.NO_LOGIN);
        }
        question.setUserId(hostHolder.get().getId());
        question.setCommentCount(0);
        question.setCreateTime(new Date());
        question.setStatus(Constant.QUESTION_STATUS_NORMAL);
        question.setType(Constant.QUESTION_TYPE_NORMAL);
        question.setId(idWorker.nextId());
        question.setScore(0D);
        questionMapper.insert(question);
        redisDao.sSet(RedisKeyUtils.getQuestionScoreKey(), question.getId());
    }

    @Override
    public QuestionDTO getById(Long questionId) {
        return questionMapper.selectById(questionId);
    }

    @Override
    public List<Question> getByUserId(Long userId) {
        Example questionExample = new Example(Question.class);
        Example.Criteria userCriteria = questionExample.createCriteria();
        userCriteria.andEqualTo("userId", userId);
        return questionMapper.selectByExample(questionExample);
    }

    @Override
    public void updateScore(Long questionid, double score) {
        questionMapper.updateScoreById(questionid, score);
    }

    @Override
    public List<Question> getHostQuestion() {
        return questionMapper.selectHotQuestion();
    }
}
