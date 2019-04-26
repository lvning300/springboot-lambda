package com.demo.cloud.user.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;


@SuppressWarnings("ALL")
@Slf4j
@Component
public class RedisLock {


    private static final String LOCKED = "LOCKED";

    /**
     * 默认请求锁的超时时间(ms 毫秒)
     */
    private static final long TIME_OUT = 1000 * 10;

    /**
     * 默认锁的有效时间(s)
     */
    public static final int EXPIRE = 80;

    /**
     * 锁的自动失效有效时间(s)
     */
    private int expireTime = EXPIRE;

    /**
     * 请求锁的超时时间(ms)
     */
    private long timeOut = TIME_OUT;

    /**
     * Redis管理模板
     */
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     *
     * @param key 锁标志对应的key
     * @return
     */
    public boolean lock(String key) {

        long nowTime = System.nanoTime();
        long timeout = timeOut * 1000000;

        final Random random = new Random();

        while ((System.nanoTime() - nowTime) < timeout) {

            if (redisTemplate.opsForValue().setIfAbsent(key, LOCKED)) {
                redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
                return true;
            }

            try {
                Thread.sleep(10, random.nextInt(50000));
            } catch (InterruptedException e) {
                log.error("## 获取分布式锁休眠被中断：", e);
            }
        }

        return false;

    }

    public boolean isLock(String key) {
        return redisTemplate.hasKey(key);
    }

    public void unlock(String key) {
        redisTemplate.delete(key);
    }

}
