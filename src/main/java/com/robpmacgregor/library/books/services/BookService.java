package com.robpmacgregor.library.books.services;

import com.robpmacgregor.library.books.models.Book;

public interface BookService {
    public Book getBookById(long id);
}
