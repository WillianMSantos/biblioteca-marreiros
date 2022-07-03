package com.marreiros.biblioteca.controller;

import com.marreiros.biblioteca.dto.request.BookRequestDto;
import com.marreiros.biblioteca.dto.response.BookResponseDto;
import com.marreiros.biblioteca.exception.InvalidDataException;
import com.marreiros.biblioteca.model.Book;
import com.marreiros.biblioteca.service.BooksService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("Biblioteca")
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
	public BookResponseDto findByBookName(@PathVariable String name) {
		return booksService.findBookDtoForName(name);
	}


	@GetMapping("/{isbn}/find-isbn")
	@ApiOperation("Procurar livro pelo ISBN")
	public BookResponseDto findByBookForIsbn(@PathVariable String isbn){
		return booksService.findBookDtoForIsbn(isbn);
	}
	
	@GetMapping("/{id}/find-id")
	@ApiOperation("Procurar livro pelo ID")
	public BookResponseDto findByBookForId(@PathVariable Integer id) {
		return booksService.findBookDtoForId(id);
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
	public List<BookResponseDto> findAllBooks() {
		return booksService.findAllBooks();
	}
	
	@PutMapping("/{id}/update")
	@ApiOperation("Atualizar livro")
	public String updateBook(@PathVariable Integer id, @RequestBody Book bookUpdate) {
		booksService.update(id, bookUpdate);
		return "Livro atualziado com sucesso";
	}
}
