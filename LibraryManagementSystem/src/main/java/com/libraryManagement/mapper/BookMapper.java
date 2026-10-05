package com.libraryManagement.mapper;

import com.libraryManagement.dto.BookRespDto;
import com.libraryManagement.model.Book;

public class BookMapper {
    public static BookRespDto convertBookToDto(Book book) {
        return new BookRespDto(
                book.getId(),
                book.getTitle(),
                book.getGenre(),
                book.getStatus(),
                book.getAuthor().getName(),
                book.getMember() == null ?
                        null : book.getMember().getName(),
                book.getMember() == null ?
                        null : book.getMember().getEmail(),
                book.getMember() == null ?
                        null : book.getMember().getMembershipType(),
                book.getPublishedYear()
        );
    }
}
