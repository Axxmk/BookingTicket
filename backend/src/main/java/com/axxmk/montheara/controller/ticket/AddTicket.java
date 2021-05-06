package com.axxmk.montheara.controller.ticket;

import com.axxmk.montheara.model.Ticket;
import com.axxmk.montheara.model.User;
import com.axxmk.montheara.util.JwtUtil;
import com.axxmk.montheara.util.MySQLConnection;
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
}
