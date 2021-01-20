package com.devbooks.libraryapis.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    UserEntity findByUsername(String username);

    List<UserEntity> findByFirstNameContaining(String firstName);

    List<UserEntity> findByLastNameContaining(String lastName);

    List<UserEntity> findByFirstNameAndLastNameContaining(String firstName, String lastName);
}