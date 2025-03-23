package com.usman.onlinebookstore.models.entities;

import jakarta.persistence.Id;

import com.usman.onlinebookstore.enums.Genre;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Genre genre;
    private String isbn;
    private String author;
    private int year;
    private double price;

    public Book() {}

    public Book(String title, Genre genre, String isbn, String author, int year, double price) {
        this.title = title;
        this.genre = genre;
        this.isbn = isbn;
        this.author = author;
        this.year = year;
        this.price = price;
    }

    public Long getId() { return id; }
    public void setId(Long id){ this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Genre getGenre() { return genre; }
    public void setGenre(Genre genre) { this.genre = genre; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public double getPrice(){ return price; }
    public void setPrice(double price){ this.price = price; }
}
