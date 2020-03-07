package com.github.qingying0.community.service.impl;

import com.github.qingying0.community.dao.RedisDao;
import com.github.qingying0.community.dto.CommentDTO;
import com.github.qingying0.community.entity.Comment;
import com.github.qingying0.community.exception.CustomCode;
import com.github.qingying0.community.exception.CustomException;
import com.github.qingying0.community.mapper.CommentMapper;
import com.github.qingying0.community.service.ICommentService;
import com.github.qingying0.community.utils.Constant;
import com.github.qingying0.community.utils.HostHolder;
import com.github.qingying0.community.utils.IdWorker;
import com.github.qingying0.community.utils.RedisKeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private RedisDao redisDao;

    @Override
    public List<CommentDTO> getByQuestionId(Long questionId) {
        return commentMapper.selectByQuestionId(questionId);
    }

    @Override
    public void addComment(Comment comment) {
        if(hostHolder.get() == null) {
            throw new CustomException(CustomCode.NO_LOGIN);
        }
        comment.setId(idWorker.nextId());
        comment.setUserId(hostHolder.get().getId());
        comment.setCreateTime(new Date());
        comment.setStatus(Constant.COMMENT_STATUS_NORMAL);
        commentMapper.insert(comment);
        if(comment.getEntityType().equals(Constant.COMMENT_ENTITY_TYPE_QUESTION)) {
            redisDao.sSet(RedisKeyUtils.getQuestionScoreKey(), comment.getEntityId());
        }
    }
}
