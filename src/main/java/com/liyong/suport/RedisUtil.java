package com.liyong.suport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.jms.JMSException;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.CollectionUtils;

import io.lettuce.core.RedisException;

/**
 * @ClassName: RedisUtil
 * @Description: 缓存保存工具类
 * @author 
 * @date 2015年8月21日 上午9:41:08
 */

public class RedisUtil {

    private static RedisTemplate<String, Object> redisTemplate;

    private static RedisTemplate<String, Object> redisTemplateNoSer;

    /**
     * TODO: 初始化RedisTemplate
     * @throws Exception 
     */
    public static void initRedisTemplate(final RedisTemplate<String, Object> sprintRedisTemplate) throws RedisException {
    	if (null == sprintRedisTemplate) {
            throw new RedisException("未初始化redisTemplate！！");
        }
        redisTemplate = sprintRedisTemplate;

    }
    /**
     * TODO: 初始化RedisTemplate
     */
    public static void initRedisTemplateNoSer(final RedisTemplate<String, Object> sprintRedisTemplate) {
    	redisTemplateNoSer = sprintRedisTemplate;
    }

    /**
     * @param <T>
     * @description 获取 redis中的对象
     * @param
     * @return void
     * @throws
     */
    public static <T> T redisQueryObject(final RedisTemplate<String, Object> redisTemplate, final String key) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        return (T) ops.get(key);
    }

    /**
     * @description 删除redis中指定的key
     * @param
     * @return void
     * @throws
     */
    public static void redisDeleteKey(final RedisTemplate<String, Object> redisTemplate, final String key) {
        redisTemplate.delete(key);
    }
    
    /**
     * @description 保存对象到redis
     * @param key
     * @return object
     * @throws
     */
    public static void redisSaveObject(final RedisTemplate<String, Object> redisTemplate, final String key,
            final Object object) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        ops.getAndSet(key, object);
    }
    
    

    /**
     * @description 保存对象到redis
     * @param key
     * @return object
     * @throws
     */
    public static void redisSaveObject(final RedisTemplate<String, Object> redisTemplate, final String key,
            final Object object, int time) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        ops.set(key, object, time, TimeUnit.MINUTES);
    }

    /**
     * @description redis保存数据到list
     * @param
     * @return void
     * @throws
     */
    public static void redisSaveList(final RedisTemplate<String, Object> redisTemplate, final String key,
            final Object object, int count) {
        ListOperations<String, Object> ops = redisTemplate.opsForList();
        ops.leftPush(key, object);
        if (0 < count)
            ops.trim(key, 0, count);
    }

    /**
     * @param <T>
     * @description 获取 redis中的list对象
     * @param
     * @return
     * @throws
     */
    public static <T> List<T> redisQueryList(final RedisTemplate<String, Object> redisTemplate, final String key,
            Class<T> claxx) {
        ListOperations<String, Object> ops = redisTemplate.opsForList();
        List<Object> tempList = ops.range(key, 0, -1);
        if (CollectionUtils.isEmpty(tempList)) {
            return null;
        }

        List<T> resultList = new ArrayList<T>();
        for (Object serl : tempList) {
            resultList.add(claxx.cast(serl));
        }
        tempList.clear();
        return resultList;
    }

    /**
     * @description redis 删除列表中的对象
     * @param
     * @return void
     * @throws
     */
    public static void redisDelListValue(final RedisTemplate<String, Object> redisTemplate, final String key,
            final Serializable value) {
        ListOperations<String, Object> ops = redisTemplate.opsForList();
        ops.remove(key, 0, value);
    }

    /**
     * @param <T>
     * @description 获取 redis中的对象
     * @param
     * @return void
     * @throws
     */
    public static <T> T redisQueryObject(final String key) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        return (T) ops.get(key);
    }

    /**
     * @description 删除redis中指定的key
     * @param
     * @return void
     * @throws
     */
    public static void redisDeleteKey(final String key) {
        redisTemplate.delete(key);
    }
    
    /**
     * 左模糊删除redis key
     * @param key
     */
    public static void redisDeleteKeyLikeLeft(final String key) {
       	Set<String> keys = redisTemplate.keys(key + "*");
       	if(keys.size()>0){
       		Iterator<String> it = keys.iterator();
       		while(it.hasNext()){
       			System.out.println("删除的keys。。。。。。。。"+it.next());
       		}
       	    redisTemplate.delete(keys);
       	}       
    }

    /**
     * @description 保存对象到redis
     * @param key
     * @return object
     * @throws
     */
    public static void redisSaveObject(final String key, final Object object) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        ops.getAndSet(key, object);
    }

    /**
     * @description 保存对象到redis
     * @param key
     * @return object
     * @throws
     */
    public static void redisSaveObjectNoSer(final String key, final Object object) {
        ValueOperations<String, Object> ops = redisTemplateNoSer.opsForValue();
        ops.getAndSet(key, object);
    }

    /**
     * @description 保存对象到redis
     * @param key
     * @return object
     * @throws
     */
    public static void redisSaveObject(final String key, final Object object, int time) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        ops.set(key, object, time, TimeUnit.MINUTES);
    }
    
    public static void redisSaveObjectNoSer(final String key, final Object object, int time) {
        ValueOperations<String, Object> ops = redisTemplateNoSer.opsForValue();
        ops.set(key, object, time, TimeUnit.MINUTES);
    }

    /**
     * @description redis保存数据到list
     * @param
     * @return void
     * @throws
     */
    public static void redisSaveList(final String key, final Object object, int count) {
        ListOperations<String, Object> ops = redisTemplate.opsForList();
        ops.leftPush(key, object);
        if (0 < count)
            ops.trim(key, 0, count);
    }

    /**
     * @param <T>
     * @description 获取 redis中的list对象
     * @param
     * @return
     * @throws
     */
    public static <T> List<T> redisQueryList(final String key, Class<T> claxx) {
        ListOperations<String, Object> ops = redisTemplate.opsForList();
        List<Object> tempList = ops.range(key, 0, -1);
        if (CollectionUtils.isEmpty(tempList)) {
            return null;
        }

        List<T> resultList = new ArrayList<T>();
        for (Object serl : tempList) {
            resultList.add(claxx.cast(serl));
        }
        tempList.clear();
        return resultList;
    }
    


    /**
     * @description redis 删除列表中的对象
     * @param
     * @return void
     * @throws
     */
    public static void redisDelListValue(final String key, final Serializable value) {
        ListOperations<String, Object> ops = redisTemplate.opsForList();
        ops.remove(key, 0, value);
    }

}
