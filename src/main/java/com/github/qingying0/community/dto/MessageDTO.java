package com.github.qingying0.community.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@Data
public class MessageDTO {

    private Long id;

    private Long fromId;

    private Long toId;

    private String conversationId;

    /**
     * 0-未读;1-已读;2-删除;
     */
    private Integer status;

    private Date createTime;

    private String content;

    private Long otherUserId;

    private String username;

    private String headerUrl;
}
