package com.axxmk.montheara.controller.movie;

import com.axxmk.montheara.model.Movie;
import com.axxmk.montheara.util.MySQLConnection;
import com.axxmk.montheara.util.Permission;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UpdateMovie {

    @PutMapping(path = "/movies/{movieId}")
    public Map<String, Object> updateMovie(@CookieValue String token,
                                           @PathVariable int movieId,
                                           @RequestBody Movie movie) {
        Map<String, Object> res = new HashMap<>();

        if (Permission.checkPermission(token, res)) {
            return res;
        }

        try {
            Connection connection = MySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE movie SET title = ?, status = ?, releaseDate = ?, duration = ?, synopsis = ?, revenue = ? " +
                            "WHERE id = ?"
            );
            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setString(2, movie.getStatus());
            preparedStatement.setDate(3, new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(movie.getReleaseDate()).getTime()));
            preparedStatement.setInt(4, movie.getDuration());
            preparedStatement.setString(5, movie.getSynopsis());
            preparedStatement.setInt(6, movie.getRevenue());
            preparedStatement.setInt(7, movieId);

            preparedStatement.execute();


            preparedStatement = connection.prepareStatement(
                    "DELETE FROM movie_genre " +
                            "WHERE movieId = ?"
            );
            preparedStatement.setInt(1, movieId);

            preparedStatement.execute();

            String[] genres = movie.getGenre().split(",");
            for (String eachGenre : genres) {
                PreparedStatement preparedGenre = connection.prepareStatement(
                        "INSERT INTO movie_genre (movieId, genreId) " +
                                " SELECT ? AS movieId, id FROM genre WHERE name = ? "
                );
                preparedGenre.setInt(1, movieId);
                preparedGenre.setString(2, eachGenre);

                preparedGenre.execute();
            }

            res.put("success", true);

        } catch (SQLException | ParseException e) {
            e.printStackTrace();
            res.put("success", false);
            res.put("error_reason", "MySQL Connection Error");
        }

        return res;
    }
}
