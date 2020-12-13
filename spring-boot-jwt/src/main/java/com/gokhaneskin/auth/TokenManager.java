package com.gokhaneskin.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class TokenManager {

    private static final int VALIDITY=5*60*1000;
    Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public String generateToken(String userName){
        return Jwts.builder()
                .setSubject(userName)
                .setIssuer("GokhanEskin")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+VALIDITY))
                .signWith(KEY)
                .compact();
    }

    public boolean tokenValidate(String token){
        return getUserFromToken(token)!=null && isExpired(token);
    }

    public String getUserFromToken(String token){
        Claims claims = getClaims(token);
        return  claims.getSubject();
    }

    public boolean isExpired(String token){
        Claims claims = getClaims(token);
        return  claims.getExpiration().after(new Date(System.currentTimeMillis()));
    }

    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
    }

}
