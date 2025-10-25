package com.robpmacgregor.library.books;

import com.robpmacgregor.library.books.models.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.Type;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
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

    @Test
    void testGetBookSearchByCategoryReturnsFictionCollection() {
        ResponseEntity<List<Book>> response =
                this.restTemplate.exchange(
                        "/api/book?category=fiction",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Book>>() {}
                );

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody().size(), equalTo(6));
        assertThat(response.getBody().get(0).getTitle(), equalTo("Tomorrow, and Tomorrow, and Tomorrow"));
    }

    @Test
    void testGetBookSearchByCategoryAndGenreReturnsKlara() {
        ResponseEntity<List<Book>> response =
                this.restTemplate.exchange(
                        "/api/book?category=fiction&genre=Dystopian / Science Fiction",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Book>>() {}
                );

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody().size(), equalTo(1));
        assertThat(response.getBody().get(0).getTitle(), equalTo("Klara and the Sun"));
    }

    @Test
    void testGetBookSearchByNonExistentCategoryReturnsEmptyCollection() {
        ResponseEntity<List<Book>> response =
                this.restTemplate.exchange(
                        "/api/book?category=Biography",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Book>>() {}
                );

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody().size(), equalTo(0));
    }
}
