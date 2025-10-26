package com.robpmacgregor.library.books.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Map;

@Entity
public class InventoryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @Enumerated(EnumType.STRING)
    private InventoryItemStatus status;

    public InventoryItem() {}

    public InventoryItemStatus getStatus() {
        return status;
    }

    public void setStatus(InventoryItemStatus status) {
        this.status = status;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
