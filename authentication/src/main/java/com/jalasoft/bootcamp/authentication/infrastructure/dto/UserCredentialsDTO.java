package com.jalasoft.bootcamp.authentication.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserCredentialsDTO
{
    @ApiModelProperty(
        example = "jalasof-user"
    )
    @JsonProperty("username")
    private final String usernameOrEmail;

    @ApiModelProperty(
        example = "password"
    )
    @JsonProperty("password")
    private final String password;
}
