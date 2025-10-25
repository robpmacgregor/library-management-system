package com.robpmacgregor.library.books.controllers;

import com.robpmacgregor.library.books.models.Book;
import com.robpmacgregor.library.books.services.BookService;
import com.robpmacgregor.library.exceptions.ResourceNotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/book")
public class BooksController
{
    @Autowired
    BookService bookService;

    private static final Log log = LogFactory.getLog(BooksController.class);

    @GetMapping("/{id}")
    public ResponseEntity<Book> get(@PathVariable long id) {
        Book book = bookService.getBookById(id);
        log.info("Book title: " + book.getTitle()+ " returned");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(book);
    }


    @GetMapping("")
    public ResponseEntity<List<Book>> search(@RequestParam Map<String, String> params) {
        Map<String, String> searchParams = new HashMap<>(params);

        Set<String> validParams = Set.of("category", "genre", "publisher");
        searchParams.keySet().retainAll(validParams);
        List<Book> bookList = bookService.findBooksBy(searchParams);
        log.info(bookList.size() + " books returned");

        return ResponseEntity.ok(bookList);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException e) {
        log.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }
}
