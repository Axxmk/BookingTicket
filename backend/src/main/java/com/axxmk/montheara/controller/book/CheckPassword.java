package com.axxmk.montheara.controller.book;

import com.axxmk.montheara.model.User;
import com.axxmk.montheara.util.JwtUtil;
import com.axxmk.montheara.util.MySQLConnection;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/booking")
public class CheckPassword {

    @PostMapping(path="/check")
    public Map<String, Object> checkPassword(@CookieValue String token, @RequestBody User user) {
        Map<String, Object> res = new HashMap<>();

        int userId = JwtUtil.parseToken(token);

        try {
            Connection connection = MySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT password FROM user WHERE id = ?"
            );
            preparedStatement.setInt(1, userId);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String password = rs.getString("password");
                if(password.equals(user.getPassword())) {
                    res.put("success", true);
                }
                else {
                    res.put("success", false);
                    res.put("error_reason", "Your password is incorrect");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            res.put("success", false);
            res.put("error_reason", "MySQL Connection Error");
        }

        return  res;
    }
}
