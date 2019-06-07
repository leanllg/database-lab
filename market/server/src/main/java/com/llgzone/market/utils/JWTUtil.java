package com.llgzone.market.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    // 过期时间2小时
    private static final long EXPIRE_TIME = 2*60*60*1000;


    private static String secret;

    @SuppressWarnings("static-access")
    @Value("${jwt.secret}")
    private void setSecret(String secret) {
        this.secret = secret;
    }

    /**
     * 校验token是否正确
     * @param token 密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String uid, int type) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("uid", uid)
                    .withClaim("type", type)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的用户名
     */
    public static String getUid(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("uid").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static Integer getType(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("type").asInt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static String sign(String uid, int type) {
        Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // 附带uid信息
        return JWT.create()
                .withClaim("uid", uid)
                .withClaim("type", type)
                .withExpiresAt(date)
                .sign(algorithm);

    }
}