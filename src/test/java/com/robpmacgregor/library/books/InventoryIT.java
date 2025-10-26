package com.robpmacgregor.library.books;

import com.robpmacgregor.library.books.models.InventoryItem;
import com.robpmacgregor.library.books.models.InventoryItemStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InventoryIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testGetInventoryItemReturnsCorrectInventoryResourceAndRelatedBook() {
        ResponseEntity<InventoryItem> response = this.restTemplate.getForEntity("/api/inventoryItem/1", InventoryItem.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody().getStatus(), equalTo(InventoryItemStatus.AVAILABLE));
        assertThat(response.getBody().getBook().getTitle(), equalTo("Tomorrow, and Tomorrow, and Tomorrow"));


    }

}
