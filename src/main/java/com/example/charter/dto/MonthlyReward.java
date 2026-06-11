package com.example.charter.dto;

public class MonthlyReward {
    private String month;
    private long points;

    public MonthlyReward(String month, long points) {
        this.month = month;
        this.points = points;
    }

    public String getMonth() {
        return month;
    }

    public long getPoints() {
        return points;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setPoints(long points) {
        this.points = points;
    }
}