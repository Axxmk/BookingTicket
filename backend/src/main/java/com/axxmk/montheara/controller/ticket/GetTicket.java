package com.axxmk.montheara.controller.ticket;

import com.axxmk.montheara.util.jwt.JwtUtil;
import com.axxmk.montheara.util.MySQLConnection;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class GetTicket {

    @GetMapping(path = "/tickets")
    public Map<String, Object> getTicket(@CookieValue String token) {
        Map<String, Object> res = new HashMap<>();

        int userId = JwtUtil.parseToken(token);

        try {
            Connection connection = MySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT showtime.start_time, showtime.theatre, movie.title, group_concat(ticket_seat.seatNumber) as seats " +
                            "FROM ticket " +
                            "INNER JOIN ticket_seat ON ticket.id = ticket_seat.ticketId " +
                            "INNER JOIN showtime ON ticket.showtimeId = showtime.id " +
                            "INNER JOIN movie ON showtime.movieId = movie.id " +
                            "WHERE ticket.userId = ? " +
                            "GROUP BY ticket.id"
            );
            preparedStatement.setInt(1, userId);

            ResultSet rs = preparedStatement.executeQuery();

            ArrayList<Object> tickets = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> ticket = new HashMap<>();
                ticket.put("title", rs.getString("title"));
                ticket.put("theatre", rs.getInt("theatre"));
                ticket.put("date", new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("start_time")));
                ticket.put("start_time", new SimpleDateFormat("HH:mm").format(rs.getTimestamp("start_time")));

                String[] seats = rs.getString("seats").split(",");
                ticket.put("seat", seats);

                tickets.add(ticket);

            }

            res.put("tickets", tickets);
            res.put("success", true);

        } catch (SQLException e) {
            e.printStackTrace();
            res.put("success", false);
            res.put("error_reason", "MySQL Connection Error");
        }

        return res;
    }
}
