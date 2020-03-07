package com.github.qingying0.community.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserBo {

    @NotEmpty(message = "用户名不能为空")
    @Size(min = 3, message = "用户名长度太短")
    private String username;

    @NotEmpty(message = "密码不能为空")
    @Size(min = 3, message = "密码长度太短")
    private String password;

    @Email(message = "邮箱格式错误")
    @NotEmpty(message = "邮箱不能为空")
    private String email;

    @NotEmpty(message = "密码不能为空")
    @Size(min = 3, message = "密码长度太短")
    private String confirmPassword;
}
