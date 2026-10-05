package com.libraryManagement.main;

import com.libraryManagement.config.AppConfig;
import com.libraryManagement.enums.BookStatus;
import com.libraryManagement.enums.Genre;
import com.libraryManagement.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        BookService bookService = context.getBean(BookService.class);

        String title = "The Silent Harbor";
        Genre genre = Genre.FICTION;
        BookStatus bookStatus = BookStatus.AVAILABLE;
        long authorId = 1;
        Long memberId = null;
        int publishedYear = 2015;

        bookService.save(title, genre, bookStatus, authorId, memberId, publishedYear);
        System.out.println("Book is saved successfully.");
    }
}
