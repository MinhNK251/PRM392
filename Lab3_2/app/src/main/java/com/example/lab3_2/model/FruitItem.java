package com.example.lab3_2.model;

public class FruitItem {
    int imageResId;
    String title;
    String description;

    public FruitItem(int imageResId, String title, String description) {
        this.imageResId = imageResId;
        this.title = title;
        this.description = description;
    }

    // Getters
    public int getImageResId() { return imageResId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
}
