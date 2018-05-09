package com.emusicstore.dao;

import com.emusicstore.model.Product;

import java.util.List;

/**
 * @author Rohith Raj 2/13/2018
 */
public interface ProductDao {

    void addProduct(Product product);

    Product getProductById(String id);

    List<Product> getAllProducts();

    void deleteProduct(String id);

    void editProduct(Product product);
}
