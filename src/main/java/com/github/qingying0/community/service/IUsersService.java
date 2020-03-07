package com.github.qingying0.community.service;

import com.github.qingying0.community.bo.UserLoginBo;
import com.github.qingying0.community.dto.UserDTO;
import com.github.qingying0.community.entity.Users;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qingying0
 * @since 2020-03-02
 */
public interface IUsersService {

    UserDTO login(UserLoginBo user);

    void register(Users user);

    void activeAccount(Long userId, String activeCode);

    UserDTO getByUserId(Long userId);
}
