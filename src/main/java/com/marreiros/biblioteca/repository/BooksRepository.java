package com.marreiros.biblioteca.repository;

import com.marreiros.biblioteca.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BooksRepository extends JpaRepository<Book, Integer> {
	
	Optional<Book> findByName(String name);
	Optional<Book> findByAuthor(String Author);
}
