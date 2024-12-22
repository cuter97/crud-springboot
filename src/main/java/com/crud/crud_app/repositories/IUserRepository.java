package com.crud.crud_app.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.crud.crud_app.entities.User;

public interface IUserRepository extends CrudRepository<User, Long> {
    boolean existByUsername(String username);
    Optional<User> findByUsername(String username);
}
