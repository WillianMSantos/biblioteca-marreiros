package com.marreiros.biblioteca.service;

import com.marreiros.biblioteca.dto.request.BookRequestDto;
import com.marreiros.biblioteca.exception.BookIsRantadException;
import com.marreiros.biblioteca.exception.BookNotIsRantadException;
import com.marreiros.biblioteca.exception.ExistingBookException;
import com.marreiros.biblioteca.exception.InvalidDataException;
import com.marreiros.biblioteca.model.Book;
import com.marreiros.biblioteca.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BooksService {
	
	@Autowired
	private BooksRepository booksRepository;
	
	public Book saveBook(BookRequestDto bookRequestDto) throws InvalidDataException {
		
		if(bookRequestDto.isNullOrEmpty()) {
			throw new InvalidDataException();
		}
		
		if(booksRepository.findByName(bookRequestDto.getName()).isPresent() &&
		   booksRepository.findByAuthor(bookRequestDto.getAuthor()).isPresent()) {
			throw new ExistingBookException();
		}
		
		Book book = new Book();
		book.setId(bookRequestDto.getId());
		book.setName(bookRequestDto.getName());
		book.setAuthor(bookRequestDto.getAuthor());
		
		booksRepository.save(book);
		
		return book;
	}
	
	public Book findByBookForName(String name) {
		return booksRepository.findByName(name)
				       .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
				                                                     "Livro nao encontrado por esse nome"));
		
	}
	
	
	public Book findBookForId(Integer id) {
		return booksRepository.findById(id)
		                      .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
		                                                                    "Livro não encontrado"));
	}
	
	public void deleteBook(Integer id) {
		booksRepository.findById(id)
		               .map(book -> {
		               	if(book.getRented().equals(true)){
			                throw new BookIsRantadException();
		                }
		               	booksRepository.delete(book);
		               	return Void.TYPE;})
				       .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
				                                                     "Livro não encontrado pelo id"));
	}
	
	public void update(Integer id, Book bookUpdate) {
		
		booksRepository.findById(id).map(book -> {
			
			bookUpdate.setId(book.getId());
			
			if (book.getRented().equals(true)) {
				throw new BookIsRantadException();
			}
			
			booksRepository.save(bookUpdate);
			return bookUpdate;
			
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
		                                                 "Livro não encontrado"));
	}
	
	public void rentedBook(Integer id) {
		
		booksRepository.findById(id).map(book -> {
			book.setId(id);
			
			if(book.getRented().equals(true)) {
				throw new BookNotIsRantadException();
			}
			
			book.setRented(true);
			booksRepository.save(book);
			return book;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
		                                                "Livro não encontrado"));
	}
	
	public void giveBackBook(Integer id) {
	
		booksRepository.findById(id).map(book -> {
			book.setId(id);
			
			if(book.getRented().equals(false)) {
				throw new BookIsRantadException();
			}
			
			book.setRented(false);
			booksRepository.save(book);
			return book;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
		                                                 "Livro não encontrado"));
	}
	
	public List<Book> findAllBooks() {
		return booksRepository.findAll();
	}
}