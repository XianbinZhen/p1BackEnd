package dev.zhen.utilTests;

import dev.zhen.utils.ConnectionUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class ConnectionUtilTest {

    @Test
    void create_connection() {
        Connection connection = ConnectionUtil.createConnection();
        Assertions.assertNotNull(connection);
    }
}
