package com.usman.onlinebookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import com.usman.onlinebookstore.enums.Genre;
import com.usman.onlinebookstore.models.entities.Book;
import com.usman.onlinebookstore.repositories.BookRepository;

@SpringBootApplication
@EnableJpaRepositories
@ConfigurationPropertiesScan
public class OnlinebookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlinebookstoreApplication.class, args);
	}

	@Bean
    CommandLineRunner initDatabase(@Autowired BookRepository bookRepository) {
        return args -> {
            long count = bookRepository.count();
            if(count == 0){
                bookRepository.saveAll(List.of(
                    new Book("The Alchemist", Genre.FICTION, "978-3-16-148410-0", "Paulo Coelho", 1988, 500),
                    new Book("Gone Girl", Genre.THRILLER, "978-0-06-236873-3", "Gillian Flynn", 2012, 500),
                    new Book("The Girl with the Dragon Tattoo", Genre.MYSTERY, "978-0-307-45455-6", "Stieg Larsson", 2005, 500),
                    new Book("The Raven", Genre.POETRY, "978-1-59308-306-6", "Edgar Allan Poe", 1845, 500),
                    new Book("Dracula", Genre.HORROR, "978-0-451-53163-0", "Bram Stoker", 1897, 500),
                    new Book("Animal Farm", Genre.SATIRE, "978-0-452-28424-1", "George Orwell", 1945, 500),
                    new Book("It", Genre.HORROR, "978-1-5011-2809-9", "Stephen King", 1986, 500),
                    new Book("The Catcher in the Rye", Genre.FICTION, "978-0-316-76948-0", "J.D. Salinger", 1951, 500),
                    new Book("The Da Vinci Code", Genre.THRILLER, "978-0-385-50420-1", "Dan Brown", 2003, 500),
                    new Book("Inferno", Genre.THRILLER, "978-0-385-53785-8", "Dan Brown", 2013, 500)
                ));
            }
        };
    }

}
