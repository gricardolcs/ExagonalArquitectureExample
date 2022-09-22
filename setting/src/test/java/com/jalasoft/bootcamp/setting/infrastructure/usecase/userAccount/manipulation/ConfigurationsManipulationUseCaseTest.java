package com.jalasoft.bootcamp.setting.infrastructure.usecase.userAccount.manipulation;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

import com.jalasoft.bootcamp.becommon.domain.exceptions.EntityNotFoundException;
import com.jalasoft.bootcamp.becommon.domain.exceptions.PasswordException;
import com.jalasoft.bootcamp.becommon.domain.exceptions.SettingsException;
import com.jalasoft.bootcamp.setting.domain.userAccount.UserAccount;
import com.jalasoft.bootcamp.setting.domain.userAccount.UserAccountRepository;
import com.jalasoft.bootcamp.setting.infrastructure.usecase.configurations.manipulation.ConfigurationsManipulationUseCase;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ConfigurationsManipulationUseCaseTest
{
    @Mock
    private UserAccountRepository userAccountRepository;

    @InjectMocks
    private ConfigurationsManipulationUseCase configurationsManipulationUseCase;

    private Optional<UserAccount> userAccountOptional;

    private UserAccount userAccount;

    private Long userAccountSettingsId;

    @BeforeEach
    void setup()
    {
        userAccount = new UserAccount(1111111111111111111L, 60, 5);
        userAccountOptional = Optional.of(userAccount);
        userAccountSettingsId = 1111111111111111111L;
    }

    @Test
    public void testUpdateExpirationDays()
    {
        UserAccount userAccountModified =
            new UserAccount(1111111111111111111L, 50, 4);
        Mockito.when(userAccountRepository.findById(userAccountSettingsId))
            .thenReturn(userAccountOptional);
        Mockito.when(userAccountRepository.save(userAccount)).thenReturn(userAccountModified);
        configurationsManipulationUseCase.updateAccountExpirationDate(
            userAccountSettingsId,
            userAccountModified.getPasswordExpirationInDays());
        verify(userAccountRepository).save(userAccount);
    }

    @Test
    public void testUpdateBlockAttempts()
    {
        UserAccount userAccountModified =
            new UserAccount(1111111111111111111L, 60, 4);
        Mockito.when(userAccountRepository.findById(userAccountSettingsId))
            .thenReturn(userAccountOptional);
        Mockito.when(userAccountRepository.save(userAccount)).thenReturn(userAccountModified);
        configurationsManipulationUseCase.updateAccountBlockAttempts(
            userAccountSettingsId,
            userAccountModified.getBlockAttempts());
        verify(userAccountRepository).save(userAccount);
    }

    @Test
    public void testDefaultValuesNotInserted()
    {
        assertThrows(
            EntityNotFoundException.class,
            () -> configurationsManipulationUseCase.updateAccountExpirationDate(
                userAccountSettingsId,
                15));
        assertThrows(
            EntityNotFoundException.class,
            () -> configurationsManipulationUseCase.updateAccountBlockAttempts(
                userAccountSettingsId,5));
    }
}