package com.axxmk.montheara.util.jwt;

import com.axxmk.montheara.util.MySQLConnection;
import io.jsonwebtoken.JwtException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class Permission {
    public static boolean checkPermission(String token, Map<String, Object> res) {
        try {
            int userId = JwtUtil.parseToken(token);

            Connection connection = MySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT role FROM user WHERE id = ?");
            preparedStatement.setInt(1, userId);

            ResultSet rs = preparedStatement.executeQuery();

            boolean permission = rs.next() && rs.getBoolean("role");

            if (!permission) {
                res.put("success", false);
                res.put("error_reason", "User is not admin");
                return true;
            }

        } catch (JwtException | SQLException e) {
            res.put("success", false);
            res.put("error_reason", "Cannot verify token");
            return true;
        }

        return false;
    }
}
