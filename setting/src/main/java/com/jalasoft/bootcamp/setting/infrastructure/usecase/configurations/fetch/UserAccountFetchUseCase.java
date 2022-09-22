package com.jalasoft.bootcamp.setting.infrastructure.usecase.configurations.fetch;

import com.jalasoft.bootcamp.becommon.domain.ddd.IsUseCase;
import com.jalasoft.bootcamp.becommon.domain.exceptions.EntityNotFoundException;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.Field;
import com.jalasoft.bootcamp.setting.domain.userAccount.UserAccount;
import com.jalasoft.bootcamp.setting.domain.userAccount.UserAccountRepository;
import com.jalasoft.bootcamp.setting.infrastructure.dto.userAccount.UserAccountDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@IsUseCase
@Service
public class UserAccountFetchUseCase
{

    private final UserAccountRepository userAccountSettingsRepository;

    @Autowired
    public UserAccountFetchUseCase(final UserAccountRepository userAccountSettingsRepository)
    {
        this.userAccountSettingsRepository = userAccountSettingsRepository;
    }

    public UserAccountDTO fetchById(long id)
    {
        UserAccount userAccount = userAccountSettingsRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(
                Field.ID,
                ErrorsUtil.getDescription(ErrorsUtil.ERROR_DESCRIPTION_DEFAULT_ACCOUNT_SETTING_NOT_FOUND, Field.ID,
                    String.valueOf(id))));
        return new UserAccountDTO(userAccount);
    }
}
