package com.crud.crud_app.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.crud.crud_app.entities.Role;

public interface IRoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByName(String name);

}
