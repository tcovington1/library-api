package com.devbooks.libraryapis.author;

import com.devbooks.libraryapis.exception.LibraryResourceAlreadyExistsException;
import com.devbooks.libraryapis.exception.LibraryResourceNotFoundException;
import com.devbooks.libraryapis.utils.LibraryApiUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void addAuthor(Author authorToBeAdded, String traceId)
            throws LibraryResourceAlreadyExistsException {

        AuthorEntity authorEntity = new AuthorEntity(
                authorToBeAdded.getFirstName(),
                authorToBeAdded.getLastName(),
                authorToBeAdded.getDateOfBirth(),
                authorToBeAdded.getGender()
        );
        AuthorEntity addedAuthor = null;

        try {
            addedAuthor = authorRepository.save(authorEntity);
        } catch (DataIntegrityViolationException e) {
            throw new LibraryResourceAlreadyExistsException(traceId, "Author already exists. See line 35 of AuthorService.");
        }

        authorToBeAdded.setAuthorId(addedAuthor.getAuthorId());
    }


    public Object getAuthor(Integer authorId, String traceId) throws LibraryResourceNotFoundException {

        Optional<AuthorEntity> authorEntity = authorRepository.findById(authorId);
        Author author = null;

        if( authorEntity.isPresent()) {
            AuthorEntity ae = authorEntity.get();
            author = createAuthorFromEntity(ae);
        } else {
            throw new LibraryResourceNotFoundException(traceId, "Author Id: " + authorId + " was not found");
        }

        return author;
    }

    public void updateAuthor(Author authorToBeUpdated, String traceId) throws LibraryResourceNotFoundException {

        Optional<AuthorEntity> authorEntity = authorRepository.findById(authorToBeUpdated.getAuthorId());
        Author author = null;

        if(authorEntity.isPresent()) {
//            PublisherEntity pe = publisherEntity.get();
            AuthorEntity ae = authorEntity.get();

            if(LibraryApiUtils.doesStringValueExist(authorToBeUpdated.getFirstName())) {
                ae.setFirstName(authorToBeUpdated.getFirstName());
            }

            if(LibraryApiUtils.doesStringValueExist(authorToBeUpdated.getLastName())) {
                ae.setLastName(authorToBeUpdated.getLastName());
            }

            authorRepository.save(ae);
            authorToBeUpdated = createAuthorFromEntity(ae);
        } else {
            throw new LibraryResourceNotFoundException(traceId, "Author Id: " + authorToBeUpdated.getAuthorId() + " was not found");
        }
    }

    public void deleteAuthor(Integer authorId, String traceId) throws LibraryResourceNotFoundException {

        try {
            authorRepository.deleteById(authorId);
        } catch (EmptyResultDataAccessException e) {
            throw new LibraryResourceNotFoundException(traceId, "Author Id: " + authorId + " was not found");
        }
    }

    public List<Author> searchAuthor(String firstName, String lastName, String traceId) {

        List<AuthorEntity> authorEntities = null;

        if(LibraryApiUtils.doesStringValueExist(firstName) && LibraryApiUtils.doesStringValueExist(lastName)) {
            authorEntities = authorRepository.findByFirstNameAndLastNameContaining(firstName, lastName);
        } else if(LibraryApiUtils.doesStringValueExist(firstName) && !LibraryApiUtils.doesStringValueExist(lastName)) {
            authorEntities = authorRepository.findByFirstNameContaining(firstName);
        } else if(LibraryApiUtils.doesStringValueExist(lastName) && !LibraryApiUtils.doesStringValueExist(firstName)) {
            authorEntities = authorRepository.findByLastNameContaining(lastName);
        }
        if(authorEntities != null && authorEntities.size() > 0 ) {
            return createAuthorsForSearchResponse(authorEntities);
        } else {
            return Collections.emptyList();
        }
    }

    private List<Author> createAuthorsForSearchResponse(List<AuthorEntity> authorEntities) {
        return authorEntities.stream()
                .map(ae -> new Author(ae.getAuthorId(), ae.getFirstName(), ae.getLastName(), ae.getDateOfBirth(), ae.getGender()))
                .collect(Collectors.toList());
    }

    private Author createAuthorFromEntity(AuthorEntity ae) {

        return new Author(ae.getAuthorId(), ae.getFirstName(), ae.getLastName(),
                ae.getDateOfBirth(), ae.getGender());
    }
}
