package com.github.qingying0.community.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@Data
public class Message {
    @Id
    private Long id;

    @Column(name = "from_id")
    private Long fromId;

    @Column(name = "to_id")
    private Long toId;

    @Column(name = "conversation_id")
    private String conversationId;

    /**
     * 0-未读;1-已读;2-删除;
     */
    private Integer status;

    @Column(name = "create_time")
    private Date createTime;

    private String content;
}
