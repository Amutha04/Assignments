package com.libraryManagement.main;

import com.libraryManagement.config.AppConfig;
import com.libraryManagement.dto.BookRespDto;
import com.libraryManagement.enums.BookStatus;
import com.libraryManagement.enums.Genre;
import com.libraryManagement.exception.ResourceNotFoundException;
import com.libraryManagement.model.Book;
import com.libraryManagement.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        BookService bookService = context.getBean(BookService.class);
/*
        String title = "The Life of a Visionary";
        Genre genre = Genre.BIOGRAPHY;
        BookStatus bookStatus = BookStatus.LOST;
        long authorId = 5;
        Long memberId = null;
        int publishedYear = 2012;

        bookService.save(title, genre, bookStatus, authorId, memberId, publishedYear);
        System.out.println("Book is saved successfully.");


        int id = 3;
        try {
            Book book = bookService.findById(id);
            System.out.println(book);
        }
        catch (ResourceNotFoundException e){
            System.out.println(e.getMessage());
        }

 */
        try {
            List<BookRespDto> listDto = bookService.findAll();
            listDto.forEach(System.out :: println);
        }
        catch (ResourceNotFoundException e){
            System.out.println(e.getMessage());
        }

    }
}
