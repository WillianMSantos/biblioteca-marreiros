package com.marreiros.biblioteca.exception;

public class IsbnInvalidException extends RuntimeException {
    public IsbnInvalidException() {super("Isbn só pode ser digitado dados numéricos");}
}
