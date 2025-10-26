package com.robpmacgregor.library.books.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private LocalDate releaseDate;
    private String genre;
    private String category;
    private String synopsis;

    @OneToMany(mappedBy = "book")
    private List<InventoryItem> inventoryItems;

    public Book() {}

    public Book(String title, String author, String publisher, LocalDate releaseDate, String genre, String category, String synopsis) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.category = category;
        this.synopsis = synopsis;
    }

    public Book(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public static Book createFromParams(Map<String, String> params) {
        Book book = new Book();

        if (params.containsKey("category")) {
            book.setCategory(params.get("category"));
        }
        if (params.containsKey("genre")) {
            book.setGenre(params.get("genre"));
        }
        if (params.containsKey("publisher")) {
            book.setPublisher(params.get("publisher"));
        }
        return book;
    }
}
