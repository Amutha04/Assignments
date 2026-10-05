package com.libraryManagement.dto;

import com.libraryManagement.enums.BookStatus;
import com.libraryManagement.enums.Genre;
import com.libraryManagement.enums.MembershipType;

public record BookRespDto(
        long id,
        String title,
        Genre genre,
        BookStatus status,
        String authorName,
        String memberName,
        String memberEmail,
        MembershipType membershipType,
        int publishedYear
) {
}
