package com.example.seconddemo.controller;

import com.example.seconddemo.dto.CreateProductRequestDTO;
import com.example.seconddemo.models.Category;
import com.example.seconddemo.models.Product;
import com.example.seconddemo.service.ProductService;
import com.example.seconddemo.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ProductController {

    private final ProductService Productservice;

    public ProductController(@Qualifier("SelfProductService") ProductService ProductService) {
        this.Productservice = ProductService;

    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable("id") Integer id)
            throws ProductNotFoundException {
        return Productservice.getProductById(id);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() throws ProductNotFoundException {
        return Productservice.getAllProducts();
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return Productservice.getAllCategories();
    }

    @PostMapping("/product")
    public Product createProduct(@RequestBody CreateProductRequestDTO userResponse) throws ProductNotFoundException {
        return Productservice.createProduct(userResponse);

    }

    @GetMapping("/category/{id}")
    public Category getCategoryById(@PathVariable("id") int id) throws ProductNotFoundException {
        return  Productservice.getCategoryById(id);
    }

    @GetMapping("/categoryT/{title}")
    public Category getCategoryByTitle(@PathVariable("title") String title) throws ProductNotFoundException {
        return Productservice.getCategoryByTitle(title);
    }

    @GetMapping("/products/{pageNo}/{pageSize}")
    public Page<Product> getPaginatedProducts(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize) {
        return Productservice.getPaginatedProducts(pageNo, pageSize);
    }

    @GetMapping("/update-product")
    public Product updateProduct(@RequestBody Product product) {
        return Productservice.updateProduct(product);
    }

    @GetMapping("delete-product/{id}")
    public void deleteProduct(@PathVariable("id") Integer id) throws ProductNotFoundException {
        Productservice.deleteProduct(id);
    }
}
