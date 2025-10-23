package com.robpmacgregor.library.books.repositories;

import com.robpmacgregor.library.books.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
}
