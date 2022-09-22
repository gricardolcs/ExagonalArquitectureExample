package com.jalasoft.bootcamp.setting.domain.userAccount;

import com.jalasoft.bootcamp.becommon.domain.ddd.IsAggregateRoot;
import com.jalasoft.bootcamp.becommon.domain.ddd.IsEntity;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;

@Getter
@Setter
@IsEntity
@IsAggregateRoot
public class UserAccount extends AbstractAggregateRoot<UserAccount>
{
    @Id
    private final Long id;
    private int passwordExpirationInDays;
    private int blockAttempts;

    @PersistenceConstructor
    public UserAccount(
        final Long id,
        final int passwordExpirationInDays,
        final int blockAttempts)
    {
        this.id = id;
        this.passwordExpirationInDays = passwordExpirationInDays;
        this.blockAttempts = blockAttempts;
    }
}
