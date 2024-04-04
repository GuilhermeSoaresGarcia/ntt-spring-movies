package com.example.moviesapi.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.moviesapi.model.entity.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HandleToken {

  @Value("${api.security.token.secret}")
  private String secret;

  public String generateToken(User user) {
    Algorithm algorithm = Algorithm.HMAC256(secret);

    return JWT.create()
        .withIssuer("agrix")
        .withSubject(user.getUsername())
        .withExpiresAt(generateExpirationDate())
        .sign(algorithm);
  }

  private Instant generateExpirationDate() {
    return LocalDateTime.now()
        .plusHours(3)
        .toInstant(ZoneOffset.of("-03:00"));
  }

  public String validateToken(String token) {
    Algorithm algorithm = Algorithm.HMAC256(secret);
    return JWT.require(algorithm)
        .withIssuer("ntt-movies")
        .build()
        .verify(token)
        .getSubject();
  }
}
