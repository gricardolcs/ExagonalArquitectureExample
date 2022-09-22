package com.jalasoft.bootcamp.setting.infrastructure.usecase.configurations.manipulation;

import com.jalasoft.bootcamp.becommon.domain.ddd.IsUseCase;
import com.jalasoft.bootcamp.becommon.domain.exceptions.EntityNotFoundException;
import com.jalasoft.bootcamp.becommon.domain.exceptions.SettingsException;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.Field;
import com.jalasoft.bootcamp.setting.domain.userAccount.UserAccount;
import com.jalasoft.bootcamp.setting.domain.userAccount.UserAccountRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@IsUseCase
@Service
public class ConfigurationsManipulationUseCase
{
    private final UserAccountRepository userAccountRepository;

    @Autowired
    public ConfigurationsManipulationUseCase(UserAccountRepository userAccountRepository)
    {
        this.userAccountRepository = userAccountRepository;
    }

    public void updateAccountExpirationDate(Long id, int passwordExpirationInDays)
    {
        UserAccount userAccount = userAccountRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(
                Field.ID,
                ErrorsUtil.getDescription(
                    ErrorsUtil.ERROR_DESCRIPTION_DEFAULT_ACCOUNT_SETTING_NOT_FOUND,
                    Field.ID,
                    String.valueOf(id))));
        userAccount.setPasswordExpirationInDays(passwordExpirationInDays);
        userAccountRepository.save(userAccount);
    }

    public void updateAccountBlockAttempts(Long id, int blockAttempts)
    {
        UserAccount userAccount = userAccountRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(
                Field.ID,
                ErrorsUtil.getDescription(
                    ErrorsUtil.ERROR_DESCRIPTION_DEFAULT_ACCOUNT_SETTING_NOT_FOUND,
                    Field.ID,
                    String.valueOf(id))));
        userAccount.setBlockAttempts(blockAttempts);
        userAccountRepository.save(userAccount);
    }
}
