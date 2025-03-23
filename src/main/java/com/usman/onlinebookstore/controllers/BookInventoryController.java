package com.usman.onlinebookstore.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.usman.onlinebookstore.enums.Genre;
import com.usman.onlinebookstore.models.entities.Book;
import com.usman.onlinebookstore.services.interfaces.IBookService;

import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookInventoryController {

    private final IBookService bookService;

    public BookInventoryController(IBookService bookService){
        this.bookService = bookService;
    }
    
    @GetMapping()
    public List<Book> getAll() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Optional<Book> getById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }
    
    @GetMapping("/search")
    public List<Book> searchByYear(@RequestParam(required = false) String title, @RequestParam(required = false) String author, @RequestParam(required = false) Integer year, @RequestParam(required = false) Genre genre) {
        return bookService.searchBooks(title,author,year,genre);
    }
}
