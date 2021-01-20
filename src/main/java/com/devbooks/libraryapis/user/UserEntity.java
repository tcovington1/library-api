package com.devbooks.libraryapis.user;

import com.devbooks.libraryapis.model.common.Gender;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="USER")
public class UserEntity {

    @Column(name="User_Id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userId_generator")
    @SequenceGenerator(name="userId_generator", sequenceName = "user_sequence", allocationSize=1)
    private Integer userId;

    @Column(name="First_Name")
    private String firstName;

    @Column(name="Last_Name")
    private String lastName;

    @Column(name="Date_Of_Birth")
    private LocalDate dateOfBirth;

    @Column(name="Phone_Number")
    private String phoneNumber;

    @Column(name="Email_Id")
    private String emailId;

    @Column(name="Gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name="Role")
    private String role;

    @Column(name="Username")
    private String username;

    @Column(name="Password")
    private String password;

    public UserEntity() {
    }

    public UserEntity(String username, String password, String firstName, String lastName, LocalDate dateOfBirth, Gender gender,
                      String phoneNumber, String emailId, String role) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
        this.role = role;
    }

    public Integer getUserId() {
        return userId;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Gender getGender() {
        return gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

//    User_Id	INT PRIMARY KEY,
//    Username VARCHAR(50) NOT NULL UNIQUE,
//    Password VARCHAR(100) NOT NULL,
//    First_Name VARCHAR(50) NOT NULL,
//    Last_Name VARCHAR(50) NOT NULL,
//    Date_Of_Birth DATE NOT NULL,
//    Gender ENUM('Male', 'Female', 'Undisclosed') NOT NULL,
//    Phone_Number VARCHAR(11) NOT NULL,
//    Email_Id VARCHAR(100) NOT NULL UNIQUE,
//    Role ENUM('ADMIN', 'USER') NOT NULL

