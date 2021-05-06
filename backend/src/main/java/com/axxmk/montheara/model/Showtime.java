package com.axxmk.montheara.model;

public class Showtime {
    private int movieId;
    private int theatre;
    private String date;
    private String start_times;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getTheatre() {
        return theatre;
    }

    public void setTheatre(int theatre) {
        this.theatre = theatre;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStart_times() {
        return start_times;
    }

    public void setStart_times(String start_times) {
        this.start_times = start_times;
    }
}
