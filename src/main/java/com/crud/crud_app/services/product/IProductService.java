package com.crud.crud_app.services.product;

import java.util.List;
import java.util.Optional;

import com.crud.crud_app.entities.Product;

public interface IProductService {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product save(Product product);
    Product update(Long id, Product product);
    void delete(Long id);
}
