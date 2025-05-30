package com.example.lab5_2.model;

public class Book {
    private int cover;
    private String title;
    private String description;
    private String category;

    public Book(int cover, String title, String description, String category) {
        this.cover = cover;
        this.title = title;
        this.description = description;
        this.category = category;
    }

    public int getCover() {
        return cover;
    }

    public void setCover(int cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
