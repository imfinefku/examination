package com.study.common.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.study.bean.User;
import com.study.controller.AuthorityController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class JWTUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorityController.class);

    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000;//token到期时间ms

    private static final String TOKEN_SECRET = "helloworld";  //密钥盐

    public static String sign(User user) {
        String token = null;
        long currentTimeMillis = System.currentTimeMillis();
        Date issuedAt = new Date(currentTimeMillis);
        Date expiresAt = new Date(currentTimeMillis + EXPIRE_TIME);
        try {
            token = JWT.create()
                    .withIssuer("auth0")//发行人
                    .withClaim("id", user.getId())
                    .withClaim("name", user.getName())
                    .withIssuedAt(issuedAt)//签发时间
                    .withExpiresAt(expiresAt)//过期时间
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));//加密
        } catch (Exception ex) {
            LOGGER.error("签发token失败", ex);
        }
        return token;
    }

    /**
     * 校验token
     * @param token
     * @return
     */
    public static boolean verify(String token) {
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();//创建token验证器
            jwtVerifier.verify(token);
            return true;
        } catch (Exception ex) {
            //抛出错误即为验证不通过
            return false;
        }
    }

    public static User getUser(String token){
        User user = new User();
        DecodedJWT jwt=JWT.decode(token);
        user.setId(jwt.getClaim("id").asString());
        user.setName(jwt.getClaim("name").asString());
        return user;
    }
}
