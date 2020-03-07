package com.github.qingying0.community.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@Data
public class QuestionDTO {

    private Long id;

    private Long userId;

    private String title;

    /**
     * 0-普通; 1-置顶;
     */
    private Integer type;

    /**
     * 0-正常; 1-精华; 2-拉黑;
     */
    private Integer status;

    private Date createTime;

    private Integer commentCount;

    private Double score;

    private String content;

    private String username;

    private String headerUrl;

    private String tags;

}
