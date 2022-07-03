package com.marreiros.biblioteca.dto.response;

import com.marreiros.biblioteca.model.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDto {

    private Integer id;
    private String name;
    private String author;
    private String isbn;
    private String status;
    private String details;

    public static BookResponseDto toBookDto(Book book) {

        BookResponseDto dto = BookResponseDto.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .status(book.getStatus())
                .isbn(book.getIsbn())
                .details(book.getDetails())
                .build();

        return dto;
    }
}
