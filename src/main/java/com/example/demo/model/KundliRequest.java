package com.example.demo.model;

public class KundliRequest {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private double latitude;
    private double longitude;

    // Getters and Setters
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    
    public int getMonth() { return month; }
    public void setMonth(int month) { this.month = month; }
    
    public int getDay() { return day; }
    public void setDay(int day) { this.day = day; }
    
    public int getHour() { return hour; }
    public void setHour(int hour) { this.hour = hour; }
    
    public int getMinute() { return minute; }
    public void setMinute(int minute) { this.minute = minute; }
    
    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }
    
    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
}