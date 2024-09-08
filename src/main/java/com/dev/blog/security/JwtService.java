package com.dev.blog.security;

import com.dev.blog.data.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${security.jwt.expiry}")
    private long jwtExpiry;
    @Value("${security.jwt.secret-key}")
    private String secretKey;

    public String generateToken(User user){
        return Jwts.builder()
                .subject(user.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpiry))
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims extractAllClaims(String token){
        return  Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public <T> T extractClaims(String token, Function<Claims,T> claimsResolver){
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String getSubject(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public boolean validateToken(String jwtToken) {
        return getExpiration(jwtToken).after(new Date());
    }

    private Date getExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    private SecretKey getSecretKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);

    }
}
