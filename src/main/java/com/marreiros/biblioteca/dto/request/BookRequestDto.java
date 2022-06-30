package com.marreiros.biblioteca.dto.request;

public class BookRequestDto {
	
	private String name;
	private String author;
	private boolean rented = false;
	private String details;
	
	public String getName() {
		return name;
	}
	
	public void setName(final String name) {
		this.name = name;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(final String author) {
		this.author = author;
	}
	
	public boolean isRented() {
		return rented;
	}
	
	public void setRented(final boolean rented) {
		this.rented = rented;
	}
	
	public String getDetails() {
		return details;
	}
	
	public void setDetails(final String details) {
		this.details = details;
	}
	
	public boolean isNullOrEmpty() {
		return this.getName() == null || this.author == null;
	}
}
