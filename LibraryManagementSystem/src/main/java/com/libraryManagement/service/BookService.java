package com.libraryManagement.service;

import com.libraryManagement.enums.BookStatus;
import com.libraryManagement.enums.Genre;
import com.libraryManagement.exception.ResourceNotFoundException;
import com.libraryManagement.model.Author;
import com.libraryManagement.model.Book;
import com.libraryManagement.model.Member;
import com.libraryManagement.repository.AuthorRepository;
import com.libraryManagement.repository.BookRepository;
import com.libraryManagement.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final MemberRepository memberRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, MemberRepository memberRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void save(String title, Genre genre, BookStatus bookStatus, long authorId, Long memberId, int publishedYear) {
        // Step 1 : create Author and Member Object
        Optional<Author> optionalAuthor = authorRepository.getAuthorById(authorId);
        if(optionalAuthor.isEmpty())
            throw new ResourceNotFoundException("Invalid Author Id");
        Author author = optionalAuthor.get();

        Member member = memberId == null ? null : memberRepository.getMemberById(memberId);

        // Step 2 : create book Object
        Book book = new Book(title, genre, bookStatus, author, member, publishedYear);

        // Step 4 : send book object to repository
        bookRepository.save(book);
    }

    public Book findById(int id) {
        Book book = bookRepository.findById(id);
        if(book == null)
            throw new ResourceNotFoundException("Invalid Book ID");
        return book;
    }
}
