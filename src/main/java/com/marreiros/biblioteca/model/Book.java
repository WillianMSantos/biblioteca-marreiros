package com.marreiros.biblioteca.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.annotation.Generated;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "book")
public class Book {
	
	@Id
	@Field("id")
	private Integer id;

	@Field("name")
	private String name;

	@Field("author")
	private String author;

	@Field("rented")
	private Boolean rented = false;

	@Field("details")
	private String details;
}
