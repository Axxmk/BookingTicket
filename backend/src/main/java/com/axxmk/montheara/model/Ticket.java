package com.axxmk.montheara.model;

public class Ticket {
    private int showtimeId;
    private String seatNumbers;
    private String title;
    private int theatre;
    private String date;
    private String start_time;
    private int price;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
    }

    public String getSeatNumbers() {
        return seatNumbers;
    }

    public void setSeatNumbers(String seatNumbers) {
        this.seatNumbers = seatNumbers;
    }
}
