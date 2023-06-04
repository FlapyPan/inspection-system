package com.inspectionSystem.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(UserDetails userDetails) throws UnsupportedEncodingException {
        Map<String, Object> claims = new HashMap<>();
        claims.put("Type", "jwt");
        claims.put("alg", "HS256");
        Date expireDate = new Date(System.currentTimeMillis() + expiration * 3600 *1000 * 24);
        // 设置头部信息
        return JWT.create()
                .withHeader(claims)
                .withClaim("phone", userDetails.getUsername())
                .withExpiresAt(expireDate)
                .sign(Algorithm.HMAC256(secret));
    }

    public String getUsernameFromToken(String token) throws UnsupportedEncodingException {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("phone").asString();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            String username = getUsernameFromToken(token);
            return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    private boolean isTokenExpired(String token) throws UnsupportedEncodingException {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getExpiresAt().before(new Date());
    }
}

