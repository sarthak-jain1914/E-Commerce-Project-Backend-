package com.example.seconddemo.Service;

import com.example.seconddemo.DTO.CreateProductRequestDTO;
import com.example.seconddemo.Models.Category;
import com.example.seconddemo.Models.Product;
import com.example.seconddemo.Repository.Projection.CategoryIdAndTitle;
import com.example.seconddemo.exception.ProductNotFoundException;

import java.util.List;


public interface ProductService {
    Product getProductById(Integer id);

    List<Product> getAllProducts();

    List<CategoryIdAndTitle> getAllCategories();

    Product createProduct(String title, String description, String imageURL,
                          String catTitle) throws ProductNotFoundException;

    Category getCategoryById(int id) throws ProductNotFoundException;

    Category getCategoryByTitle(String title) throws ProductNotFoundException;

}
