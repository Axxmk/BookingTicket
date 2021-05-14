package com.axxmk.montheara.controller.showtime;

import com.axxmk.montheara.util.MySQLConnection;
import com.axxmk.montheara.util.jwt.Permission;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class DeleteShowtime {

    @DeleteMapping(path = "/showtimes/{showtimeId}")
    public Map<String, Object> deleteShowtime(@CookieValue String token,
                                              @PathVariable int showtimeId) {
        Map<String, Object> res = new HashMap<>();

        if (Permission.checkPermission(token, res)) {
            return res;
        }

        try {
            Connection connection = MySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM showtime WHERE id = ?"
            );
            preparedStatement.setInt(1, showtimeId);
            preparedStatement.executeUpdate();

            res.put("success", true);

        } catch (SQLException e) {
            e.printStackTrace();
            res.put("success", false);
            res.put("error_reason", "MySQL Connection Error");
        }

        return res;
    }
}
