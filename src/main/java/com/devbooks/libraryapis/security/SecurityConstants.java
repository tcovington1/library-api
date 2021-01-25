package com.devbooks.libraryapis.security;

public class SecurityConstants {

    public static final long EXPIRATION_TIME = 1000000;
    public static final String SIGNING_SECRET = "APISecret";
    public static final String NEW_USER_DEFAULT_PASSWORD = "Password123";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_TOKEN_PREFIX = "Bearer  a";
    public static final String NEW_USER_REGISTRATION_URL = "/v1/users";
}
