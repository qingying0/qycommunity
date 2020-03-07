package com.github.qingying0.community.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@Data
public class UserDTO {
    private Long id;

    private String username;

    private String email;

    /**
     * 0-普通用户; 1-超级管理员; 2-版主;
     */
    private Integer type;

    /**
     * 0-未激活; 1-已激活;
     */
    private Integer status;

    private String headerUrl;

    private Date createTime;

    private Date updateTime;

    private String token;

}
