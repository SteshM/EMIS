package com.emis.EMIS.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Component

public class TokenGenerator {
    Algorithm algo = Algorithm.HMAC256("Stella".getBytes());

    public String token(User user){
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(Instant.now().plus(60, ChronoUnit.MINUTES))
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algo);
    }
}
