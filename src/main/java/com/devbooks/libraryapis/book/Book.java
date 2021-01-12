package com.devbooks.libraryapis.book;

import com.devbooks.libraryapis.author.Author;

import java.util.HashSet;
import java.util.Set;

public class Book {

    private Integer bookId;

    private String isbn;

    private String title;

    private Integer publisherId;

    private Integer yearPublished;

    private String edition;

//    private BookStatus bookStatus;

    private Set<Author> authors = new HashSet<>();

    public Book() {
    }

    public Book(Integer bookId,
                String isbn, String title, Integer publisherId,
                Integer yearPublished, String edition, Set<Author> authors) {
        this.bookId = bookId;
        this.isbn = isbn;
        this.title = title;
        this.publisherId = publisherId;
        this.yearPublished = yearPublished;
        this.edition = edition;
        this.authors = authors;
    }

//    BookStatus bookStatus
    public Book(int bookId, String isbn, String title, int publisherid, int yearPublished, String edition
                ) {
        this.bookId = bookId;
        this.isbn = isbn;
        this.title = title;
        this.publisherId = publisherId;
        this.yearPublished = yearPublished;
        this.edition = edition;
//        this.bookStatus = bookStatus;
    }

//    BookStatus bookStatus
    public Book(String isbn, String title, Integer publisherId, int yearPublished, String edition) {
        this.isbn = isbn;
        this.title = title;
        this.publisherId = publisherId;
        this.yearPublished = yearPublished;
        this.edition = edition;
//        this.bookStatus = bookStatus;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public Integer getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(Integer yearPublished) {
        this.yearPublished = yearPublished;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

//    public BookStatus getBookStatus() {
//        return bookStatus;
//    }
//
//    public void setBookStatus(BookStatus bookStatus) {
//        this.bookStatus = bookStatus;
//    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }
//    Book_Id	 INT PRIMARY KEY,
//    ISBN VARCHAR(50) NOT NULL UNIQUE,
//    Title VARCHAR(50) NOT NULL,
//    Publisher_Id INT NOT NULL,
//    Year_Published INT NOT NULL,
//    Edition VARCHAR(20),
//    FOREIGN KEY fk_publisher(Publisher_Id) REFERENCES PUBLISHER(Publisher_Id)
}
