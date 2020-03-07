package com.github.qingying0.community.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class Users {
    @Id
    private Long id;

    private String username;

    private String password;

    @JsonIgnore
    private String salt;

    private String email;

    /**
     * 0-普通用户; 1-超级管理员; 2-版主;
     */
    private Integer type;

    /**
     * 0-未激活; 1-已激活;
     */
    private Integer status;

    @Column(name = "activation_code")
    private String activationCode;

    @Column(name = "header_url")
    private String headerUrl;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

}
