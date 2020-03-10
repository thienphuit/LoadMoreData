package com.example.loadmoredatarest.model;

import java.io.Serializable;

public class Article implements Serializable {
    private String title;
    private String author;
    private String urlToImage;
    private String publishedAt;
    private String description;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getDescription() {
        return description;
    }
}
