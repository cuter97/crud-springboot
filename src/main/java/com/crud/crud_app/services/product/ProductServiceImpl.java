package com.crud.crud_app.services.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crud.crud_app.entities.Product;
import com.crud.crud_app.exceptions.ApiErrorException;
import com.crud.crud_app.repositories.IProductRespository;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRespository productRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    @Override
    public Product save(Product product) {
        if (product == null)
            throw new ApiErrorException("Product cannot be null", HttpStatus.BAD_REQUEST.value());

        try {
            return productRepository.save(product);
        } catch (Exception e) {
            throw new ApiErrorException("Error saving product: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Transactional
    @Override
    public Product update(Long id, Product product) {
        productRepository.findById(id)
                .orElseThrow(
                        () -> new ApiErrorException("Product not found with ID: " + id, HttpStatus.NOT_FOUND.value()));

        product.setId(id);
        return productRepository.save(product);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ApiErrorException("Product not found with ID: " + id,
                        HttpStatus.BAD_REQUEST.value()));
        productRepository.delete(product);
    }

}
