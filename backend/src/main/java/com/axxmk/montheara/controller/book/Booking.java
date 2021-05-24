package com.axxmk.montheara.controller.book;

import com.axxmk.montheara.util.MySQLConnection;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/booking")
public class Booking {

    @GetMapping(path = "/showtimes/{movieId}")
    public Map<String, Object> getBookingShowtime(@PathVariable int movieId) {
        Map<String, Object> res = new HashMap<>();

        try {
            Connection connection = MySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM showtime WHERE movieId = ? AND start_time >= current_timestamp AND Date(start_time) <= current_date + interval 6 day ORDER BY start_time, theatre"
            );
            preparedStatement.setInt(1, movieId);

            ResultSet rs = preparedStatement.executeQuery();

            ArrayList<Object> showtimes = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> showtime = new HashMap<>();
                showtime.put("showtimeId", rs.getInt("id"));
                showtime.put("theatre", rs.getInt("theatre"));
                showtime.put("date", new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("start_time")));
                showtime.put("start_time", new SimpleDateFormat("HH:mm").format(rs.getTimestamp("start_time")));

                showtimes.add(showtime);
            }
            res.put("showtimes", showtimes);

            preparedStatement = connection.prepareStatement(
                    "SELECT title FROM movie WHERE id = ?"
            );
            preparedStatement.setInt(1, movieId);

            rs = preparedStatement.executeQuery();
            if(rs.next()) {
                res.put("movieTitle", rs.getString("title"));
                res.put("success", true);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            res.put("success", false);
            res.put("error_reason", "MySQL Connection Error");
        }

        return res;
    }

    @GetMapping(path = "/seats/{showtimeId}")
    public Map<String, Object> getBookingSeats(@PathVariable int showtimeId) {
        Map<String, Object> res = new HashMap<>();

        ArrayList<String> seats = new ArrayList<>();
        String[] prefix = {"A", "B", "C", "D", "E"};
        String number;

        for(int i = 0; i <= 4; i++) {
            for(int j = 1; j <= 16; j++) {
                number = String.format("%02d", j);
                seats.add(prefix[i] + number);
            }
        }

        try {
            Connection connection = MySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT seatNumber FROM ticket INNER JOIN ticket_seat ON ticket_seat.ticketId = ticket.id WHERE ticket.showtimeId = ?"
            );
            preparedStatement.setInt(1, showtimeId);

            ResultSet rs = preparedStatement.executeQuery();

            ArrayList<String> reservedSeats = new ArrayList<>();
            while (rs.next()) {
                reservedSeats.add(rs.getString("seatNumber"));
            }

            seats.removeAll(reservedSeats);

            res.put("seats", seats);
            res.put("success", true);

        } catch (SQLException e) {
            e.printStackTrace();
            res.put("success", false);
            res.put("error_reason", "MySQL Connection Error");
        }

        return res;
    }
}
