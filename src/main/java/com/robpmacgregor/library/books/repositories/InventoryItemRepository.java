package com.robpmacgregor.library.books.repositories;

import com.robpmacgregor.library.books.models.Book;
import com.robpmacgregor.library.books.models.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long>{

}
