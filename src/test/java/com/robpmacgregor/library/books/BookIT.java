package com.robpmacgregor.library.books;

import com.robpmacgregor.library.books.models.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testGettestGetBookReturns200IfFound() {
        ResponseEntity<Book> response = this.restTemplate.getForEntity("/api/book/1", Book.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }
    @Test
    void testGettestGetBookReturns404IfNotFound() {
        ResponseEntity<String> response = this.restTemplate.getForEntity("/api/book/0", String.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(), equalTo("Book id 0 could not be found"));
    }
}
