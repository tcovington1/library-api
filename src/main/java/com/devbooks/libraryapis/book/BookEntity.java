package com.devbooks.libraryapis.book;

import com.devbooks.libraryapis.author.Author;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="BOOK")
public class BookEntity {

    @Column(name="Book_Id")
    @Id
    private Integer bookId;

    @Column(name="ISBN")
    private String isbn;

    @Column(name="Title")
    private String title;

    @Column(name="Publisher_Id")
    private Integer publisherId;

    @Column(name="Year_Published")
    private Integer yearPublished;

    @Column(name="Edition")
    private String edition;

//    private BookStatus bookStatus;

    private Set<Author> authors = new HashSet<>();
}
