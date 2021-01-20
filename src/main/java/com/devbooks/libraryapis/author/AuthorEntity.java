package com.devbooks.libraryapis.author;

import com.devbooks.libraryapis.model.common.Gender;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="AUTHOR")
public class AuthorEntity {

    @Column(name="Author_Id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authorId_generator")
    @SequenceGenerator(name = "authorId_generator", sequenceName = "AUTHOR_SEQUENCE", allocationSize = 50)
    private Integer authorId;

    @Column(name="Date_Of_Birth")
    private LocalDate dateOfBirth;

    @Column(name="First_Name")
    private String firstName;

    @Column(name="Last_Name")
    private String lastName;

    @Column(name="Gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    public AuthorEntity() {
    }

    public AuthorEntity(String firstName, String lastName, LocalDate dateOfBirth, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
