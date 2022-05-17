package com.bookstore.controller;

import com.bookstore.domain.BookDto;
import com.bookstore.domain.ResponseWrapper;
import com.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<ResponseWrapper<List<BookDto>>> getAll() {
        return ResponseEntity.ok(bookService.getAllBook());
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<BookDto>> addBook(@Valid @RequestBody BookDto request) {
        return ResponseEntity.ok(bookService.createBook(request));
    }
}
