package com.usman.onlinebookstore.serviceTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import com.usman.onlinebookstore.enums.Genre;
import com.usman.onlinebookstore.models.entities.Book;
import com.usman.onlinebookstore.repositories.BookRepository;
import com.usman.onlinebookstore.services.implementations.BookServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setYear(2022);
        book.setGenre(Genre.THRILLER);
    }

    @Test
    void testGetAllBooks() {
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book));
        List<Book> books = bookService.getAllBooks();
        assertFalse(books.isEmpty());
        assertEquals(1, books.size());
        assertEquals("Test Book", books.get(0).getTitle());
    }

    @Test
    void testGetBookById() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        Optional<Book> retrievedBook = bookService.getBookById(1L);
        assertTrue(retrievedBook.isPresent());
        assertEquals("Test Book", retrievedBook.get().getTitle());
    }

    @Test
    @SuppressWarnings("unchecked")
    void testSearchBooks() {
        when(bookRepository.findAll(any(Specification.class))).thenReturn(Arrays.asList(book));
        List<Book> books = bookService.searchBooks("Test Book", "Test Author", 2022, Genre.THRILLER);
        assertFalse(books.isEmpty());
        assertEquals(1, books.size());
        assertEquals("Test Book", books.get(0).getTitle());
    }

    @Test
    @SuppressWarnings("unchecked")
    void testSearchBooksWithEmptyFilters() {
        when(bookRepository.findAll(any(Specification.class))).thenReturn(Arrays.asList(book));
        List<Book> books = bookService.searchBooks(null, null, 0, null);
        assertFalse(books.isEmpty());
        assertEquals(1, books.size());
    }
}
