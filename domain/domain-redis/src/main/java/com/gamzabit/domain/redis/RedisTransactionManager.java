package com.gamzabit.domain.redis;

import java.util.function.Consumer;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class RedisTransactionManager {

    private final RedisTemplate<String, Object> redisTemplate;

    public void invokeLock(Runnable runnable, String key) {
        redisTemplate.execute(new SessionCallback<>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> operations) throws DataAccessException {
                operations.watch((K) key);
                operations.multi();
                try {
                    runnable.run();
                } catch (Throwable e) {
                    log.error("Redis transaction error: ", e);
                    operations.discard();
                    return null;
                }
                operations.exec();
                return null;
            }
        });
    }

    public void invoke(Runnable runnable) {
        redisTemplate.execute(new SessionCallback<Object>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> operations) throws DataAccessException {
                operations.multi();
                try {
                    runnable.run();
                } catch (Throwable e) {
                    log.error("Redis transaction error: ", e);
                    operations.discard();
                    return null;
                }
                operations.exec();
                return null;
            }
        });
    }
}
