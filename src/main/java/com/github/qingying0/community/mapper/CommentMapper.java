package com.github.qingying0.community.mapper;

import com.github.qingying0.community.dto.CommentDTO;
import com.github.qingying0.community.dto.ReplyDTO;
import com.github.qingying0.community.entity.Comment;
import com.github.qingying0.community.mymapper.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper extends MyMapper<Comment> {

    List<CommentDTO> selectByQuestionId(Long questionId);

    List<ReplyDTO> selectReplyByCommentId(Long commentId);
}
