package com.marreiros.biblioteca.model;

import javax.persistence.*;


@Entity
@Table(name = "TB_BOOK")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "DS_NAME")
	private String name;
	
	@Column(name = "DS_AUTHOR")
	private String author;
	
	private Boolean rented = false;
	
	@Column(name = "DS_DETAILS")
	private String details;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(final Integer id) {
		this.id = id;
	}
	
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
	
	public Boolean getRented() {
		return rented;
	}
	
	public void setRented(final Boolean rented) {
		this.rented = rented;
	}
	
	public String getDetails() {
		return details;
	}
	
	public void setDetails(final String details) {
		this.details = details;
	}
}
