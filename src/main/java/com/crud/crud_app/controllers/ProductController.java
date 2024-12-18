package com.crud.crud_app.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.crud.crud_app.entities.Product;
import com.crud.crud_app.services.IProductService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("/list-product")
    public List<Product> listProduct() {
        return productService.findAll();
    }

    @GetMapping("/view-product/{id}")
    public ResponseEntity<?> viewProduct(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);

        if (product.isPresent())
            return ResponseEntity.ok(product.orElseThrow());
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping("/save-product")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        Product productNew = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productNew);
    }

    @PutMapping("/update-product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
        Product updatedProduct = productService.update(id, product);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok("Product with ID " + id + " was successfully deleted.");
    }
}
