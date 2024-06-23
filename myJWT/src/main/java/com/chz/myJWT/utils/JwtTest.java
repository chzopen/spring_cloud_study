package com.chz.myJWT.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class JwtTest {

    public static final String SECRET = "I_am_chz";

    public static String createJwtToken()
    {
        HashMap<String, Object> headers = new HashMap<>();

        Calendar expires = Calendar.getInstance();
        expires.add(Calendar.MINUTE, 30);       // 过期时间，60s

        String jwtToken = JWT.create()
                // 第一部分Header
                .withHeader(headers)
                // 第二部分Payload
                .withClaim("userId", 20)
                .withClaim("userName", "chz")
                .withExpiresAt(expires.getTime())
                // 第三部分Signature
                .sign(Algorithm.HMAC256(SECRET));
        return jwtToken;
    }

    public static DecodedJWT parseJwtToken(String jwtToken)
    {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(jwtToken);
        return decodedJWT;
    }

    public static void main(String[] args)
    {
        String jwtToken = createJwtToken();
        System.out.println("jwtToken: " + jwtToken);

        DecodedJWT decodedJWT = parseJwtToken(jwtToken);
        System.out.println("userId: " + decodedJWT.getClaim("userId").asInt());
        System.out.println("userName: " + decodedJWT.getClaim("userName").asString());
        System.out.println("expiresAt: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(decodedJWT.getExpiresAt()));
    }

}