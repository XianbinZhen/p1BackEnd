package dev.zhen.utilTests;

import com.auth0.jwt.interfaces.DecodedJWT;
import dev.zhen.utils.JwtUtil;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JwtTests {

    @Test
    @Order(0)
    void generate_jwt() {
        String jwt = JwtUtil.generate("employee", "Chalice Ferdy");
        System.out.println(jwt);
    }

    @Test
    @Order(1)
    void decode_jwt() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbXBsb3llZU5hbWUiOiJDaGFsaWNlIEZlcmR5Iiwicm9sZSI6ImVtcGxveWVlIn0.ykexJ1e0XmN8ORwTfA16uv0TK-nce9TEC87jU5TCRQw";
        DecodedJWT jwt = JwtUtil.verifyToken(token);
        String role = jwt.getClaim("role").asString();
        String employeeName = jwt.getClaim("employeeName").asString();
        Assertions.assertTrue(role.equals("employee"));
        Assertions.assertTrue(employeeName.equals("Chalice Ferdy"));
    }

    @Test
    @Order(2)
    void edit_jwt() {
        DecodedJWT jwt =JwtUtil.verifyToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbXBsb3llZU5hbWUiOiJDaGFsaWNlIEZlcmR5Iiwicm9sZSI6Im1hbmFnZXIifQ.AO4TEDMfvdcBcZ861fE5Yz1Qv7FHlGeh9y-JXMYopTE");
        Assertions.assertNull(jwt);
        jwt = JwtUtil.verifyToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbXBsb3llZU5hbWUiOiJDaGFsaWNlIEZlcmR5Iiwicm9sZSI6Im1hbmFnZXIifQ.r75AtC3OeOBku8PRVIBnqxXPu2EBiXDJFod1_Ed7D60");
        String role = jwt.getClaim("role").asString();
        Assertions.assertTrue(role.equals("manager"));
    }
}
