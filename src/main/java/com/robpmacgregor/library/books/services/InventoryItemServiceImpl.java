package com.robpmacgregor.library.books.services;

import com.robpmacgregor.library.books.models.Book;
import com.robpmacgregor.library.books.models.InventoryItem;
import com.robpmacgregor.library.books.repositories.BookRepository;
import com.robpmacgregor.library.books.repositories.InventoryItemRepository;
import com.robpmacgregor.library.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class InventoryItemServiceImpl implements InventoryItemService{
    @Autowired
    InventoryItemRepository inventoryItemRepository;

    @Override
    public InventoryItem getInventoryItemById (long id) {
        return inventoryItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("InventoryItem id %d could not be found", id)));
    }
}
