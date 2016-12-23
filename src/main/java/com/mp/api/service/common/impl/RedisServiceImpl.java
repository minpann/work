package com.mp.api.service.common.impl;

import com.alibaba.fastjson.JSON;
import com.mp.api.service.common.RedisService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 常用redis操作的实现类 Created by csma on 5/8/16.
 */
@Service
public class RedisServiceImpl implements RedisService {
    private final static Logger LOGGER = LoggerFactory.getLogger(RedisServiceImpl.class);
    @Resource(name = "redisTemplate")
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 移除KEY
     * 
     * @param key 移除某类KEY
     * @return true or false
     */
    @Override
    public boolean removeKey(String key) {
        if (keyValid(key)) {
            redisTemplate.delete(key);
            return true;
        }

        return false;
    }

    /**
     * 检查ＫＥＹ是否有效，前提是不能为空串
     * 
     * @param key key
     * @return true or false
     */
    private boolean keyValid(String key) {
        if (StringUtils.isBlank(key)) {
            LOGGER.error("The key {} is not invalid !", key);
            return false;
        }
        return true;
    }

    /**
     * 判断KEY和VALUE是否都有效
     * 
     * @param key key
     * @param value value
     * @return true or false
     */
    private boolean keyAndValueValid(String key, String value) {
        if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
            LOGGER.error("The key {} or value {} is not invalid !", key, value);
            return false;
        }
        return true;
    }

    /**
     * 获取值类型
     * 
     * @return string type
     */
    private ValueOperations<String, String> getValue() {
        return redisTemplate.opsForValue();
    }

    /**
     * string 类型的key 操作实现
     * 
     * @param key key
     * @return 结果
     */
    @Override
    public String getForString(String key) {

        String value = null;
        if (keyValid(key)) {
            value = getValue().get(key);

            if (value == null) {
                LOGGER.warn("The value of key {} is null!", key);
            }
        }
        return value;
    }

    /**
     * string 类型的设置方法
     * 
     * @param key key
     * @param value value
     * @return 是否操作成功
     */
    @Override
    public boolean setForString(String key, String value) {

        if (keyValid(key) && value != null) {
            if (StringUtils.isBlank(value)) {
                LOGGER.warn("The value of key {} is blank !", key);
            }
            getValue().set(key, value);
            return true;
        }
        return false;
    }

    /**
     * 有序集合的存储
     * 
     * @param key key
     * @param value value
     * @param score 排序值
     * @return 是否操作成功
     */
    @Override
    public boolean setForZSet(String key, String value, double score) {
        if (keyValid(key) && value != null) {
            if (StringUtils.isBlank(value)) {
                LOGGER.warn("The value of key {} is blank !", key);
            }
            getZSet().add(key, value, score);
            return true;
        }
        return false;
    }

    /**
     * 获取有序集合的某个值的score
     * 
     * @param key key
     * @param value value
     * @return 对应的score
     */
    @Override
    public Double getScoreForZSet(String key, String value) {
        if (keyAndValueValid(key, value)) {
            return getZSet().score(key, value);
        }
        return null;
    }

    /**
     * 有序集合， 移除指定KEY
     * 
     * @param key key
     * @param values values
     * @return 是否操作成功
     */
    @Override
    public boolean removeForZSet(String key, String... values) {
        if (keyValid(key) && values != null) {
            if (values.length == 0) {
                LOGGER.warn("The values to be removed from the key {} is empty !", key);
            }
            LOGGER.debug("removeForZSet key:{},values:{}", key, Arrays.toString(values));
            getZSet().remove(key, (Object[]) values);
            return true;
        }
        return false;
    }

    /**
     * 有序集合获取指定范围内的数据
     * 
     * @param key key
     * @param start start
     * @param end end
     * @return 结果, 确保结果不为NULL对象，便于调用方处理
     */
    @Override
    public Set<String> getForZSet(String key, long start, long end) {

        Set<String> value = Collections.emptySet();

        if (keyValid(key)) {
            value = getZSet().range(key, start, end);

            if (value == null || value.isEmpty()) {
                LOGGER.warn("The value of key {} , range {}--{} is null or empty!", key, start, end);
                return Collections.emptySet();
            }
        }
        return value;
    }

    /**
     * 有序集合 获取所有的数据
     * 
     * @param key key
     * @return value
     */
    @Override
    public Set<String> getForZSet(String key) {
        return getForZSet(key, 0, -1);
    }

    /**
     * ZSet 操作对象
     * 
     * @return zset
     */
    private ZSetOperations<String, String> getZSet() {
        return redisTemplate.opsForZSet();
    }

    /**
     * 有序集合获取获取指定KEY，指定值的排名
     * 
     * @param key key
     * @param value value
     * @return 结果, 确保结果不为NULL对象，便于调用方处理
     */
    @Override
    public Long getRankForZSet(String key, String value) {
        Long rank = -1L;
        if (keyValid(key) && value != null) {
            rank = getZSet().rank(key, value);

            if (rank == null) {
                LOGGER.warn("The rank for value {} of key {} can't be fetched !", value, key);
                rank = -1L;
            }
        }
        return rank;
    }

    /**
     * 有序集合获取指定范围内的数据
     * 
     * @param key key
     * @param start start
     * @param end end
     * @return 结果, 确保结果不为NULL对象，便于调用方处理
     */
    @Override
    public Set<String> getReverseForZSet(String key, long start, long end) {

        LOGGER.debug("getReverseForZset    key:{},start:{},end:{}", key, start, end);
        Set<String> value = Collections.emptySet();

        if (keyValid(key)) {
            value = getZSet().reverseRange(key, start, end);

            if (value == null || value.isEmpty()) {
                LOGGER.warn("The reverse value of key {} , range {}--{} is null or empty!", key,
                        start, end);
                return Collections.emptySet();
            }
        }
        LOGGER.debug("getReverseForZset value:{}", value);
        return value;
    }

    /**
     * 有序集合获取指定范围内的数据
     * 
     * @param key key
     * @param start start
     * @param end end
     * @return 结果, 确保结果不为NULL对象，便于调用方处理
     */
    @Override
    public Set<ZSetOperations.TypedTuple<String>> getReverseForZSetWithScore(String key,
                                                                             long start, long end) {

        LOGGER.debug("getReverseForZSetWithScore    key:{},start:{},end:{}", key, start, end);
        Set<ZSetOperations.TypedTuple<String>> value = Collections.emptySet();

        if (keyValid(key)) {
            value = getZSet().reverseRangeWithScores(key, start, end);

            if (value == null || value.isEmpty()) {
                LOGGER.warn("The reverse value of key {} , range {}--{} is null or empty!", key,
                        start, end);
                return Collections.emptySet();
            }
        }
        LOGGER.debug("getReverseForZSetWithScore value:{}", value);
        return value;
    }

    /**
     * 有序集合获取指定范围内的数据
     * 
     * @param key key
     * @param min min
     * @param max max
     * @return 结果, 确保结果不为NULL对象，便于调用方处理
     */
    @Override
    public Set<ZSetOperations.TypedTuple<String>> reverseRangeByScoreWithScores(String key,
                                                                                double min, double max) {

        LOGGER.debug("rangeByScoreWithScores    key:{},min:{},max:{}", key, min, max);
        Set<ZSetOperations.TypedTuple<String>> value = Collections.emptySet();

        if (keyValid(key)) {
            value = getZSet().reverseRangeByScoreWithScores(key, min, max);

            if (value == null || value.isEmpty()) {
                LOGGER.warn("The range value of key {} , range {}--{} is null or empty!", key, min,
                        max);
                return Collections.emptySet();
            }
        }
        LOGGER.debug("rangeByScoreWithScores value:{}", value);
        return value;
    }

    @Override
    public Long countZSetByScore(String key, double min, double max) {
        LOGGER.debug("countZSetByScore key:{},min:{},max:{}", key, min, max);
        Long count = -1L;
        if (keyValid(key)) {
            count = getZSet().count(key, min, max);
        }
        LOGGER.debug("rangeByScoreWithScores value:{}", count);
        return count;
    }

    /**
     * 有序集合 获取所有的数据
     * 
     * @param key key
     * @return value
     */
    @Override
    public Set<String> getReverseForZSet(String key) {
        return getReverseForZSet(key, 0, -1);
    }

    /**
     * 有序集合获取获取指定KEY，指定值的排名
     * 
     * @param key key
     * @param value value
     * @return 结果, 确保结果不为NULL对象，便于调用方处理
     */
    @Override
    public Long getReverseRankForZSet(String key, String value) {
        Long rank = -1L;
        if (keyValid(key) && value != null) {
            rank = getZSet().reverseRank(key, value);

            if (rank == null) {
                LOGGER.warn("The reverse rank for value {} of key {} can't be fetched !", key,
                        value);
                rank = -1L;
            }
        }
        LOGGER.debug("getReverseRankForZSet key:{},value:{},rank:{}", key, value, rank);
        return rank;
    }

    @Override
    public Long removeRangeByScore(String key, double min, double max) {
        Long count = -1L;
        if (keyValid(key)) {
            count = getZSet().removeRangeByScore(key, min, max);
        }
        LOGGER.debug("removeRangeByScore key:{},min:{},max:{}", key, min, max);
        return count;
    }

    /**
     * hash 类型获取获取指定KEY的所有内容
     * 
     * @param key key
     * @return 结果, 确保结果不为NULL对象，便于调用方处理
     */
    @Override
    public Map<String, String> getAllForHash(String key) {
        Map<String, String> resultMap = Collections.emptyMap();
        if (keyValid(key)) {
            resultMap = getHash().entries(key);
            if (resultMap == null || resultMap.isEmpty()) {
                LOGGER.warn("The items  value of key {} can't be fetched !", key);
                resultMap = Collections.emptyMap();
            }
        }
        return resultMap;
    }

    /**
     * hash, 获取值，这个仅用于单个KEY的获取
     * 
     * @param key key
     * @param hashKey hashKey
     * @return value value
     */
    @Override
    public String getForHash(String key, String hashKey) {
        if (keyValid(key)) {
            if (StringUtils.isNotBlank(hashKey)) {
                return getHash().get(key, hashKey);
            } else {
                LOGGER.warn("The hashKey is empty !, hashKey:{}", hashKey);
            }
        }

        return null;
    }

    /**
     * hash， 设置值， 一般情况，如果要添加多个的话，采用这种方式比较合适，对于单个，则采用另一个方法
     * 
     * @param key key
     * @param contentMap 内容
     */
    @Override
    public void putAllForHash(String key, Map<String, String> contentMap) {
        if (keyValid(key) && contentMap != null && !contentMap.isEmpty()) {
            getHash().putAll(key, contentMap);
        } else {
            LOGGER.warn("The contentMap is empty !");
        }
    }

    /**
     * hash, 设置值，这个仅用于单个KEY的更新
     * 
     * @param key key
     * @param hashKey hashKey
     * @param value value
     */
    @Override
    public void putForHash(String key, String hashKey, String value) {
        if (keyValid(key)) {
            if (StringUtils.isNotBlank(hashKey) && StringUtils.isNotBlank(value)) {
                getHash().put(key, hashKey, value);
            } else {
                LOGGER.warn("The hashKey or the value is empty !, hashKey:{}, value:{}", hashKey,
                        value);
            }
        }
    }

    /**
     * 判断一个SET中是否有指定元素
     * 
     * @param key key
     * @param member member
     * @return true or false
     */
    @Override
    public boolean hasMemberForSet(String key, String member) {

        if (keyAndValueValid(key, member)) {
            return redisTemplate.opsForSet().isMember(key, member);
        }
        return false;
    }

    /**
     * 将元素添加到Set里
     * 
     * @param key key
     * @param member member
     * @return true or false
     */
    @Override
    public boolean addMemberForSet(String key, String member) {
        return keyAndValueValid(key, member) && redisTemplate.opsForSet().add(key, member) > 0;
    }

    /**
     * 将元素从Set里移除
     * 
     * @param key key
     * @param member member
     * @return true or false
     */
    @Override
    public boolean removeMemberForSet(String key, String member) {
        return keyAndValueValid(key, member) && redisTemplate.opsForSet().remove(key, member) > 0;
    }

    /**
     * 将item添加到list里
     * 
     * @param key key
     * @param item item
     * @return true or false
     */
    @Override
    public boolean pushForList(String key, String item) {

        return keyAndValueValid(key, item) && redisTemplate.opsForList().rightPush(key, item) > 0;
    }

    /**
     * 从list中pull一个元素
     * 
     * @param key key
     * @return item
     */
    @Override
    public String pullForList(String key) {
        if (keyValid(key)) {
            return redisTemplate.opsForList().leftPop(key);
        }
        return null;
    }

    /**
     * ZSet 操作对象
     * 
     * @return zset
     */
    private HashOperations<String, String, String> getHash() {
        return redisTemplate.opsForHash();
    }

    @Override
    public Long getSizeForSet(String key) {
        Long rank = -1L;
        if (keyValid(key)) {
            rank = getZSet().size(key);
        }
        LOGGER.debug("getSizeForSet key:{},value:{}", key, rank);
        return rank;
    }

    @Override
    public boolean setForZSets(String key, Set<ZSetOperations.TypedTuple<String>> values) {
        if (keyValid(key) && CollectionUtils.isNotEmpty(values)) {
            getZSet().add(key, values);
            return true;
        }
        return false;
    }

    @Override
    public boolean setExpire(String key, long timeout) {
        if (keyValid(key)) {
            redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
            return true;
        }
        return false;
    }

    @Override
    public boolean setExpire(String key, long timeout,TimeUnit unit) {
        if (keyValid(key)) {
            redisTemplate.expire(key, timeout, unit);
            return true;
        }
        return false;
    }

    @Override
    public boolean setForList(String key, List<String> values) {
        if (keyValid(key) && values != null) {
            if (values.isEmpty()) {
                LOGGER.warn("The value of key {} is blank !", key);
            }
            this.redisTemplate.opsForList().leftPushAll(key, values);
            return true;
        }
        return false;
    }

    /**
     * 获取有序集合的某个值的score
     * 
     * @param key key
     * @return 对应的score
     */
    @Override
    public List<String> getForList(String key) {
        if (keyValid(key)) {
            long size = redisTemplate.opsForList().size(key);
            return redisTemplate.opsForList().range(key, 0L, size);
        }
        return Collections.emptyList();
    }

    @Override
    public <T> T getObjectForHash(String key, String field, Class<T> clazz) {
        String value = getForHash(key, field);
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return JSON.parseObject(value, clazz);
    }
}
