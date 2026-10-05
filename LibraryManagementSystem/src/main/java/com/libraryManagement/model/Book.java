package com.libraryManagement.model;

import com.libraryManagement.enums.BookStatus;
import com.libraryManagement.enums.Genre;
import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Enumerated(EnumType.STRING)
    private BookStatus status;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "published_year")
    private int publishedYear;

    public Book() {
    }

    public Book(long id, String title, Genre genre, BookStatus status, Author author, Member member, int publishedYear) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.status = status;
        this.author = author;
        this.member = member;
        this.publishedYear = publishedYear;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre=" + genre +
                ", status=" + status +
                ", author=" + author +
                ", member=" + member +
                ", publishedYear=" + publishedYear +
                '}';
    }
}
