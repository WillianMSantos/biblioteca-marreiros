package com.marreiros.biblioteca.exception;

public class BookIsRantadException extends RuntimeException {
	public BookIsRantadException(){ super("Livro está alugado");}
}
