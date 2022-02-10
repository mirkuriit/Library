package com.example.library;

public class Book {
    String author;
    String title;

    public Book(String author, String title) {
        this.author = author;
        this.title = title;
    }

    @Override
    public String toString() {
        return  author  + ", " + title;
    }
}
