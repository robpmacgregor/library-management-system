package com.robpmacgregor.library.books.services;

import com.robpmacgregor.library.books.models.Book;
import com.robpmacgregor.library.books.repositories.BookRepository;
import com.robpmacgregor.library.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    BookRepository bookRepository;

    @Override
    public Book getBookById(long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Book id %d could not be found", id)));
    }
}
