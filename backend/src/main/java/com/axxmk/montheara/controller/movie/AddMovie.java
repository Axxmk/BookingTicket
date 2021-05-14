package com.axxmk.montheara.controller.movie;

import com.axxmk.montheara.model.Movie;
import com.axxmk.montheara.util.GetUrlContent;
import com.axxmk.montheara.util.MySQLConnection;
import com.axxmk.montheara.util.jwt.Permission;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AddMovie {

    @PostMapping(path = "/movies")
    public Map<String, Object> addMovie(@CookieValue String token,
                                        @RequestBody Movie movie) {
        Map<String, Object> res = new HashMap<>();
        String poster_path, releaseDate;
        int revenue, duration;

        if (Permission.checkPermission(token, res)) {
            return res;
        }

        try {
            String content = GetUrlContent.getUrlContents(String.format("https://api.themoviedb.org/3/movie/%s?api_key=c1f769d21d49564d0ec0209addf4104a&language=en-US", movie
                    .getTmdbId()));
            JSONObject jsonObject = new JSONObject(content);

            poster_path = "https://image.tmdb.org/t/p/w500" + jsonObject.getString("poster_path");
            releaseDate = jsonObject.getString("release_date");
            revenue = jsonObject.getInt("revenue");
            duration = jsonObject.getInt("runtime");

        } catch (IOException e) {
            res.put("success", false);
            res.put("error_reason", "TMDB not found that movie id");
            return res;
        }

        try {
            Connection connection = MySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO movie (title, status, revenue, releaseDate, duration, synopsis, poster_path) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)"
                    , Statement.RETURN_GENERATED_KEYS
            );
            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setString(2, movie.getStatus());
            preparedStatement.setInt(3, revenue);
            preparedStatement.setDate(4, new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(releaseDate)
                                                                                             .getTime()));
            preparedStatement.setInt(5, duration);
            preparedStatement.setString(6, movie.getSynopsis());
            preparedStatement.setString(7, poster_path);

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();

            int generatedKey = rs.getInt(1);

            String[] genres = movie.getGenre().split(",");
            for (String eachGenre : genres) {
                PreparedStatement preparedGenre = connection.prepareStatement(
                        "INSERT INTO movie_genre (movieId, genreId) " +
                                " SELECT ? AS movieId, id FROM genre WHERE name = ? "
                );
                preparedGenre.setInt(1, generatedKey);
                preparedGenre.setString(2, eachGenre);

                preparedGenre.executeUpdate();
            }

            res.put("success", true);

        } catch (SQLException | ParseException e) {
            e.printStackTrace();
            res.put("success", false);

            if (e instanceof SQLIntegrityConstraintViolationException) {
                if (e.getMessage().contains("for key 'title'")) {
                    res.put("error_reason", "Movie is already exist");
                } else if (e.getMessage().contains("for key 'tmdb'")) {
                    res.put("error_reason", "TMDB movie id is already exist");
                }
            } else {
                res.put("error_reason", "MySQL Connection Error");
            }
        }

        return res;
    }
}
