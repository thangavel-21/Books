package com.springboot.h2db;

import com.springboot.h2db.model.BookModel;
import com.springboot.h2db.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepo;

    @GetMapping("/books")
    public ResponseEntity<List<BookModel>> getAllBooks() {
        try {
            List<BookModel> bookList = new ArrayList<>(bookRepo.findAll());
            if (bookList.isEmpty()) {
                return new ResponseEntity<>(bookList, HttpStatus.OK);
            }
            return new ResponseEntity<>(bookList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    public ResponseEntity<BookModel> saveBook(BookModel book) {
        BookModel bookObj = bookRepo.save(book);
        return new ResponseEntity<>(bookObj, HttpStatus.OK);
    }

    public ResponseEntity<BookModel> getBookById(Long id) {
        try {
            Optional<BookModel> bookData = bookRepo.findById(id);
            return bookData.map(bookModel -> new ResponseEntity<>(bookModel, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<BookModel> updateBook(Long id, BookModel newBookData) {
        Optional<BookModel> oldBookData = bookRepo.findById(id);
        if (oldBookData.isPresent()) {
            BookModel updatedBookData = oldBookData.get();
            updatedBookData.setBookName(newBookData.getBookName());
            updatedBookData.setBookAuthor(newBookData.getBookAuthor());

            BookModel bookObj = bookRepo.save(updatedBookData);

            return new ResponseEntity<>(bookObj, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<BookModel> deleteBookById(Long id) {
        Optional<BookModel> bookData = bookRepo.findById(id);
        if (bookData.isPresent()) {
            bookRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
