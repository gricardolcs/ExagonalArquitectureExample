package com.jalasoft.bootcamp.authentication.domain.user;

import io.swagger.annotations.ApiModel;

@ApiModel
public enum UserStatus
{
    ACTIVE,
    INACTIVE,
    BLOCKED
}
