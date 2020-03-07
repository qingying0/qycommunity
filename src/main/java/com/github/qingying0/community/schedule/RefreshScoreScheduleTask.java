package com.github.qingying0.community.schedule;

import com.github.qingying0.community.dao.RedisDao;
import com.github.qingying0.community.dto.QuestionDTO;
import com.github.qingying0.community.entity.Question;
import com.github.qingying0.community.exception.CustomException;
import com.github.qingying0.community.service.IFollowService;
import com.github.qingying0.community.service.IQuestionService;
import com.github.qingying0.community.utils.Constant;
import com.github.qingying0.community.utils.RedisKeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@Component
public class RefreshScoreScheduleTask {

    @Autowired
    private RedisDao redisDao;

    @Autowired
    private IQuestionService questionService;

    @Autowired
    private static final Date initDate;

    @Autowired
    private IFollowService followService;

    static {
        try {
            initDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .parse("2015-1-1 00:00:00");
        } catch (ParseException e) {
            throw new RuntimeException("初始化初始时间失败");
        }
    }

    @Scheduled(fixedRate = 3 * 60 * 1000)
    public void reportCurrentTime() {
        BoundSetOperations<String, Object> operations = redisDao.sBound(RedisKeyUtils.getQuestionScoreKey());
        if(operations.size() == 0) {
            System.out.println("没有需要刷新的帖子");
            return;
        }
        System.out.println("计算分数");
        while(operations.size() > 0) {
            refreshScore(Long.valueOf(operations.pop().toString()));
        }
    }

    public void refreshScore(Long questionid) {
        QuestionDTO question = questionService.getById(questionid);
        Integer commentCount = question.getCommentCount();
        Long followerCount = followService.getFollowerCount(Constant.ENTITY_TYPE_QUESTION, questionid);
        double w = commentCount * 10 + followerCount * 5;
        double score = Math.log(Math.max(w, 1)) + (question.getCreateTime().getTime() - initDate.getTime()) / (1000 * 3600 * 24);
        questionService.updateScore(questionid, score);
    }
}
