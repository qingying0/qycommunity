package com.github.qingying0.community.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class Question {
    @Id
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @NotEmpty(message = "标题不能为空")
    private String title;

    /**
     * 0-普通; 1-置顶;
     */
    private Integer type;

    /**
     * 0-正常; 1-精华; 2-拉黑;
     */
    private Integer status;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "comment_count")
    private Integer commentCount;

    private Double score;

    @NotEmpty(message = "内容不能为空")
    private String content;

    @Column(name = "category_id")
    private Long categoryId;

    private String tags;
}
