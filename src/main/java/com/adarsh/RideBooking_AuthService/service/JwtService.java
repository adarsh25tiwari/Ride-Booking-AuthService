package com.adarsh.RideBooking_AuthService.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.expiry}")
    private int expiry;

    @Value("${jwt.secret}")
    private String secret;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String createJwtToken(Map<String, Object> claims,String email) {
        Date expireDate = new Date(System.currentTimeMillis() + expiry);

        return Jwts.builder()
                .claims(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expireDate)
                .signWith(getSigningKey())
                .subject(email)
                .compact();
    }

    public String createJwtToken(String email) {
        return createJwtToken(new HashMap<>(), email);
    }

    // check token expiry

    public Claims extractAllPayloads(String token){
        return Jwts
                .parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token,Function<Claims, T> claimsResolver){
        Claims claims = extractAllPayloads(token);
        return claimsResolver.apply(claims);
    }

    private Date extractExpiry(String token){
        return (Date) extractClaim(token,Claims::getExpiration);
    }

    public Boolean isTokenExpired(String token){
        return extractExpiry(token).before(new Date(System.currentTimeMillis()));
    }

    // validate Token

    public String extractEmail(String token){
        return extractClaim(token,Claims::getSubject);
    }


    private Boolean validateToken(String token, String email){
        final String userEmail = extractEmail(token);
        return email.equals(userEmail) && !isTokenExpired(token);
    }


}
