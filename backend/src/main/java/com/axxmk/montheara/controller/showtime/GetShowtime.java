package com.axxmk.montheara.controller.showtime;

import com.axxmk.montheara.util.MySQLConnection;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class GetShowtime {

    @GetMapping(path = "/showtimes/{movieId}")
    public Map<String, Object> getShowtime(@PathVariable int movieId) {
        Map<String, Object> res = new HashMap<>();

        java.sql.Date now = new java.sql.Date(new Date().getTime());

        try {
            Connection connection = MySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM showtime WHERE movieId = ? AND start_time >= ? "
            );
            preparedStatement.setInt(1, movieId);
            preparedStatement.setDate(2, now);

            ResultSet rs = preparedStatement.executeQuery();

            ArrayList<Object> showtimes = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> showtime = new HashMap<>();
                showtime.put("showtimeId", rs.getInt("id"));
                showtime.put("theatre", rs.getInt("theatre"));
                showtime.put("start_time", new SimpleDateFormat("HH:mm").format(rs.getTimestamp("start_time")));
                showtime.put("date", new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("start_time")));

                showtimes.add(showtime);
            }

            res.put("showtimes", showtimes);
            res.put("success", true);

        } catch (SQLException e) {
            e.printStackTrace();
            res.put("success", false);
        }

        return res;
    }
}
