package com.jalasoft.bootcamp.authentication.domain.user.repository;

import javax.annotation.PostConstruct;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisJsonRepository implements RedisRepository
{
    private static final String GROUP = "CRM";
    private final RedisTemplate<String, String> redisTemplate;
    private HashOperations hashOperations;

    public RedisJsonRepository(final RedisTemplate<String, String> redisTemplate)
    {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init()
    {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(final String key, final String value)
    {
        hashOperations.put(GROUP, key, value);
    }

    @Override
    public String findByKey(final String key)
    {
        return (String) hashOperations.get(GROUP, key);
    }

    @Override
    public void delete(final String key)
    {
        hashOperations.delete(GROUP, key);
    }
}
