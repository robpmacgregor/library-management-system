package com.robpmacgregor.library.books.controllers;

import com.robpmacgregor.library.books.models.Book;
import com.robpmacgregor.library.books.models.InventoryItem;
import com.robpmacgregor.library.books.services.BookService;
import com.robpmacgregor.library.books.services.InventoryItemService;
import com.robpmacgregor.library.exceptions.ResourceNotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/inventoryItem")
public class InventoryItemController
{
    @Autowired
    InventoryItemService inventoryItemService;

    private static final Log log = LogFactory.getLog(InventoryItemController.class);

    @GetMapping("/{id}")
    public ResponseEntity<InventoryItem> get(@PathVariable long id) {
        InventoryItem inventoryItem = inventoryItemService.getInventoryItemById(id);
        log.info("InventoryItem: " + inventoryItem.getId() + " returned");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventoryItem);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException e) {
        log.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }
}
