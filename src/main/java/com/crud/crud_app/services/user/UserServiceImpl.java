package com.crud.crud_app.services.user;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crud.crud_app.entities.Role;
import com.crud.crud_app.entities.User;
import com.crud.crud_app.exceptions.ApiErrorException;
import com.crud.crud_app.repositories.IRoleRepository;
import com.crud.crud_app.repositories.IUserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    @Transactional
    public void initializeRoles() {
        try {
            if (roleRepository.findByName("ROLE_USER").isEmpty()) {
                Role roleUser = new Role();
                roleUser.setName("ROLE_USER");
                roleRepository.save(roleUser);
            }

            if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
                Role roleAdmin = new Role();
                roleAdmin.setName("ROLE_ADMIN");
                roleRepository.save(roleAdmin);
            }
        } catch (Exception e) {
            throw new ApiErrorException("Error initializing roles: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    @Transactional
    public User save(User user) {
        if (user == null)
            throw new ApiErrorException("User cannot be null", HttpStatus.BAD_REQUEST.value());

        Set<Role> roles = new HashSet<>();

        // Añadir ROLE_USER
        roleRepository.findByName("ROLE_USER").ifPresentOrElse(
                roles::add,
                () -> {
                    throw new ApiErrorException("Role 'ROLE_USER' not found", HttpStatus.INTERNAL_SERVER_ERROR.value());
                });

        // Añadir ROLE_ADMIN si es admin
        if (user.isAdmin()) {
            roleRepository.findByName("ROLE_ADMIN").ifPresentOrElse(
                    roles::add,
                    () -> {
                        throw new ApiErrorException("Role 'ROLE_ADMIN' not found",
                                HttpStatus.INTERNAL_SERVER_ERROR.value());
                    });
        }

        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        try {
            return userRepository.save(user);
        } catch (DataAccessException e) {
            throw new ApiErrorException("Error saving user: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public boolean existByUsername(String username) {
        return userRepository.existByUsername(username);
    }
}
