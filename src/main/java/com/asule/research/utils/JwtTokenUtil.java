package com.asule.research.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {
    @Value("${jwt.secret-key}")
    private String secretKeyBase64; // 这里存的是 Base64 编码后的密钥

    private static final long EXPIRATION_TIME = 3600000; // 1 hour (调整为1小时)

    public String generateToken(Long userId, String username) {
        SecretKey key = getSigningKey();

        // payload有效负载，你想放什么数据进去
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("username", username);

        long expireTime = System.currentTimeMillis() + EXPIRATION_TIME;

        return Jwts.builder()
                .claims(claims)
                .subject(String.valueOf(userId))              // sub-面向的用户
//                .issuer(issuer)                               // iss-签发者
                .issuedAt(new Date())                         // iat 发布时间
                .expiration(new Date(expireTime))             // exp 到期时间
                .signWith(key, Jwts.SIG.HS256)                // 签名算法
                .compact();
    }

    /**
     * 解析 Base64 密钥，并返回 SecretKey
     */
    private SecretKey getSigningKey() {
        byte[] keyBytes = Base64.getDecoder().decode(secretKeyBase64);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public Claims parseToken(String token) {
        SecretKey secretKey = getSigningKey();
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isTokenValid(String token) {
        try {
            Claims claims = parseToken(token);
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public Long getUserId(String token) {
        Claims claims = parseToken(token);
        return Long.valueOf(claims.getSubject());
    }

    public String getUsername(String token) {
        Claims claims = parseToken(token);
        return claims.get("username", String.class);
    }

    public static void main(String[] args) {
        // 生成密钥
        SecretKey key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
        String base64Key = Encoders.BASE64.encode(key.getEncoded());
        System.out.println(base64Key);
    }
}
