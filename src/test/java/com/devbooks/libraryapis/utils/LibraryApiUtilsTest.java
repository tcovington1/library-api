package com.devbooks.libraryapis.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class LibraryApiUtilsTest {

    @Test
    public void doesStringValueExist() {
        assertTrue(LibraryApiUtils.doesStringValueExist("ValueExists"));
        assertFalse(LibraryApiUtils.doesStringValueExist(""));
        assertFalse(LibraryApiUtils.doesStringValueExist(null));
    }
}