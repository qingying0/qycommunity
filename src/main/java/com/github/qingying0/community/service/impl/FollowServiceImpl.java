package com.github.qingying0.community.service.impl;

import com.github.qingying0.community.dao.RedisDao;
import com.github.qingying0.community.dto.UserDTO;
import com.github.qingying0.community.exception.CustomCode;
import com.github.qingying0.community.exception.CustomException;
import com.github.qingying0.community.service.IFollowService;
import com.github.qingying0.community.service.IUsersService;
import com.github.qingying0.community.utils.Constant;
import com.github.qingying0.community.utils.HostHolder;
import com.github.qingying0.community.utils.RedisKeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
public class FollowServiceImpl implements IFollowService {

    @Autowired
    private RedisDao redisDao;

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private IUsersService usersService;

    @Override
    public void followEntity(Integer entityType, Long entityId) {
        String followeeKey = RedisKeyUtils.getFolloweeKey(entityType, hostHolder.get().getId());
        String followerKey = RedisKeyUtils.getFollowerKey(entityType, entityId);
        redisDao.zsSet(followeeKey, entityId, System.currentTimeMillis());
        redisDao.zsSet(followerKey, hostHolder.get().getId(), System.currentTimeMillis());
    }

    @Override
    public void unFollowEntity(Integer entityType, Long entityId) {
        String followeeKey = RedisKeyUtils.getFolloweeKey(entityType, hostHolder.get().getId());
        String followerKey = RedisKeyUtils.getFollowerKey(entityType, entityId);
        redisDao.zsRemove(followeeKey, entityId);
        redisDao.zsRemove(followerKey, hostHolder.get().getId());
    }

    // 用户关注的实体中是否有这个
    @Override
    public Boolean hasFollowed(Integer entityType, Long entityId) {
        String followerKey = RedisKeyUtils.getFollowerKey(entityType, entityId);
        return redisDao.zsHasItem(followerKey, hostHolder.get().getId());
    }

    // 用户关注的问题/用户的数量
    @Override
    public Long getFolloweeCount(Integer entityType, Long entityId) {
        String followerKey = RedisKeyUtils.getFolloweeKey(entityType, entityId);
        return redisDao.zsSize(followerKey);
    }

    // 关注该用户或者问题的数量
    @Override
    public Long getFollowerCount(Integer entityType, Long entityId) {
        String followerKey = RedisKeyUtils.getFollowerKey(entityType, entityId);
        return redisDao.zsSize(followerKey);
    }

    // 关注的人详情
    @Override
    public List<UserDTO> getUserFollowees(Long userId) {
        String followeeKey = RedisKeyUtils.getFolloweeKey(Constant.ENTITY_TYPE_USER, userId);
        Set<Object> targets = redisDao.zsGetRange(followeeKey);
        return getUserBySet(targets);
    }

    // 粉丝的详情
    @Override
    public List<UserDTO> getUserFollowers(Long userId) {
        String followerKey = RedisKeyUtils.getFollowerKey(Constant.ENTITY_TYPE_USER, userId);
        Set<Object> targets = redisDao.zsGetRange(followerKey);
        return getUserBySet(targets);
    }

    public List<UserDTO> getUserBySet(Set targets) {
        List<UserDTO> listUser = new LinkedList<>();
        for(Object obj : targets) {
            Long targetId = Long.valueOf(obj.toString());
            listUser.add(usersService.getByUserId(targetId));
        }
        return listUser;
    }
}
