package com.example.seconddemo.service;


import com.example.seconddemo.configuration.JwtAuthFilter;
import com.example.seconddemo.configuration.JwtUtil;
import com.example.seconddemo.dto.CreateProductRequestDTO;
import com.example.seconddemo.exception.InvalidArgumentException;
import com.example.seconddemo.models.Category;
import com.example.seconddemo.models.Product;
import com.example.seconddemo.repository.CategoryRepo;
import com.example.seconddemo.repository.ProductRepo;
import com.example.seconddemo.exception.CategoryNotFoundException;
import com.example.seconddemo.exception.ProductNotFoundException;
import io.jsonwebtoken.Jwts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("SelfProductService")
public class SelfProductService implements ProductService {

    private final JwtUtil jwtUtil;
    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;


    public SelfProductService(JwtUtil jwtUtil, ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.jwtUtil = jwtUtil;
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }
//--------------------------------------------------------------------------------------------
    @Override
    public Product getProductById(Integer id) throws ProductNotFoundException {

        Optional<Product> response = productRepo.findById(id);
        if(response.isEmpty()){
            throw new ProductNotFoundException("product not found ");
        }
        return response.get();
    }
//--------------------------------------------------------------------------------------------
    @Override
    public List<Product> getAllProducts() throws ProductNotFoundException {
        return productRepo.findAll();

    }
//--------------------------------------------------------------------------------------------
    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }
//--------------------------------------------------------------------------------------------
    @Override
    public Product createProduct(CreateProductRequestDTO userResponse) throws ProductNotFoundException {

        validateArguments(userResponse.getTitle(), userResponse.getDescription(), userResponse.getImageURL(),
                userResponse.getCategory().getTitle());


        Product product = new Product();
        Category category = new Category();

        product.setTitle(userResponse.getTitle());
        product.setDescription(userResponse.getDescription());
        product.setImageURL(userResponse.getImageURL());
        product.setCreatedByUserName(userResponse.getCreatedByUserName());
        Optional<Category> ExistingCategory = categoryRepo.findByTitle(userResponse.getCategory().getTitle());

        if(ExistingCategory.isPresent()){
            product.setCategory(ExistingCategory.get());
        }
        else{
            category.setTitle(userResponse.getCategory().getTitle());
            categoryRepo.save(category);
            product.setCategory(category);
        }

        return productRepo.save(product);

    }
//--------------------------------------------------------------------------------------------
    public void validateArguments(String title, String description, String imageURL,
                                    String catTitle){
        if(title == null || title.isEmpty()){
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if(description == null || description.isEmpty()){
            throw new IllegalArgumentException("Description cannot be empty");
        }
        if(imageURL == null || imageURL.isEmpty()){
            throw new IllegalArgumentException("Image URL cannot be empty");
        }
        if(catTitle == null || catTitle.isEmpty()){
            throw new IllegalArgumentException("Category Title cannot be empty");
        }
    }

//--------------------------------------------------------------------------------------------
    public  Product updateProduct(Product product) {
        Optional<Product> existingProduct = productRepo.findById(product.getId());
        if(existingProduct.isEmpty()){
            throw new ProductNotFoundException("product not found");
        }
        existingProduct.get().setTitle(product.getTitle());
        existingProduct.get().setDescription(product.getDescription());
        existingProduct.get().setImageURL(product.getImageURL());

        return productRepo.save(existingProduct.get());
    }

//--------------------------------------------------------------------------------------------
    @Override
    public void deleteProduct(Integer id) {
        Optional<Product> existingProduct = productRepo.findById(id);
        if(existingProduct.isEmpty()){
            throw new ProductNotFoundException("product not found");
        }
        productRepo.deleteById(id);
    }
//--------------------------------------------------------------------------------------------
    @Override
    public Category getCategoryById(int id) throws ProductNotFoundException {
       Optional<Category> response = categoryRepo.findById(id);
       if(response.isEmpty())
       {
           throw new CategoryNotFoundException("category not found");
       }
        return response.get();
    }
//--------------------------------------------------------------------------------------------
    @Override
    public Category getCategoryByTitle(String title)  {
        Optional<Category> response = categoryRepo.findByTitle(title);
        if(response.isEmpty())
        {
            throw new CategoryNotFoundException("category not found");
        }
        return response.get();
    }
//--------------------------------------------------------------------------------------------
    @Override
    public Page<Product> getPaginatedProducts(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        String sortBy = "title";
        Pageable pagingWithSort = PageRequest.of(pageNo, pageSize, Sort.Direction.ASC ,sortBy);
        Page<Product> pagedResult = productRepo.findAll(pagingWithSort);

//        Page<Product> pagingResult = productRepo.findAll(paging);

        return pagedResult;
    }
//--------------------------------------------------------------------------------------------

}