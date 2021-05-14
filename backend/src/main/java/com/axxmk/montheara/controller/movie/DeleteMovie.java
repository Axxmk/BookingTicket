package com.axxmk.montheara.controller.movie;

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
public class DeleteMovie {

    @DeleteMapping(path = "/movies/{movieId}")
    public Map<String, Object> deleteMovie(@CookieValue String token,
                                           @PathVariable int movieId) {
        Map<String, Object> res = new HashMap<>();

        if (Permission.checkPermission(token, res)) {
            return res;
        }

        try {
            Connection connection = MySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM movie_genre WHERE movieId = ?"
            );
            preparedStatement.setInt(1, movieId);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(
                    "DELETE FROM movie WHERE id = ?"
            );
            preparedStatement.setInt(1, movieId);
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
