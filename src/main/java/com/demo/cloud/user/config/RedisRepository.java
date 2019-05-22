package com.demo.cloud.user.config;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public class RedisRepository {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 切换redis数据库索引
     *
     * @param dbIndex
     */
    public void switchDB(int dbIndex) {
        RedisConnectionFactory connectionFactory = redisTemplate.getConnectionFactory();
        if (connectionFactory instanceof LettuceConnectionFactory) {
            LettuceConnectionFactory lcf = (LettuceConnectionFactory) connectionFactory;
            if (lcf != null) {
                lcf.setDatabase(dbIndex);
                redisTemplate.setConnectionFactory(lcf);
            }
        } else if (connectionFactory instanceof JedisConnectionFactory) {
            ((JedisConnectionFactory) connectionFactory).setDatabase(dbIndex);
        }

    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public String getJSONString(final String key) {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();

        String result = operations.get(key);

        // JSON.parseObject(result.toString(), T.class);
        return result;
    }

    /**
     * 读取缓存，将缓存中的JSON字符串，反序列化为type指定的类型。
     * <p>
     * 仅针对单普通JAVA实例数据，对于集合型数据不支持。对于集合型数据，可以先getJSONString得到JSON字符串，
     * 然后自行根据实际情况反序列化为集合。
     *
     * @param key
     * @return
     */
    public <T> T get(final String key, Class<T> type) {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        String result = operations.get(key);
        return JSON.parseObject(result.toString(), type);
    }

    /**
     * 读取缓存，返回泛型指定的类型值，只对基本数据类型有效，比如int, boolean等,
     * 其他类型会被反序列化成一个JSONObject（RedisConfig中配置使用了阿里的FastJSON）
     *
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T get(final String key) {
        T result = null;
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();

        result = (T) operations.get(key);
        // JSON.parseObject(result.toString(), T.class);
        return result;
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 查找redis中所有符合条件的key
     *
     * @param key
     * @return
     */
    public Set<String> keys(final String key) {
        return redisTemplate.keys(key);
    }

    //查询list的长度
    public Long llen(final String key) {
        return redisTemplate.opsForList().size(key);
    }

    //查询list的值
    public List<Object> lrange(String storeKey, int i, Long count) {
        return redisTemplate.opsForList().range(storeKey, i, count);
    }

    public void setHash(String storeKey,Object hasKey, Object value) {

        stringRedisTemplate.opsForHash().put(storeKey, hasKey, value);
    }

    public void setMap2Hash(String storeKey, Map map) {
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        redisTemplate.opsForHash().putAll(storeKey,map);
    }

}
