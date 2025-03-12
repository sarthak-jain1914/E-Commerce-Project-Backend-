package com.example.seconddemo.Controller;

import com.example.seconddemo.DTO.CreateProductRequestDTO;
import com.example.seconddemo.Models.Category;
import com.example.seconddemo.Models.Product;

import com.example.seconddemo.Repository.Projection.CategoryIdAndTitle;
import com.example.seconddemo.Service.ProductService;
import com.example.seconddemo.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.web.bind.annotation.*;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    @Cacheable(value = "product", key = "#id")
    public Product getProductById(@PathVariable("id") Integer id) throws ProductNotFoundException {
        if(id >= 200) {
            throw new IllegalArgumentException("ID should not be 200");
        }
        // request to FakeStoreProductService to get product by id
        Product product = Productservice.getProductById(id);

        if(product == null) {
            throw new ProductNotFoundException("Product not found");
        }
        return product;
    }



    @GetMapping("/products")
    @Cacheable(value = "products")
    public List<Product> getAllProducts() throws ProductNotFoundException {
        return Productservice.getAllProducts();
    }

    @GetMapping("/categories")
    public List<CategoryIdAndTitle> getAllCategories() {
        return Productservice.getAllCategories();
    }

    @PostMapping("/product")
    public Product createProduct(@RequestBody CreateProductRequestDTO userResponse) throws ProductNotFoundException {

        return Productservice.createProduct(userResponse.getTitle(), userResponse.getDescription(),
                userResponse.getImageURL(), userResponse.getCategory().getTitle());

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
}
