package edu.cit.gaane.caresync.config;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;


@Service
public class JwtService {


    @Value("${jwt.secret}")
    private String SECRET_KEY;


    @Value("${jwt.expiration}")
    private long EXPIRATION_TIME;



    private Key getSigningKey() {       
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }



    public String generateToken(
            String email,
            String role

    ){


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

    @PostConstruct
        public void checkSecret() {
        System.out.println("JWT SECRET = " + SECRET_KEY);
        System.out.println("JWT LENGTH = " + SECRET_KEY.length());
}

}