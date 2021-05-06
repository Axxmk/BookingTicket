package com.axxmk.montheara.controller.account;

import com.axxmk.montheara.model.User;
import com.axxmk.montheara.util.MySQLConnection;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class Register {

    @PostMapping(path = "/account/register")
    public Map<String, Object> register(@RequestBody User user) {
        Map<String, Object> res = new HashMap<>();

        String randomAvatar = RandomStringUtils.randomAlphanumeric(10);
        String avatar_url = "https://avatars.dicebear.com/api/bottts/" + randomAvatar + ".svg";

        try {
            Connection connection = MySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO user (username, password, firstname, lastname, phone, email, avatar_url) " +
                            "VALUE (?, ?, ?, ?, ?, ?, ?)"
            );
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstname());
            preparedStatement.setString(4, user.getLastname());
            preparedStatement.setString(5, user.getPhone());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setString(7, avatar_url);

            preparedStatement.executeUpdate();

            res.put("success", true);
        } catch (SQLException e) {
            e.printStackTrace();
            res.put("success", false);

            if (e instanceof SQLIntegrityConstraintViolationException) {
                if (e.getMessage().contains("for key 'username'")) {
                    res.put("error_reason", "Username is already exist");
                } else if (e.getMessage().contains("for key 'email'")) {
                    res.put("error_reason", "Email is already exist");
                }
            } else {
                res.put("error_reason", "MySQL Connection Error");
            }
        }

        return res;
    }

    @Autowired
    JavaMailSender javaMailSender;

    @PostMapping(path = "/account/register/mail")
    public Map<String, Object> sendMail(@RequestParam String email) {
        Map<String, Object> res = new HashMap<>();

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setFrom("montheara@axxmk.ga");
        msg.setSubject("Login Successfully");
        msg.setText("Hello, This is email from Ann, Monthara. Your registration was successful. " +
                "Thank you for visiting and booking ticket with my website. " +
                "Hope you have a great experience. Thank you, Ann (Founder of Montheara)");

        javaMailSender.send(msg);

        res.put("success", true);
        return res;
    }
}