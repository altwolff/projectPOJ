package model;

import enums.Genre;

public class Book {
    private final String title;
    private final String author;
    private final String publisher;
    private final int year;
    private final int pages;
    private final Genre genre;
    private final String isbn;
    private boolean favourite;

    public Book(String title, String author, String publisher, int year, int pages, Genre genre, String isbn, boolean favourite) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.pages = pages;
        this.genre = genre;
        this.isbn = isbn;
        this.favourite = favourite;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getYear() {
        return year;
    }

    public int getPages() {
        return pages;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }
}