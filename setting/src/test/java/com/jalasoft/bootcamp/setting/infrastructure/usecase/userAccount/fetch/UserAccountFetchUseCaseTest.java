package com.jalasoft.bootcamp.setting.infrastructure.usecase.userAccount.fetch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.jalasoft.bootcamp.setting.domain.userAccount.UserAccount;
import com.jalasoft.bootcamp.setting.domain.userAccount.UserAccountRepository;
import com.jalasoft.bootcamp.setting.infrastructure.dto.userAccount.UserAccountDTO;
import com.jalasoft.bootcamp.setting.infrastructure.usecase.configurations.fetch.UserAccountFetchUseCase;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserAccountFetchUseCaseTest
{
    @Mock
    private UserAccountRepository userAccountRepository;

    @InjectMocks
    private UserAccountFetchUseCase userAccountFetchUseCase;

    private UserAccount userAccount;

    private Long userAccountSettingsId;

    private Optional<UserAccount> userAccountOptional;

    @BeforeEach
    void setUp()
    {
        userAccount = new UserAccount(-1111111111111111111L, 60, 5);
        userAccountOptional = Optional.of(userAccount);
        userAccountSettingsId = 1111111111111111111L;

    }

    @Test
    public void testFetchUserAccountById()
    {
        when(userAccountRepository.findById(userAccountSettingsId)).thenReturn(userAccountOptional);
        UserAccountDTO expectedUserAccounts = userAccountFetchUseCase.fetchById(userAccountSettingsId);
        assertEquals(
            userAccount.getBlockAttempts(),
            expectedUserAccounts.getBlockAttempts());
        assertEquals(
            userAccount.getPasswordExpirationInDays(),
            expectedUserAccounts.getPasswordExpirationInDays());
    }
}
