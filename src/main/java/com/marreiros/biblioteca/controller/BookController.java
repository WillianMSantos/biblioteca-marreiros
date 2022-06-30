package com.marreiros.biblioteca.controller;

import com.marreiros.biblioteca.dto.request.BookRequestDto;
import com.marreiros.biblioteca.exception.InvalidDataException;
import com.marreiros.biblioteca.model.Book;
import com.marreiros.biblioteca.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/books")
public class BookController {

	@Autowired
	public BooksService booksService;
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public Integer saveBook(@RequestBody @Valid BookRequestDto bookRequestDto) throws InvalidDataException {
		Book book = booksService.saveBook(bookRequestDto);
		return book.getId();
	}
	
	@GetMapping("/{name}/find-name")
	public Book findByBookName(@PathVariable String name) {
		return booksService.findByBookForName(name);
	}
	
	@GetMapping("/{id}/find-id")
	public Book findByBookForId(@PathVariable Integer id) {
		return booksService.findBookForId(id);
	}
	
	@DeleteMapping("/{id}/book")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteForId(@PathVariable Integer id) {
		 booksService.deleteBook(id);
	}
	
	@PostMapping("/{id}/ranted")
	public String rantedBook(@PathVariable Integer id) {
		booksService.rentedBook(id);
		return "Livro alugado com sucesso";
	}
	
	@PostMapping("/{id}/give-back")
	public String giveBackBook(@PathVariable Integer id) {
		booksService.giveBackBook(id);
		return "Livro devolvido com sucesso";
	}
	
	@GetMapping("/list-books")
	public List<Book> findAllBooks() {
		return booksService.findAllBooks();
	}
	
	@PutMapping("/{id}/update")
	public String updateBook(@PathVariable Integer id, @RequestBody Book bookUpdate) {
		booksService.update(id, bookUpdate);
		return "Livro atualziado com sucesso";
	}
	
}
