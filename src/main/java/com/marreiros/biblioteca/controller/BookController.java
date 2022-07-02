package com.marreiros.biblioteca.controller;

import com.marreiros.biblioteca.dto.request.BookRequestDto;
import com.marreiros.biblioteca.exception.InvalidDataException;
import com.marreiros.biblioteca.model.Book;
import com.marreiros.biblioteca.service.BooksService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/books")
public class BookController {

	@Autowired
	public BooksService booksService;
	
	@PostMapping("/register")
	@ApiOperation("Cadastrar livro")
	@ResponseStatus(HttpStatus.CREATED)
	public Integer saveBook(@RequestBody BookRequestDto bookRequestDto) throws InvalidDataException {
		Book book = booksService.saveBook(bookRequestDto);
		return book.getId();
	}
	
	@GetMapping("/{name}/find-name")
	@ApiOperation("Procurar livro pelo nome")
	public Book findByBookName(@PathVariable String name) {
		return booksService.findByBookForName(name);
	}
	
	@GetMapping("/{id}/find-id")
	@ApiOperation("Procurar livro pelo ID")
	public Book findByBookForId(@PathVariable Integer id) {
		return booksService.findBookForId(id);
	}
	
	@DeleteMapping("/{id}/book")
	@ApiOperation("Excluir livro")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteForId(@PathVariable Integer id) {
		 booksService.deleteBook(id);
	}
	
	@PostMapping("/{id}/ranted")
	@ApiOperation("Alugar livro")
	public String rantedBook(@PathVariable Integer id) {
		booksService.rentedBook(id);
		return "Livro alugado com sucesso";
	}
	
	@PostMapping("/{id}/give-back")
	@ApiOperation("Devolver livro")
	public String giveBackBook(@PathVariable Integer id) {
		booksService.giveBackBook(id);
		return "Livro devolvido com sucesso";
	}
	
	@GetMapping("/list-books")
	@ApiOperation("Listar livros")
	public List<Book> findAllBooks() {
		return booksService.findAllBooks();
	}
	
	@PutMapping("/{id}/update")
	@ApiOperation("Atualizar livro")
	public String updateBook(@PathVariable Integer id, @RequestBody Book bookUpdate) {
		booksService.update(id, bookUpdate);
		return "Livro atualziado com sucesso";
	}
}
