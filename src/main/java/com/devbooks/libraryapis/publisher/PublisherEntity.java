package com.devbooks.libraryapis.publisher;

import javax.persistence.*;

@Entity
@Table(name = "PUBLISHER")
public class PublisherEntity {

    @Column(name = "Publisher_Id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "publisherId_generator")
    @SequenceGenerator(name = "publisherId_generator", sequenceName = "PUBLISHER_SEQUENCE", allocationSize = 50)
    private int publisherId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Email_Id")
    private String emailId;

    @Column(name = "Phone_Number")
    private String phoneNumber;

    public PublisherEntity() {
    }

    public PublisherEntity(String name, String emailId, String phoneNumber) {
        this.name = name;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
    }

    public Integer getPublisherId() {
        return publisherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
