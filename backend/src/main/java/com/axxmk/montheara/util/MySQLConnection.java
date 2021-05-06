package com.axxmk.montheara.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    private static final String db_url = "jdbc:mysql://csproject.sit.kmutt.ac.th:3306/db63130500224";
    private static final Logger logger = LoggerFactory.getLogger(MySQLConnection.class);
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (!connection.isValid(300)) {
            return reConnect();
        }
        return connection;
    }

    public static Connection reConnect() throws SQLException {
        connection = DriverManager.getConnection(db_url, "63130500224", "abcd1234");
        logger.info("Reconnected to MySQL Server");
        return connection;
    }
}
