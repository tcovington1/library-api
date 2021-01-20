package com.devbooks.libraryapis.user;

import com.devbooks.libraryapis.exception.LibraryResourceAlreadyExistsException;
import com.devbooks.libraryapis.exception.LibraryResourceNotFoundException;
import com.devbooks.libraryapis.security.SecurityConstants;
import com.devbooks.libraryapis.utils.LibraryApiUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User userToBeAdded, String traceId)
            throws LibraryResourceAlreadyExistsException {

        UserEntity userEntity = new UserEntity(
               userToBeAdded.getUsername(),
                SecurityConstants.NEW_USER_DEFAULT_PASSWORD,
                userToBeAdded.getFirstName(),
                userToBeAdded.getLastName(),
                userToBeAdded.getDateOfBirth(),
                userToBeAdded.getGender(),
                userToBeAdded.getPhoneNumber(),
                userToBeAdded.getEmailId(),
                "USER" );

        userToBeAdded.setPassword(SecurityConstants.NEW_USER_DEFAULT_PASSWORD);
        UserEntity addedUser = null;

        try {
            addedUser = userRepository.save(userEntity);
        } catch (DataIntegrityViolationException e) {
            throw new LibraryResourceAlreadyExistsException(traceId, "Something went wrong. Please contact support.");
        }

        userToBeAdded.setUserId(addedUser.getUserId());
        userToBeAdded.setRole(Role.USER);
    }

    public Object getUser(Integer userId, String traceId) throws LibraryResourceNotFoundException {

        Optional<UserEntity> userEntity = userRepository.findById(userId);
        User user = null;

        if( userEntity.isPresent()) {

            UserEntity ue = userEntity.get();
            user = createUserFromEntityForLogin(ue);
        } else {
            throw new LibraryResourceNotFoundException(traceId, "User Id: " + userId + " was not found");
        }

        return user;
    }

    public void updateUser(User userToBeUpdated, String traceId) throws LibraryResourceNotFoundException {

        Optional<UserEntity> userEntity =  userRepository.findById(userToBeUpdated.getUserId());
        User user = null;

        if(userEntity.isPresent()) {
            UserEntity ue = userEntity.get();

            if(LibraryApiUtils.doesStringValueExist(userToBeUpdated.getFirstName())) {
                ue.setFirstName(userToBeUpdated.getFirstName());
            }

            if(LibraryApiUtils.doesStringValueExist(userToBeUpdated.getLastName())) {
                ue.setLastName(userToBeUpdated.getLastName());
            }

            userRepository.save(ue);
            userToBeUpdated = createUserFromEntityForLogin(ue);
        } else {
            throw new LibraryResourceNotFoundException(traceId, "User Id: " + userToBeUpdated.getUserId() + " was not found");
        }
    }

    public void deleteUser(Integer userId, String traceId) throws LibraryResourceNotFoundException {

        try {
            userRepository.deleteById(userId);
        } catch (EmptyResultDataAccessException e) {
            throw new LibraryResourceNotFoundException(traceId, "User Id: " + userId + " was not found");
        }
    }

    private User createUserFromEntityForLogin(UserEntity ue) {
        return new User(ue.getUserId(), ue.getUsername(), ue.getPassword(), ue.getFirstName(), ue.getLastName(),
                ue.getDateOfBirth(), ue.getGender(), ue.getPhoneNumber(), ue.getEmailId(), Role.valueOf(ue.getRole()));
    }

}
