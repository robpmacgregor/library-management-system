package com.robpmacgregor.library.books.services;

import com.robpmacgregor.library.books.models.Book;
import com.robpmacgregor.library.books.models.InventoryItem;

import java.util.List;
import java.util.Map;

public interface InventoryItemService {
    public InventoryItem getInventoryItemById(long id);
}
