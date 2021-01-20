//package com.devbooks.libraryapis.author;
//
//import com.devbooks.libraryapis.exception.LibraryResourceAlreadyExistsException;
//import com.devbooks.libraryapis.testutils.LibraryApiTestUtil;
//import com.devbooks.libraryapis.testutils.TestConstants;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@RunWith(MockitoJUnitRunner.class)
//public class AuthorServiceTest {
//
//    @Mock
//    AuthorRepository authorRepository;
//
//    AuthorService authorService;
//    private Object DataIntegrityViolationException;
//
//
//    @Before
//    public void setUp() throws Exception {
//        authorService = new AuthorService(authorRepository);
//    }
//
//    @Test
//    public void addAuthor_sucess() throws LibraryResourceAlreadyExistsException {
//        when(authorRepository.save(any(AuthorEntity.class)))
//                .thenReturn((LibraryApiTestUtil.createAuthorEntity()));
//        Author author = LibraryApiTestUtil.createAuthor();
//        authorService.addAuthor(author, TestConstants.API_TRACE_ID);
//
//        verify(authorRepository, times(1)).save(any(AuthorEntity.class));
//        assertNotNull(author.getAuthorId());
//        assertTrue(author.getFirstName().equals(TestConstants.TEST_AUTHOR_FIRST_NAME));
//        assertTrue(author.getLastName().equals(TestConstants.TEST_AUTHOR_LAST_NAME));
//    }
//
//    @Test
//    public void getAuthor() {
//    }
//
//    @Test
//    public void updateAuthor() {
//    }
//
//    @Test
//    public void deleteAuthor() {
//    }
//
//    @Test
//    public void searchAuthor() {
//    }
//}