package com.jalasoft.bootcamp.authentication.domain.user.repository;

public interface RedisRepository
{
    void save(String key, String value);

    String findByKey(String key);

    void delete(String key);
}
