package com.github.qingying0.community.utils;

/**
 * 生成redisKey的工具类
 */
public class RedisKeyUtils {

    public static final String SPLIT= ":";
    public static final String TOKEN = "token";
    public static final String LIKE = "like";
    public static final String USER_LIKE = "user:like";
    public static final String FOLLOWEE = "followee";
    public static final String FOLLOWER = "follower";
    public static final String QUESTION_SCORE = "question:score";

    public static String getTokenKey(String token) {
        return TOKEN + SPLIT + token;
    }

    // 存储某个回复点赞的user  --- set
    public static String getLikeEntity(Integer entityType, Long entityId) {
        return LIKE + SPLIT + entityType + SPLIT + entityId;
    }

    // 存储用户收到的赞   -------int
    public static String getUserLike(Long userId) {
        return USER_LIKE + SPLIT + userId;
    }

    // 存储用户关注的问题或者用户  ------zset(entityId, data)
    public static String getFolloweeKey(Integer entityType, Long userId) {
        return FOLLOWEE + SPLIT + userId + SPLIT + entityType;
    }

    // 存储关注实体的 user -----zset(userId, date)
    public static String getFollowerKey(Integer entityType, Long entityId) {
        return FOLLOWER + SPLIT + entityType + SPLIT + entityId;
    }

    public static String getQuestionScoreKey() {
        return QUESTION_SCORE ;
    }
}
