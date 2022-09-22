package com.jalasoft.bootcamp.authentication.infrastructure.configuration.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jalasoft.bootcamp.becommon.domain.exceptions.AuthTokenException;
import com.jalasoft.bootcamp.becommon.domain.exceptions.Errors;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Component
public class BootcampAuthenticationEntryPoint implements AuthenticationEntryPoint
{

    private final HandlerExceptionResolver resolver;

    @Autowired
    public BootcampAuthenticationEntryPoint(final @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver)
    {
        this.resolver = resolver;
    }

    @Override
    public void commence(
        HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException)
    {
        if (authException != null)
        {
            resolver.resolveException(request, response, null, new AuthTokenException("Token"
                , authException.getLocalizedMessage()));
        }
    }
}
