package com.jalasoft.bootcamp.authentication.infrastructure.configuration.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.web.authentication.www.NonceExpiredException;
import org.springframework.stereotype.Component;

@Component
public class TokenManager implements Serializable
{

    private static final long serialVersionUID = 7008375124389347049L;
    @Value("${jwt.secret}")
    private String jwtSecret;

    public String getUsernameFromToken(String token)
    {
        try
        {
            final Claims claims =
                Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
            return claims.getSubject();
        }
        catch (ExpiredJwtException e)
        {
            throw new NonceExpiredException("token expired");
        }
        catch (SignatureException e)
        {
            throw new InsufficientAuthenticationException("Invalid token");
        }
    }
}
