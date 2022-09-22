package com.jalasoft.bootcamp.authentication.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Account
{
    private String token;
    private long daysRemaining;
    private String message;
}
