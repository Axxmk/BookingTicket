package com.axxmk.montheara;

import com.axxmk.montheara.util.MySQLConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class MonthearaApplication {

    public static void main(String[] args) {

        SpringApplication.run(MonthearaApplication.class, args);

        try {
            MySQLConnection.reConnect();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
