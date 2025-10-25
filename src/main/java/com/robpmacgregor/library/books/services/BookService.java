package com.robpmacgregor.library.books.services;

import com.robpmacgregor.library.books.models.Book;

import java.util.List;
import java.util.Map;

public interface BookService {
    public Book getBookById(long id);

    public List<Book> findBooksBy(Map<String, String> searchParams);
}
