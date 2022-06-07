package com.rosewhat.rolenote.domain;

public class User {

    private String title;
    private String comment;
    private String tribuna;
    private String place;

    public User(String title, String comment, String tribuna, String place) {
        this.title = title;
        this.comment = comment;
        this.tribuna = tribuna;
        this.place = place;
    }

    public String getTitle() {
        return title;
    }

    public String getComment() {
        return comment;
    }

    public String getTribuna() {
        return tribuna;
    }

    public String getPlace() {
        return place;
    }
}
