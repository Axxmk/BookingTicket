package com.axxmk.montheara.controller.showtime;

import com.axxmk.montheara.util.MySQLConnection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class GetNowShowing {

    @GetMapping(path = "/showtimes/now")
    public Map<String, Object> getNowShowing() {
        Map<String, Object> res = new HashMap<>();

        try {
            Connection connection = MySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT showtime.*, movie.*, group_concat(genre.name) as genres " +
                            "FROM showtime " +
                            "INNER JOIN movie ON showtime.movieId = movie.id " +
                            "INNER JOIN movie_genre ON movie_genre.movieId = movie.id " +
                            "INNER JOIN genre ON genre.id = movie_genre.genreId " +
                            "WHERE showtime.start_time <= current_timestamp AND current_timestamp <= showtime.end_time " +
                            "GROUP BY showtime.id "
            );

            ResultSet rs = preparedStatement.executeQuery();

            ArrayList<Object> showtimes = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> showtime = new HashMap<>();

                Map<String, Object> showtimeInfo = new HashMap<>();
                showtimeInfo.put("showtimeId", rs.getInt("id"));
                showtimeInfo.put("theatre", rs.getInt("theatre"));
                showtime.put("showtimeInfo", showtimeInfo);

                Map<String, Object> movie = new HashMap<>();
                movie.put("movieId", rs.getInt("movieId"));
                movie.put("title", rs.getString("title"));
                movie.put("status", rs.getString("status"));
                movie.put("revenue", rs.getInt("revenue"));
                movie.put("duration", rs.getInt("duration"));
                movie.put("synopsis", rs.getString("synopsis"));
                movie.put("poster_path", rs.getString("poster_path"));

                String releaseDate = new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("releaseDate"));
                movie.put("releaseDate", releaseDate);

                String genre = rs.getString("genres");
                String[] genres = genre.split(",");
                movie.put("genre", genres);

                showtime.put("movie", movie);
                showtimes.add(showtime);
            }

            res.put("showtimes", showtimes);
            res.put("success", true);

        } catch (SQLException e) {
            e.printStackTrace();
            res.put("success", false);
            res.put("error_reason", "MySQL Connection Error");
        }

        return res;
    }
}
