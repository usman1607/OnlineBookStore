package com.usman.onlinebookstore.services.interfaces;

import com.usman.onlinebookstore.enums.Genre;
import com.usman.onlinebookstore.models.entities.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    Optional<Book> getBookById(Long id);
    List<Book> getAllBooks();
    List<Book> searchBooks(String title, String author, Integer year, Genre genre);
}