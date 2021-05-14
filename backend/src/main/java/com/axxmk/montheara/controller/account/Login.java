package com.axxmk.montheara.controller.account;

import com.axxmk.montheara.model.User;
import com.axxmk.montheara.util.jwt.JwtUtil;
import com.axxmk.montheara.util.MySQLConnection;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class Login {

    @PostMapping(path = "/account/login")
    public Map<String, Object> login(@RequestBody User user) {

        Map<String, Object> res = new HashMap<>();
        try {
            Connection connection = MySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE username = ? AND password = ?");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Map<String, Object> userInfo = new HashMap<>();
                Map<String, String> fullname = new HashMap<>();
                Map<String, String> contacts = new HashMap<>();

                String token = JwtUtil.generateToken(rs.getInt("id") + "");
                res.put("token", token);

                userInfo.put("userId", rs.getInt("id"));
                userInfo.put("username", rs.getString("username"));
                userInfo.put("role", rs.getBoolean("role"));
                userInfo.put("avatar_url", rs.getString("avatar_url"));

                fullname.put("firstname", rs.getString("firstname"));
                fullname.put("lastname", rs.getString("lastname"));
                userInfo.put("fullname", fullname);

                contacts.put("email", rs.getString("email"));
                contacts.put("phone", rs.getString("phone"));
                userInfo.put("contacts", contacts);

                res.put("user", userInfo);
                res.put("isLogin", true);
            }
            else {
                res.put("isLogin", false);
                res.put("error_reason", "Username or Password is incorrect");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            res.put("success", false);
            res.put("error_reason", "MySQL Connection Error");
        }

        return res;
    }
}
