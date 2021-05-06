package com.axxmk.montheara.controller.movie;

import com.axxmk.montheara.util.MySQLConnection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class GetMovies {

    @GetMapping(path = "/movies")
    public Map<String, Object> getMovies() {

        Map<String, Object> res = new HashMap<>();
        try {
            Connection connection = MySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT movie.*, group_concat(genre.name) as genres " +
                            "FROM movie " +
                            "INNER JOIN movie_genre ON movie_genre.movieId = movie.id " +
                            "INNER JOIN genre ON genre.id = movie_genre.genreId " +
                            "GROUP BY movie.id"
            );
            ResultSet rs = preparedStatement.executeQuery();

            ArrayList<Object> movies = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> movie = new HashMap<>();
                movie.put("movieId", rs.getInt("id"));
                movie.put("title", rs.getString("title"));
                movie.put("status", rs.getString("status"));
                movie.put("revenue", rs.getInt("revenue"));
                movie.put("duration", rs.getInt("duration"));
                movie.put("synopsis", rs.getString("synopsis"));
                movie.put("poster_path", rs.getString("poster_path"));

                String releaseDate = new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("releaseDate"));
                movie.put("releaseDate", releaseDate);

                String[] genres = rs.getString("genres").split(",");
                movie.put("genre", genres);

                movies.add(movie);
            }

            res.put("success", true);
            res.put("movies", movies);
        } catch (SQLException e) {
            e.printStackTrace();
            res.put("success", false);
            res.put("error_reason", "MySQL Connection Error");
        }

        return res;
    }
}