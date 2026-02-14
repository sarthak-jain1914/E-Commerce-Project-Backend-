package com.example.seconddemo.service;

import com.example.seconddemo.dto.CreateProductRequestDTO;
import com.example.seconddemo.models.Category;
import com.example.seconddemo.models.Product;
import com.example.seconddemo.exception.ProductNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;


public interface ProductService {
    Product getProductById(Integer id) throws ProductNotFoundException;

    List<Product> getAllProducts() throws ProductNotFoundException;

    List<Category> getAllCategories();

    Product createProduct(CreateProductRequestDTO userResponse ) throws ProductNotFoundException;

    Category getCategoryById(int id) throws ProductNotFoundException;

    Category getCategoryByTitle(String title) throws ProductNotFoundException;

    Page<Product> getPaginatedProducts(int pageNo, int pageSize);

    public  Product updateProduct(Product product);

    void deleteProduct(Integer id);
}
