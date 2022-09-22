package com.jalasoft.bootcamp.setting.infrastructure.dto.userAccount;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jalasoft.bootcamp.setting.domain.userAccount.UserAccount;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class UserAccountDTO
{
    @ApiModelProperty(
        example = "30"
    )
    @JsonProperty("passwordExpirationInDays")
    private final int passwordExpirationInDays;

    @ApiModelProperty(
        example = "30"
    )
    @JsonProperty("blockAttempts")
    private final int blockAttempts;

    @JsonCreator
    public UserAccountDTO(final int passwordExpirationInDays, final int blockAttempts)
    {
        this.passwordExpirationInDays = passwordExpirationInDays;
        this.blockAttempts = blockAttempts;
    }

    public UserAccountDTO(final UserAccount userAccount)
    {

        this.passwordExpirationInDays = userAccount.getPasswordExpirationInDays();
        this.blockAttempts = userAccount.getBlockAttempts();
    }
}
