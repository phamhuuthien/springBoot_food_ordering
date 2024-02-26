package com.IT.osahaneat.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtUtilsHelper {
//    @Value("${jwt.privateKey}")
    @Value("${jwt.privateKey}")
    private String privateKey;
    public String generateToken(String data){
        Instant now = Instant.now();
        Instant expirationTime = now.plus(7, ChronoUnit.DAYS);

        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
        String jws = Jwts.builder().subject(data).setExpiration(Date.from(expirationTime)).signWith(key).compact();
        return jws;
    }

    public boolean verifyToken(String token){
//        try {
//            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
//            Jwts.parser()
//                    .verifyWith(key)
//                    .build()
//                    .parse(token);
//            return true;
//        }catch (Exception e){
//            return false;
//        }
        try {
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
//            String username = claimsJws.getBody().getSubject();
//            System.out.println("Username: " + username);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public String getUsernameFromJWT(String token){
        try {
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            String username = claimsJws.getBody().getSubject();
            return username;
        }catch (Exception e){
            return null;
        }
    }
}
