package com.crud.crud_app.services.user;

import java.util.List;

import com.crud.crud_app.entities.User;

public interface IUserService {
    List<User> findAll();
    User save(User user);
    boolean existByUsername(String username);
}
