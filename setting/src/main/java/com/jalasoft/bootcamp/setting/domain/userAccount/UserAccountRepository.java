package com.jalasoft.bootcamp.setting.domain.userAccount;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends MongoRepository<UserAccount, Long>
{
}
