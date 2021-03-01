package dev.zhen.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtUtil {

    private static final String SECRET = "SUPER_SAFE_SECRET";
    private static final Algorithm algorithm = Algorithm.HMAC256(SECRET);

    public static String generate(String role, String employeeName) {
        String token = JWT.create()
                .withClaim("role", role)
                .withClaim("employeeName", employeeName)
                .sign(algorithm);
        return token;
    }

    public static DecodedJWT verifyToken(String token) {
        DecodedJWT jwt;
        try {
            jwt = JWT.require(algorithm).build().verify(token);
            return jwt;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
