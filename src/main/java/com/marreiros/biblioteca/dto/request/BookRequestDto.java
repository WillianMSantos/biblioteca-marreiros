package com.marreiros.biblioteca.dto.request;

import lombok.Data;

@Data
public class BookRequestDto {

	private Integer id;
	private String name;
	private String author;
	private String isbn;
	private boolean rented = false;
	private String details;

	public boolean isNullOrEmpty() {
		return this.getId() == null || this.getIsbn() == null || this.getName() == null || this.author == null;
	}
}
