package com.marreiros.biblioteca.exception;

public class ExistingBookException extends RuntimeException {
	public ExistingBookException(){ super("Livro ja cadastrado");}
}
