package com.eduardo.spoilerappnetwork.security.jwt.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtService {

    private final String secret;
    private final Long VALIDITY;

    public JwtService(@Value("${jwt.secret}") String secret, @Value("${jwt.validity}") Long validity){
        this.secret = secret;
        this.VALIDITY = validity;
    }


    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, secret)
                .setSubject(userDetails.getUsername())
                .setExpiration(Date.from(Instant.now().plus(VALIDITY, ChronoUnit.MINUTES)))
                .compact();
    }

    public String getUsernameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpiration(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        return isNotExpired(token) && getUsernameFromToken(token).equals(userDetails.getUsername());
    }

    private boolean isNotExpired(String token){
        return getExpiration(token).after(new Date());
    }

    private <T> T getClaimFromToken(String token, Function<Claims,T> resolver){
        Claims tokenBody = getBodyFromToken(token);
        return resolver.apply(tokenBody);
    }

    private Claims getBodyFromToken(String token){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
