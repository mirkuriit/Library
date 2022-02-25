package com.example.library;

public class Book {
    String author;
    String title;
    int year;
    long bookId;
    int coverId;

    public Book(String author, String title, int year, int coverId) {
        this.author = author;
        this.title = title;
        this.year = year;
        this.coverId = coverId;

    }

    @Override
    public String toString() {
        return  author  + ", " + title;
    }
}
