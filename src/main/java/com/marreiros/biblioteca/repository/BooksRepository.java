package com.marreiros.biblioteca.repository;

import com.marreiros.biblioteca.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BooksRepository extends MongoRepository<Book, Integer> {
	
	Optional<Book> findByName(String name);
	Optional<Book> findByAuthor(String Author);
	Optional<Book> findByIsbn(String isbn);
}
