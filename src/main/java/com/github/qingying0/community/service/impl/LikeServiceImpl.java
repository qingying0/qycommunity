package com.github.qingying0.community.service.impl;

import com.github.qingying0.community.dao.RedisDao;
import com.github.qingying0.community.exception.CustomCode;
import com.github.qingying0.community.exception.CustomException;
import com.github.qingying0.community.service.ILikeService;
import com.github.qingying0.community.utils.HostHolder;
import com.github.qingying0.community.utils.RedisKeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements ILikeService {

    @Autowired
    private RedisDao redisDao;

    @Autowired
    private HostHolder hostHolder;

    @Override
    public void like(Long likeUserId, Integer entityType, Long entityId) {
        if(hostHolder.get() == null) {
            throw new CustomException(CustomCode.NO_LOGIN);
        }
        Long userId = hostHolder.get().getId();
        String likeEntityKey = RedisKeyUtils.getLikeEntity(entityType, entityId);
        boolean isLike = redisDao.sHasKey(likeEntityKey, userId);
        if(isLike) {
            redisDao.decr(RedisKeyUtils.getUserLike(likeUserId), 1L);
            redisDao.setRemove(likeEntityKey, userId);
        } else {
            redisDao.incr(RedisKeyUtils.getUserLike(likeUserId), 1L);
            redisDao.sSet(likeEntityKey, userId);
        }
    }

    @Override
    public Long getLikeCount(Integer entityType, Long entityId) {
        String likeEntityKey = RedisKeyUtils.getLikeEntity(entityType, entityId);
        return redisDao.sGetSetSize(likeEntityKey);
    }

    @Override
    public Integer getLikeStatus(Integer entityType, Long entityId) {
        if(hostHolder.get() == null) {
            return 0;
        }
        Long userId = hostHolder.get().getId();
        String likeEntityKey = RedisKeyUtils.getLikeEntity(entityType, entityId);
        return redisDao.sHasKey(likeEntityKey, userId) ? 1 : 0; // 1 为点赞 0为未赞
    }

    @Override
    public Integer getUserLikeCount(Long userId) {
        Integer likeCount = (Integer)redisDao.get(RedisKeyUtils.getUserLike(userId));
        return likeCount == null ? 0 : likeCount;
    }
}
