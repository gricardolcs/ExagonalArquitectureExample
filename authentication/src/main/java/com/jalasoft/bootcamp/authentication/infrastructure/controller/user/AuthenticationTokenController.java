package com.jalasoft.bootcamp.authentication.infrastructure.controller.user;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

import com.jalasoft.bootcamp.authentication.domain.user.Account;
import com.jalasoft.bootcamp.authentication.domain.user.User;
import com.jalasoft.bootcamp.authentication.infrastructure.configuration.security.JwtUtil;
import com.jalasoft.bootcamp.authentication.infrastructure.dto.UserCredentialsDTO;
import com.jalasoft.bootcamp.authentication.infrastructure.usecase.user.fetch.UserFetchUseCase;
import com.jalasoft.bootcamp.becommon.domain.exceptions.AuthTokenException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationTokenController
{

    private static final Logger logger = LogManager.getLogger(AuthenticationTokenController.class);
    private final AuthenticationManager authenticationManager;
    private final UserFetchUseCase userDetailsService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthenticationTokenController(
        final AuthenticationManager authenticationManager,
        final UserFetchUseCase userDetailsService,
        final JwtUtil jwtUtil)
    {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Account> createAuthenticationToken(
        HttpServletRequest request,
        @RequestBody UserCredentialsDTO userCredentialsDTO)
    {
        try
        {
            UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                    userCredentialsDTO.getUsernameOrEmail(), userCredentialsDTO.getPassword());
            authenticationManager.authenticate(authentication);
            User user = userDetailsService.loadUserByUsername(userCredentialsDTO.getUsernameOrEmail());
            String token = jwtUtil.generateToken(user);
            Account account = userDetailsService.getAlertMessage(user.getPasswordUpdatedDate());
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);
            HttpSession session = request.getSession(true);
            session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, securityContext);
            account.setToken(token);
            return ResponseEntity.ok(account);
        }
        catch (Exception e)
        {
            throw new AuthTokenException("Problem with the token generation", e.getMessage());
        }
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        if (session != null)
        {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.getAuthentication().setAuthenticated(false);
            SecurityContextHolder.clearContext();
            session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, null);
            session.invalidate();
        }
    }
}

