package com.example.gamecollection;

public class Game {
    private String name;
    private String imagePath;
    private int year;

    public Game(String name, String imagePath, int year) {
        this.name = name;
        this.imagePath = imagePath;
        this.year = year;
    }
    public Game() {
        this.name = "";
        this.imagePath = "";
        this.year = 0;
    }

    public String getName() {
        return name;
    }
    public String getImagePath() {
        return imagePath;
    }
    public int getYear() {
        return year;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public void setYear(int year) {
        this.year = year;
    }
}