package com.devbooks.libraryapis.testutils;

import com.devbooks.libraryapis.model.common.Gender;

import java.time.LocalDate;

public class TestConstants {

    public static final String API_TRACE_ID = "Test-Trace-ID";

    public static final String TEST_PUBLISHER_NAME = "TestPublisherName";
    public static final String TEST_PUBLISHER_EMAIL = "TestPublisherName@email.com";
    public static final String TEST_PUBLISHER_PHONE = "123-456-7890";

    public static final String TEST_PUBLISHER_EMAIL_UPDATED = "TestPublisherNEW@email.com";
    public static final String TEST_PUBLISHER_PHONE_UPDATED = "123-456-1111";

    public static final String TEST_AUTHOR_FIRST_NAME = "TestAuthorFirstName";
    public static final String TEST_AUTHOR_LAST_NAME = "TestAuthorLastName";
    public static final LocalDate TEST_AUTHOR_DOB = LocalDate.now();
    public static final Gender TEST_AUTHOR_GENDER = Gender.Male;

    public static final String TEST_AUTHOR_FIRST_NAME_UPDATED = "TestAuthorFirstNameUpdated";
    public static final String TEST_AUTHOR_LAST_NAME_UPDATED = "TestAuthorLastNameUpdated";
}
