package com.jalasoft.bootcamp.authentication.infrastructure.configuration.security;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

import com.jalasoft.bootcamp.authentication.domain.user.User;
import com.jalasoft.bootcamp.authentication.infrastructure.usecase.user.fetch.UserFetchUseCase;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.Field;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class CustomJwtAuthenticationFilter extends OncePerRequestFilter
{

    private final static Logger logger = LoggerFactory
        .getLogger(CustomJwtAuthenticationFilter.class);
    private final UserFetchUseCase userDetailsService;
    private final TokenManager tokenManager;
    private final BootcampAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    public CustomJwtAuthenticationFilter(
        final UserFetchUseCase userDetailsService,
        final TokenManager tokenManager,
        final BootcampAuthenticationEntryPoint jwtAuthenticationEntryPoint)
    {
        this.userDetailsService = userDetailsService;
        this.tokenManager = tokenManager;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response, FilterChain filterChain)
    throws ServletException, IOException
    {
        try
        {
            String token = request.getHeader(Field.AUTHORIZATION);
            if (token != null)
            {
                if (!token.startsWith(Field.BEARER))
                {
                    throw new InsufficientAuthenticationException("Bearer String not found in "
                        + "token");
                }
                String username = tokenManager
                    .getUsernameFromToken(token.replace(Field.BEARER, ""));
                if (username == null)
                {
                    throw new InsufficientAuthenticationException("Token invalid");
                }

                if (hasSessionSecurityHeader(request)||
                    request.getSession().getAttribute(SPRING_SECURITY_CONTEXT_KEY) != null)
                {
                    User user = userDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken
                        authenticationToken = new UsernamePasswordAuthenticationToken(
                        user, user.getPassword(), user.getAuthorities());
                    authenticationToken.setDetails(new
                        WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
            filterChain.doFilter(request, response);
        }
        catch (AuthenticationException e)
        {
            logger.error(e.getMessage());
            jwtAuthenticationEntryPoint.commence(request, response, e);
        }
    }

    private boolean hasSessionSecurityHeader(HttpServletRequest request) {
        String header = request.getHeader(Field.SESSION_SECURITY);
        return header != null && header.equalsIgnoreCase("yes");
    }
}
