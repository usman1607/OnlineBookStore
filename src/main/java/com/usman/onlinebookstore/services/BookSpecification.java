package com.usman.onlinebookstore.services;

import java.util.ArrayList;

import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;

import com.usman.onlinebookstore.enums.Genre;
import com.usman.onlinebookstore.models.entities.Book;

import java.util.List;

public class BookSpecification {
    public static Specification<Book> withFilters(String title, String author, Integer year, Genre genre) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (title != null && !title.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%%" + title.toLowerCase() + "%%"));
            }
            if (author != null && !author.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("author")), "%%" + author.toLowerCase() + "%%"));
            }
            if (year != null) {
                predicates.add(criteriaBuilder.equal(root.get("year"), year));
            }
            if (genre != null) {
                predicates.add(criteriaBuilder.equal(root.get("genre"), genre));
            }
            
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
