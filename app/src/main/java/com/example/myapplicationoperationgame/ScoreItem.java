package com.example.myapplicationoperationgame;

public class ScoreItem {
    private int score;
    private String date;

    public ScoreItem(int score, String date) {
        this.score = score;
        this.date = date;
    }

    public int getScore() {
        return score;
    }

    public String getDate() {
        return date;
    }
}
