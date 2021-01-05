package com.devbooks.libraryapis.testutils;

import com.devbooks.libraryapis.publisher.Publisher;
import com.devbooks.libraryapis.publisher.PublisherEntity;

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
}
