package edu.cit.gaane.caresync.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private final String SECRET_KEY =
            "caresync-super-secret-key-for-jwt-authentication-2026";

    private final long EXPIRATION_TIME =
            1000 * 60 * 60 * 24; // 24 hours


    private Key getSigningKey(){

        return Keys.hmacShaKeyFor(
                SECRET_KEY.getBytes()
        );

    }


    public String generateToken(String email, String role){

        return Jwts.builder()

                .subject(email)

                .claim("role", role)

                .issuedAt(new Date())

                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                + EXPIRATION_TIME
                        )
                )

                .signWith(
                        getSigningKey()
                )

                .compact();

    }


    public String extractEmail(String token){

        return Jwts.parser()

                .verifyWith(
                        (javax.crypto.SecretKey) getSigningKey()
                )

                .build()

                .parseSignedClaims(token)

                .getPayload()

                .getSubject();

    }


    public String extractRole(String token){

        return (String) Jwts.parser()

                .verifyWith(
                        (javax.crypto.SecretKey) getSigningKey()
                )

                .build()

                .parseSignedClaims(token)

                .getPayload()

                .get("role");

    }


}