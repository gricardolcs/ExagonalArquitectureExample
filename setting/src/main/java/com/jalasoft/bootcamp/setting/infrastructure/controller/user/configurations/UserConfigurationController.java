package com.jalasoft.bootcamp.setting.infrastructure.controller.user.configurations;

import com.jalasoft.bootcamp.setting.infrastructure.dto.userAccount.UserAccountDTO;
import com.jalasoft.bootcamp.setting.infrastructure.usecase.configurations.fetch.UserAccountFetchUseCase;
import com.jalasoft.bootcamp.setting.infrastructure.usecase.configurations.manipulation.ConfigurationsManipulationUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@ComponentScan(basePackages = "com.jalasoft.bootcamp.becommon.domain.exceptions")
@RestController
@RequestMapping("/configurations/users")
public class UserConfigurationController
{
    private static final String ID = "id";
    private final UserAccountFetchUseCase userAccountSettingsFetchUseCase;
    private final ConfigurationsManipulationUseCase configurationsManipulationUseCase;

    @Autowired
    public UserConfigurationController(
        ConfigurationsManipulationUseCase configurationsManipulationUseCase,
        UserAccountFetchUseCase userAccountSettingsFetchUseCase)
    {
        this.userAccountSettingsFetchUseCase = userAccountSettingsFetchUseCase;
        this.configurationsManipulationUseCase = configurationsManipulationUseCase;
    }

    @ApiOperation(
        value = "Get user settings",
        response = UserAccountDTO.class,
        notes = "Return all user settings"
    )
    @ApiResponses( {
        @ApiResponse(
            code = 200,
            message = "Get all settings successfully"
        ),
        @ApiResponse(
            code = 400,
            message = "Settings file not found"
        )
    })
    @GetMapping("/{id}")
    ResponseEntity<UserAccountDTO> fetchUserAccountSettingsById(@PathVariable(ID) long id)
    {
        return new ResponseEntity<>(userAccountSettingsFetchUseCase.fetchById(id),
            HttpStatus.OK);
    }

    @ApiOperation(
        value = "Update expiration password in days",
        response = Void.class,
        notes = "Update expiration password for all users"
    )
    @ApiResponses( {
        @ApiResponse(
            code = 204,
            message = "Expiration password update successfully"
        ),
        @ApiResponse(
            code = 206,
            message = "Partial update, missing update users date"
        ),
        @ApiResponse(
            code = 400,
            message = "Settings file not found"
        )
    })
    @PatchMapping("/expiration/{id}/{passwordExpirationInDays}")
    public ResponseEntity<Void> updateAccountExpirationPasswordDay(
        @PathVariable(ID) long id,
        @PathVariable("passwordExpirationInDays") int passwordExpirationInDays)
    {
        configurationsManipulationUseCase.updateAccountExpirationDate(id, passwordExpirationInDays);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(
        value = "Update account block attempts",
        response = Void.class,
        notes = "Update account block attempts for settings"
    )
    @ApiResponses( {
        @ApiResponse(
            code = 204,
            message = "Block attempts update successfully"
        ),
        @ApiResponse(
            code = 206,
            message = "Partial update, missing update block attempts"
        ),
    })
    @PatchMapping("/account-block-attempts/{id}/{blockAttempts}")
    public ResponseEntity<UserAccountDTO> updateAccountBlockAttempts(
        @PathVariable(ID) long id,
        @PathVariable("blockAttempts") int blockAttempts)
    {
        configurationsManipulationUseCase.updateAccountBlockAttempts(id, blockAttempts);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
