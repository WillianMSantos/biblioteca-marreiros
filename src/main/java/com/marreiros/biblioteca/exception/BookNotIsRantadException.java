package com.marreiros.biblioteca.exception;

public class BookNotIsRantadException extends RuntimeException {
	public BookNotIsRantadException(){ super("Livro não está alugado");}
}
