package com.marreiros.biblioteca.service;

import com.marreiros.biblioteca.dto.request.BookRequestDto;
import com.marreiros.biblioteca.dto.response.BookResponseDto;
import com.marreiros.biblioteca.exception.*;
import com.marreiros.biblioteca.model.Book;
import com.marreiros.biblioteca.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BooksService {

	@Autowired
	private BooksRepository booksRepository;
	
	public Book saveBook(BookRequestDto bookRequestDto) throws InvalidDataException {
		
		if(bookRequestDto.isNullOrEmpty()) {
			throw new InvalidDataException();
		}
		
		if(booksRepository.findByName(bookRequestDto.getName()).isPresent() &&
		    booksRepository.findByAuthor(bookRequestDto.getAuthor()).isPresent() ||
			booksRepository.findByIsbn(bookRequestDto.getIsbn()).isPresent()) {
			throw new ExistingBookException();
		}
		
		Book book = new Book();
		book.setId(bookRequestDto.getId());
		book.setName(bookRequestDto.getName());
		book.setStatus("AVAILABLE");
		book.setAuthor(bookRequestDto.getAuthor());

		boolean isNumeric = verifyIsbnIsNumeric(bookRequestDto.getIsbn());

		if(isNumeric){
			book.setIsbn(bookRequestDto.getIsbn());
		}else {
			throw new IsbnInvalidException();
		}

		book.setDetails(bookRequestDto.getDetails());
		booksRepository.save(book);
		
		return book;
	}

	public BookResponseDto findBookDtoForName(String name) {
		Book book = findByBookForName(name);
		return BookResponseDto.toBookDto(book);
	}

	public Book findByBookForName(String name) {
		return booksRepository.findByName(name)
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Livro nao encontrado por esse nome"));

	}

	public BookResponseDto findBookDtoForId(Integer id) {
		Book book = findBookForId(id);
		return BookResponseDto.toBookDto(book);
	}

	public BookResponseDto findBookDtoForIsbn(String isbn){
		Book book = findByBookForIsbn(isbn);
		return BookResponseDto.toBookDto(book);

	}

	public Book findByBookForIsbn(String isbn) {
		return booksRepository.findByIsbn(isbn)
				      .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
							                                   "Livro não encontrado"));

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
				throw new BookIsRantadException();
			}
			
			book.setRented(true);
			book.setStatus("RENTED");
			booksRepository.save(book);
			return book;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
		                                                "Livro não encontrado"));
	}
	
	public void giveBackBook(Integer id) {
	
		booksRepository.findById(id).map(book -> {
			book.setId(id);
			
			if(book.getRented().equals(false)) {
				throw new BookNotIsRantadException();
			}
			
			book.setRented(false);
			book.setStatus("AVAILABLE");
			booksRepository.save(book);
			return book;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
		                                                 "Livro não encontrado"));
	}
	
	public List<BookResponseDto> findAllBooks() {
		List<Book> allBooks = booksRepository.findAll();

		return allBooks.stream()
				.map(BookResponseDto::toBookDto)
				.collect(Collectors.toList());
	}

	public Boolean verifyIsbnIsNumeric(String isbn) {
		return isbn.chars().allMatch(Character::isDigit);
	}



}