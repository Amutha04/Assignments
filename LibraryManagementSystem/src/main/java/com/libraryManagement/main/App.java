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
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        BookService bookService = context.getBean(BookService.class);

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Library Management ---");
            System.out.println("1. Save a book");
            System.out.println("2. Find book by id");
            System.out.println("3. Show all books");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    try {
                        sc.nextLine();
                        System.out.print("Title: ");
                        String title = sc.nextLine();

                        System.out.print("Genre (FICTION, NON_FICTION, SCIENCE, HISTORY, BIOGRAPHY): ");
                        Genre genre = Genre.valueOf(sc.nextLine().trim().toUpperCase());

                        System.out.print("Status (AVAILABLE, BORROWED, LOST): ");
                        BookStatus bookStatus = BookStatus.valueOf(sc.nextLine().trim().toUpperCase());

                        System.out.print("Author id: ");
                        long authorId = sc.nextLong();

                        Long memberId = null;
                        if(bookStatus == BookStatus.BORROWED) {
                            System.out.print("Borrowed id: ");
                            memberId = sc.nextLong();
                        }

                        System.out.print("Published year: ");
                        int publishedYear = sc.nextInt();

                        bookService.save(title, genre, bookStatus, authorId, memberId, publishedYear);
                        System.out.println("Book is saved successfully.");
                    }
                    catch (ResourceNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        System.out.print("Enter book id: ");
                        int id = sc.nextInt();

                        Book book = bookService.findById(id);
                        System.out.println(book);
                    } catch (ResourceNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        List<BookRespDto> listDto = bookService.findAll();
                        listDto.forEach(System.out::println);
                    } catch (ResourceNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    System.out.println("Exiting..!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please select 1 to 4.");
            }
        }

    }
}
