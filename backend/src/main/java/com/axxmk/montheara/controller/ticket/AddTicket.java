package com.axxmk.montheara.controller.ticket;

import com.axxmk.montheara.model.Ticket;
import com.axxmk.montheara.util.jwt.JwtUtil;
import com.axxmk.montheara.util.MySQLConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AddTicket {

    @PostMapping(path = "/tickets")
    public Map<String, Object> addTicket(@CookieValue String token, @RequestBody Ticket ticket) {
        Map<String, Object> res = new HashMap<>();

        int userId = JwtUtil.parseToken(token);

        try {
            Connection connection = MySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO ticket (showtimeId, userId) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS
            );
            preparedStatement.setInt(1, ticket.getShowtimeId());
            preparedStatement.setInt(2, userId);

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();

            int generatedKey = rs.getInt(1);

            String[] seatNumbers = ticket.getSeatNumbers().split(",");
            for (String seatNumber : seatNumbers) {
                PreparedStatement preparedGenre = connection.prepareStatement(
                        "INSERT INTO ticket_seat (ticketId, seatNumber) " +
                                " VALUES (?, ?)"
                );
                preparedGenre.setInt(1, generatedKey);
                preparedGenre.setString(2, seatNumber);

                preparedGenre.executeUpdate();
            }

            res.put("success", true);

        } catch (SQLException e) {
            e.printStackTrace();
            res.put("success", false);
            res.put("error_reason", "MySQL Connection Error");
        }

        return res;
    }

    @Autowired
    JavaMailSender javaMailSender;

    @PostMapping(path = "/tickets/mail")
    public Map<String, Object> sendMail(@CookieValue String token, @RequestBody Ticket ticket) {
        Map<String, Object> res = new HashMap<>();

        int userId = JwtUtil.parseToken(token);
        String email = "";

        try {
            Connection connection = MySQLConnection.getConnection();
            PreparedStatement preparedStatement =  connection.prepareStatement("SELECT email FROM user WHERE id = ?");
            preparedStatement.setInt(1, userId);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                email = rs.getString("email");
            }
            else{
                res.put("success", false);
                res.put("error_reason", "Cannot find your email");
                return res;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            res.put("success", false);
            res.put("error_reason", "MySQL Connection Error");
        }
        System.out.println(email);

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setFrom("montheara@axxmk.ga");
        msg.setSubject("Montheara Payment For Booking Ticket");
        msg.setText("Thank you for booking ticket with our website. Please pay within 2 hours otherwise we will cancel your booking \n" +
                "Account no : 034-5-678900 \n" +
                "Name : Ann Monthara \n" +
                "Amount : " + ticket.getPrice() + " Bath \n\n" +
                "Ticket Detail: \n" +
                "Title: " + ticket.getTitle() + " \n" +
                "Theatre: " + ticket.getTheatre() + " \n" +
                "Date: " + ticket.getDate() + " \n" +
                "Time: " + ticket.getStart_time() + " \n" +
                "Seats: " + ticket.getSeatNumbers()
        );

        javaMailSender.send(msg);

        res.put("success", true);
        return res;
    }
}
