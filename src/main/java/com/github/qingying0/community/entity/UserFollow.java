package com.github.qingying0.community.entity;

import javax.persistence.*;

@Table(name = "user_follow")
public class UserFollow {
    @Id
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "follow_user_id")
    private Long followUserId;

    private Boolean status;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return follow_user_id
     */
    public Long getFollowUserId() {
        return followUserId;
    }

    /**
     * @param followUserId
     */
    public void setFollowUserId(Long followUserId) {
        this.followUserId = followUserId;
    }

    /**
     * @return status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }
}