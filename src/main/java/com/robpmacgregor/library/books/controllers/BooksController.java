package com.robpmacgregor.library.books.controllers;

import com.robpmacgregor.library.books.models.Book;
import com.robpmacgregor.library.books.services.BookService;
import com.robpmacgregor.library.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book")
public class BooksController
{
    @Autowired
    BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<Book> get(@PathVariable long id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(book);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }
}
