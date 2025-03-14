package com.example.seconddemo.Service;

import com.example.seconddemo.DTO.CreateProductRequestDTO;
import com.example.seconddemo.Models.Category;
import com.example.seconddemo.Models.Product;
import com.example.seconddemo.Repository.Projection.CategoryIdAndTitle;
import com.example.seconddemo.exception.ProductNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;


public interface ProductService {
    Product getProductById(Integer id) throws ProductNotFoundException;

    List<Product> getAllProducts() throws ProductNotFoundException;

    List<CategoryIdAndTitle> getAllCategories();

    Product createProduct(String title, String description, String imageURL,
                          String catTitle) throws ProductNotFoundException;

    Category getCategoryById(int id) throws ProductNotFoundException;

    Category getCategoryByTitle(String title) throws ProductNotFoundException;

    Page<Product> getPaginatedProducts(int pageNo, int pageSize);
}
