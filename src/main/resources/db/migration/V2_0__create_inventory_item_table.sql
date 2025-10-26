CREATE TABLE inventory_item (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    book_id INT NOT NULL,
    status VARCHAR(50),
    FOREIGN KEY (book_id) REFERENCES book(id)
);