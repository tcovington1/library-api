package com.devbooks.libraryapis.user;

import com.devbooks.libraryapis.exception.LibraryResourceAlreadyExistsException;
import com.devbooks.libraryapis.exception.LibraryResourceNotFoundException;
import com.devbooks.libraryapis.utils.LibraryApiUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(path="/v1/users")
@CrossOrigin("*")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> addUser(@Valid @RequestBody User user,
                                       @RequestHeader(value="Trace-Id", defaultValue = "") String traceId)
            throws LibraryResourceAlreadyExistsException {

        if(!LibraryApiUtils.doesStringValueExist(traceId)) {
            traceId = UUID.randomUUID().toString();
        }

        System.out.println(user);

        userService.addUser(user, traceId);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping(path="/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Integer userId,
                                       @RequestHeader(value="Trace-Id", defaultValue = "") String traceId)
            throws LibraryResourceNotFoundException {
        if(!LibraryApiUtils.doesStringValueExist(traceId)) {
            traceId = UUID.randomUUID().toString();
        }

        return new ResponseEntity<>(userService.getUser(userId, traceId), HttpStatus.OK);
    }

    @PutMapping(path = "/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Integer userId,
                                          @Valid @RequestBody User user,
                                          @RequestHeader(value="Trace-Id", defaultValue = "") String traceId)
            throws LibraryResourceNotFoundException {

        if(!LibraryApiUtils.doesStringValueExist(traceId)) {
            traceId = UUID.randomUUID().toString();
        }

        user.setUserId(userId);
        userService.updateUser(user, traceId);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId,
                                          @RequestHeader(value="Trace-Id", defaultValue = "") String traceId)
            throws LibraryResourceNotFoundException {

        if(!LibraryApiUtils.doesStringValueExist(traceId)) {
            traceId = UUID.randomUUID().toString();
        }

        userService.deleteUser(userId, traceId);

        return new ResponseEntity<>("User deleted", HttpStatus.ACCEPTED);
    }

}
