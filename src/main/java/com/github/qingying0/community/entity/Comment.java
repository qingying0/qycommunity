package com.github.qingying0.community.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class Comment {
    @Id
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "entity_type")
    private Integer entityType;

    @Column(name = "entity_id")
    @NotNull
    private Long entityId;

    @Column(name = "target_id")
    @NotNull
    private Long targetId;

    private Integer status;

    @Column(name = "create_time")
    private Date createTime;

    @NotEmpty
    private String content;
}
