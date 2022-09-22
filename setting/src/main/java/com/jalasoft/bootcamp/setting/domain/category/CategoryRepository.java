package com.jalasoft.bootcamp.setting.domain.category;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, Long>
{
    boolean existsByNameIgnoreCase(String name);
}
