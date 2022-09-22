package com.jalasoft.bootcamp.authentication.infrastructure.configuration.security;

import com.jalasoft.bootcamp.authentication.domain.user.User;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.Field;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class JwtUtil
{

    private final static Logger logger = LoggerFactory.getLogger(JwtUtil.class);
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.refreshExpirationDateInMs}")
    private int refreshExpirationDateInMs;

    public String generateToken(User user)
    {
        Map<String, Object> claims = new HashMap<>();

        Collection<SimpleGrantedAuthority> roles = user.getAuthorities();

        if (roles.contains(new SimpleGrantedAuthority(Field.ADMIN)))
        {
            claims.put("isAdmin", true);
        }
        if (roles.contains(new SimpleGrantedAuthority(Field.USER)))
        {
            claims.put("isUser", true);
        }

        claims.put("name", user.getFirstName());
        claims.put("email", user.getEmail());
        claims.put("status", user.getStatus());
        claims.put("id", user.getId().toString());
        logger.info("Token had been generated");
        return generateToken(claims, user.getUsername());
    }

    private String generateToken(Map<String, Object> claims, String subject)
    {
        return Jwts.builder().setClaims(claims).setSubject(subject)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + refreshExpirationDateInMs))
            .signWith(SignatureAlgorithm.HS512, secret).compact();
    }
}
