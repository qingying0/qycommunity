package com.github.qingying0.community.service;

import com.github.qingying0.community.dto.UserDTO;

import java.util.List;

public interface IFollowService {

    void followEntity(Integer entityType, Long entityId);

    void unFollowEntity(Integer entityType, Long entityId);

    Boolean hasFollowed(Integer entityType, Long entityId);

    Long getFolloweeCount(Integer entityType, Long entityId);

    Long getFollowerCount(Integer entityType, Long userId);

    List<UserDTO> getUserFollowees(Long userId);

    List<UserDTO> getUserFollowers(Long userId);

}
