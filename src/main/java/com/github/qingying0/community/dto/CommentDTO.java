package com.github.qingying0.community.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Data
public class CommentDTO {

    private Long id;

    private Long userId;

    private Integer entityType;

    private Long entityId;

    private Long targetId;

    private Integer status;

    private Date createTime;

    private String content;

    private String username;

    private String headerUrl;

    private List<ReplyDTO> listReply;

    private Long likeCount;

    private Integer likeStatus;
}
