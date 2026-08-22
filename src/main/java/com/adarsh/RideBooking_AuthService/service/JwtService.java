package com.adarsh.RideBooking_AuthService.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final Logger log = LoggerFactory.getLogger(JwtService.class);

    @Value("${jwt.expiry}")
    private int expiry;

    @Value("${jwt.secret}")
    private String secret;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String createJwtToken(UserDetails  userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(
                "roles",
                userDetails.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList());

        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getSigningKey())
                .compact();
    }

   /* public String createJwtToken(String email) {
        return createJwtToken(new HashMap<>(), email);
    }*/

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


    public Boolean validateToken(String token){

        /*final String userEmail = extractEmail(token);
        return email.equals(userEmail) && !isTokenExpired(token);*/
        try {
            log.debug("Validating JWT");
            extractAllPayloads(token);
            return true;
        } catch (Exception e) {
            log.debug("JWT validation failed: {}", e.getMessage());
            return false;
        }

    }


}
