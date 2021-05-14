package com.axxmk.montheara.controller.showtime;

import com.axxmk.montheara.model.Showtime;
import com.axxmk.montheara.util.MySQLConnection;
import com.axxmk.montheara.util.jwt.Permission;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AddShowtime {

    @PostMapping(path = "/showtimes")
    public Map<String, Object> addShowtime(@CookieValue String token,
                                           @RequestBody Showtime showtime) {
        Map<String, Object> res = new HashMap<>();

        if (Permission.checkPermission(token, res)) {
            return res;
        }

        try {
            Connection connection = MySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT duration FROM movie WHERE id = ?"
            );
            preparedStatement.setInt(1, showtime.getMovieId());

            ResultSet rs = preparedStatement.executeQuery();
            rs.next();

            int duration = rs.getInt("duration");

            String[] start_time = showtime.getStart_times().split(",");
            for(String eachTime : start_time) {
               PreparedStatement preparedStatement2 = connection.prepareStatement(
                        "INSERT INTO showtime (movieId, theatre, start_time, end_time ) " +
                                "VALUES (?, ?, ?, ?)"
                );
                preparedStatement2.setInt(1, showtime.getMovieId());
                preparedStatement2.setInt(2, showtime.getTheatre());

               long start_timeStamp = new SimpleDateFormat("yyyy-MM-ddHH:mm").parse(showtime.getDate() + eachTime).getTime();

                preparedStatement2.setTimestamp(3, new Timestamp(start_timeStamp));
                preparedStatement2.setTimestamp(4, new Timestamp(start_timeStamp + (duration * 60000L)));

                preparedStatement2.executeUpdate();
            }

            res.put("success", true);

        } catch (SQLException | ParseException e) {
            if (e instanceof SQLIntegrityConstraintViolationException) {
                e.printStackTrace();
                res.put("success", false);
                res.put("error_reason", "This showtime already exists");
            } else {
                e.printStackTrace();
                res.put("success", false);
                res.put("error_reason", "MySQL Connection Error");
            }
        }

        return res;
    }
}
