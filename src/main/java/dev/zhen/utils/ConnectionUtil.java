package dev.zhen.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    public static Connection createConnection() {
        try {
//            Connection connection = DriverManager.getConnection(System.getenv("CONN_CRED"));
            Connection connection = DriverManager.getConnection("jdbc:postgresql://ec2-35-153-114-74.compute-1.amazonaws.com/d5j6joeui4sdul?user=drudcvmkekjkhc&password=08cd2f0c228df7825cf3961f8cb702592aaa0fc4296e0a0785c553e9d38822ed");
            return connection;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

}
