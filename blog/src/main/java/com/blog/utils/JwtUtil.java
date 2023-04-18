package com.blog.utils;

import io.jsonwebtoken.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.UUID;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    public static final Long JwT_TTL = 24 * 60 * 60 * 1000L;

//    public static final String JwT_KEY = "sangeng";

    //    @Value("${jwt.data.SECRET:jwt-token-secret}")
    private static final String SECRET = "jwtTokenSecret";//创建加密盐
//    private static SecretKey secretKey;
//    private static String key;

    static {
        // 生成密钥
//        key = "0123456789_0123456789_0123456789";
//        secretKey = new SecretKeySpec(key.getBytes(), SignatureAlgorithm.HS256.getJcaName());
//        SECRET = "jwt-token-secret";
    }

    public static String getUUID() {
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        return token;
    }

    public static String createJWT(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID());
        return builder.compact();
    }

    public static String createJWT(String subject, Long ttlMills) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMills, getUUID());
        return builder.compact();
    }

    public static String createJWT(String id, String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);
        return builder.compact();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMills, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if (ttlMills == null) {
            ttlMills = JwtUtil.JwT_TTL;
        }
        long expMillis = nowMillis + ttlMills;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .setId(uuid)
                .setSubject(subject)
                .setIssuer("sg")
                .setIssuedAt(now)
                .signWith(signatureAlgorithm, secretKey)
                .setExpiration(expDate);
    }

    public static void main(String[] args) throws Exception {
        String jwt = createJWT("2312312");
//        Claims claims = parseToken("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIzMGRlNjhlMmM0MmE0MmYzYTViN2IwM2ExN2Y1OGIyZCIsInN1YiI6IjIzMTIzMTIiLCJpc3MiOiJzZyIsImlhdCI6MTY4MTE5MTEyNywiZXhwIjoxNjgxMTkxNDg3fQ.FwuGMwJJoE0i39APOs4mKtSmBp-px6rgJkw5ZUXoZ2A");
//        String subject = claims.getSubject();
//        System.out.println(subject);
        System.out.println(jwt);
    }

    //校验jwt
    public static Claims parseToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(generalKey())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static SecretKey generalKey() {
        byte[] eccodedKey = Base64.getDecoder().decode(SECRET);
        SecretKey key = new SecretKeySpec(eccodedKey, 0, eccodedKey.length, "AES");
        return key;
    }
}
