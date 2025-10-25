package com.robpmacgregor.library.books.services;

import com.robpmacgregor.library.books.models.Book;
import com.robpmacgregor.library.books.repositories.BookRepository;
import com.robpmacgregor.library.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    BookRepository bookRepository;

    @Override
    public Book getBookById(long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Book id %d could not be found", id)));
    }

    @Override
    public List<Book> findBooksBy(Map<String, String> searchParams) {
        Book probe = Book.createFromParams(searchParams);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase("category", "genre", "publisher")
                .withStringMatcher(ExampleMatcher.StringMatcher.EXACT);

        return bookRepository.findAll(Example.of(probe, matcher));
    }


}
