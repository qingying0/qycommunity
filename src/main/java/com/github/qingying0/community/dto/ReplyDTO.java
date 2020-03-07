package com.github.qingying0.community.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReplyDTO {

    private Long id;

    private Long userId;

    private Integer entityType;

    private Long entityId;

    private Long targetId;

    private String targetName;

    private Integer status;

    private Date createTime;

    private String content;

    private String username;

    private String headerUrl;

    private Long likeCount;

    private Integer likeStatus;
}
