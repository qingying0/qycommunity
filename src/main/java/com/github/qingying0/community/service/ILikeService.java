package com.github.qingying0.community.service;

public interface ILikeService {

    void like(Long likeUserId, Integer entityType, Long entityId);

    Long getLikeCount(Integer entityType, Long entityId);

    Integer getLikeStatus(Integer entityType, Long entityId);

    Integer getUserLikeCount(Long userId);
}
