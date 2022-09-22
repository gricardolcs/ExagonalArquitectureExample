package com.jalasoft.bootcamp.setting.infrastructure.controller.user.configurations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.jalasoft.bootcamp.setting.infrastructure.dto.userAccount.UserAccountDTO;
import com.jalasoft.bootcamp.setting.infrastructure.usecase.configurations.fetch.UserAccountFetchUseCase;
import com.jalasoft.bootcamp.setting.infrastructure.usecase.configurations.manipulation.ConfigurationsManipulationUseCase;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class UserConfigurationControllerTest
{
    @Mock
    private ConfigurationsManipulationUseCase configurationsManipulationUseCase;

    @Mock
    private UserAccountFetchUseCase userAccountFetchUseCase;

    @InjectMocks
    private UserConfigurationController userConfigurationController;

    private UserAccountDTO userAccountDTO;

    private Long userAccountSettingsId;

    @BeforeEach
    void setup()
    {
        userAccountDTO = new UserAccountDTO(60, 5);
        userAccountSettingsId = 1111111111111111111L;
    }

    @Test
    public void testFetchUserAccountSettings()
    {

        when(userAccountFetchUseCase.fetchById(userAccountSettingsId)).thenReturn(userAccountDTO);
        ResponseEntity<UserAccountDTO> response =
            userConfigurationController.fetchUserAccountSettingsById(userAccountSettingsId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userAccountDTO, response.getBody());
    }

    @Test
    public void testUpdateAccountExpirationPasswordDay()
    {
        ResponseEntity<Void> response =
            userConfigurationController.updateAccountExpirationPasswordDay(userAccountSettingsId,
                60);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testUpdateAccountBlockAttempts()
    {
        ResponseEntity<UserAccountDTO> response =
            userConfigurationController.updateAccountBlockAttempts(userAccountSettingsId,
                5);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}