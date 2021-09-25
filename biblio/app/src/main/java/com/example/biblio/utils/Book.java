package com.example.biblio.utils;

public class Book {
    String title;
    String url_img;
    String url_file;

    public Book() {
    }

    public Book(String title, String url_img, String url_file) {
        this.title = title;
        this.url_img = url_img;
        this.url_file = url_file;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl_img() {
        return url_img;
    }

    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }

    public String getUrl_file() {
        return url_file;
    }

    public void setUrl_file(String url_file) {
        this.url_file = url_file;
    }
}
