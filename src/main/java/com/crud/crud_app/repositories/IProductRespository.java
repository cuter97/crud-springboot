package com.crud.crud_app.repositories;

import org.springframework.data.repository.CrudRepository;

import com.crud.crud_app.entities.Product;

public interface IProductRespository extends CrudRepository<Product, Long> {
   
}
