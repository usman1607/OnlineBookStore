package com.usman.onlinebookstore.services.implementations;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.usman.onlinebookstore.services.interfaces.IBookService;
import com.usman.onlinebookstore.enums.Genre;
import com.usman.onlinebookstore.models.entities.Book;
import com.usman.onlinebookstore.repositories.BookRepository;
import com.usman.onlinebookstore.services.BookSpecification;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements IBookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> searchBooks(String title, String author, Integer year, Genre genre) {
        Specification<Book> spec = BookSpecification.withFilters(title, author, year, genre);
        return bookRepository.findAll(spec);
    }
}
