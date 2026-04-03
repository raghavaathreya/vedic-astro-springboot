package com.example.demo.model;

import lombok.Data;

@Data
public class BirthDetails {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private double latitude;
    private double longitude;
}