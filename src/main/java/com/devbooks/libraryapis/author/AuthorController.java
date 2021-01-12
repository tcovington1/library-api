package com.devbooks.libraryapis.author;

import com.devbooks.libraryapis.exception.LibraryResourceAlreadyExistsException;
import com.devbooks.libraryapis.exception.LibraryResourceBadRequestException;
import com.devbooks.libraryapis.exception.LibraryResourceNotFoundException;
import com.devbooks.libraryapis.utils.LibraryApiUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(path="/v1/authors")
@CrossOrigin("*")
public class AuthorController {

    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(path="/{authorId}")
    public ResponseEntity<?> getAuthor(@PathVariable Integer authorId,
                                       @RequestHeader(value="Trace-Id", defaultValue = "") String traceId)
            throws LibraryResourceNotFoundException {
        if(!LibraryApiUtils.doesStringValueExist(traceId)) {
            traceId = UUID.randomUUID().toString();
        }

        return new ResponseEntity<>(authorService.getAuthor(authorId, traceId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addAuthor(@Valid @RequestBody Author author,
                                          @RequestHeader(value="Trace-Id", defaultValue = "") String traceId)
            throws LibraryResourceAlreadyExistsException {

        if(!LibraryApiUtils.doesStringValueExist(traceId)) {
            traceId = UUID.randomUUID().toString();
        }

        authorService.addAuthor(author, traceId);

        return new ResponseEntity<>(author, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{authorId}")
    public ResponseEntity<?> updateAuthor(@PathVariable Integer authorId,
                                             @Valid @RequestBody Author author,
                                             @RequestHeader(value="Trace-Id", defaultValue = "") String traceId)
            throws LibraryResourceNotFoundException {

        if(!LibraryApiUtils.doesStringValueExist(traceId)) {
            traceId = UUID.randomUUID().toString();
        }

        author.setAuthorId(authorId);
        authorService.updateAuthor(author, traceId);

        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{authorId}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Integer authorId,
                                             @RequestHeader(value="Trace-Id", defaultValue = "") String traceId)
            throws LibraryResourceNotFoundException {

        if(!LibraryApiUtils.doesStringValueExist(traceId)) {
            traceId = UUID.randomUUID().toString();
        }

        authorService.deleteAuthor(authorId, traceId);

        return new ResponseEntity<>("Author deleted", HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/search")
    public ResponseEntity<?> searchAuthor(@RequestParam String firstName, @RequestParam String lastName,
                                             @RequestHeader(value="Trace-Id", defaultValue = "") String traceId)
            throws LibraryResourceBadRequestException {

        if(!LibraryApiUtils.doesStringValueExist(traceId)) {
            traceId = UUID.randomUUID().toString();
        }

        if(!LibraryApiUtils.doesStringValueExist(firstName) && !LibraryApiUtils.doesStringValueExist(lastName)) {
            throw new LibraryResourceBadRequestException(traceId, "Please enter a name to search Author.");
        }

        return new ResponseEntity<>(authorService.searchAuthor(firstName, lastName, traceId), HttpStatus.OK);
    }

}
