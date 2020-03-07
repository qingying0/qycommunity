package com.github.qingying0.community.service;

import com.github.qingying0.community.dto.CommentDTO;
import com.github.qingying0.community.entity.Comment;

import java.util.List;

public interface ICommentService {

    List<CommentDTO> getByQuestionId(Long questionId);

    void addComment(Comment comment);
}
