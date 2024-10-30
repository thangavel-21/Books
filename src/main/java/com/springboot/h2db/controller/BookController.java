package com.springboot.h2db.controller;

import com.springboot.h2db.BookService;
import com.springboot.h2db.model.BookModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping("/books")
    public ResponseEntity<List<BookModel>> getAllBooks() {
        return service.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookModel> getBook(@PathVariable Long id) {
        return service.getBookById(id);
    }

    @PostMapping("/book")
    public ResponseEntity<BookModel> saveBook(@RequestBody BookModel book) {
        return service.saveBook(book);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<BookModel> updateBook(@PathVariable Long id, @RequestBody BookModel newBookData) {
        return service.updateBook(id, newBookData);
    }

    @DeleteMapping("book/{id}")
    public ResponseEntity<BookModel> deleteBookById(@PathVariable Long id) {
        return service.deleteBookById(id);
    }
}

