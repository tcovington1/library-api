package com.devbooks.libraryapis.testutils;

import com.devbooks.libraryapis.author.Author;
import com.devbooks.libraryapis.author.AuthorEntity;
import com.devbooks.libraryapis.model.common.Gender;
import com.devbooks.libraryapis.publisher.Publisher;
import com.devbooks.libraryapis.publisher.PublisherEntity;

import java.time.LocalDate;
import java.util.Optional;

public class LibraryApiTestUtil {

    public static Publisher createPublisher() {
        return new Publisher(null,
                TestConstants.TEST_PUBLISHER_NAME,
                TestConstants.TEST_PUBLISHER_EMAIL,
                TestConstants.TEST_PUBLISHER_PHONE);
    }

    public static PublisherEntity createPublisherEntity() {
        return new PublisherEntity(
                TestConstants.TEST_PUBLISHER_NAME,
                TestConstants.TEST_PUBLISHER_EMAIL,
                TestConstants.TEST_PUBLISHER_PHONE);
    }

    public static Optional<PublisherEntity> createPublisherEntityOptional() {
        return Optional.of(createPublisherEntity());
    }

    public static Author createAuthor() {
        return new Author(null, TestConstants.TEST_AUTHOR_FIRST_NAME,
                TestConstants.TEST_AUTHOR_LAST_NAME, LocalDate.now().minusYears(30), Gender.Female);
    }

    public static AuthorEntity createAuthorEntity() {
        return new AuthorEntity(TestConstants.TEST_AUTHOR_FIRST_NAME,
                TestConstants.TEST_AUTHOR_LAST_NAME, LocalDate.now().minusYears(30), Gender.Female);
    }

    public static Optional<AuthorEntity> createAuthorEntityOptional() {
        return Optional.of(createAuthorEntity());
    }
}
