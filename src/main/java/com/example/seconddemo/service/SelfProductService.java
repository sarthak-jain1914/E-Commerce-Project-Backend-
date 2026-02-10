package com.example.seconddemo.service;


import com.example.seconddemo.dto.CreateProductRequestDTO;
import com.example.seconddemo.models.Category;
import com.example.seconddemo.models.Product;
import com.example.seconddemo.repository.CategoryRepo;
import com.example.seconddemo.repository.ProductRepo;
import com.example.seconddemo.repository.Projection.CategoryIdAndTitle;
import com.example.seconddemo.exception.CategoryNotFoundException;
import com.example.seconddemo.exception.ProductNotFoundException;
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

    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;


    public SelfProductService(ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Product getProductById(Integer id) throws ProductNotFoundException {
        Optional<Product> response = productRepo.findById(id);
        if(response.isEmpty()){
            throw new ProductNotFoundException("product not found ");
        }
        System.out.println("Fetched product: " + response);
        return response.get();
    }

    @Override
    public List<Product> getAllProducts() throws ProductNotFoundException {
            return productRepo.findAll();

    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public Product createProduct(CreateProductRequestDTO userResponse) throws ProductNotFoundException {

        validateArguments(userResponse.getTitle(), userResponse.getDescription(), userResponse.getImageURL(),
                userResponse.getCategory().getTitle());


        Product product = new Product();
        Category category = new Category();

        product.setTitle(userResponse.getTitle());
        product.setDescription(userResponse.getDescription());
        product.setImageURL(userResponse.getImageURL());
        product.setCreatedAt(new Date());
        product.setUpdatedAt(new Date());


        Optional<Category> ExistingCategory = categoryRepo.findByTitle(userResponse.getCategory().getTitle());

        if(ExistingCategory.isPresent()){
            product.setCategory(ExistingCategory.get());
        }
        else{
            category.setCreatedAt(new Date());
            category.setUpdatedAt(new Date());
            category.setTitle(userResponse.getCategory().getTitle());
            categoryRepo.save(category);
            product.setCategory(category);
        }

        return productRepo.save(product);

    }

    public void validateArguments(String title, String description, String imageURL,
                                    String catTitle){
//        if(title == null || title.isEmpty()){
//            throw new IllegalArgumentException("Title cannot be empty");
//        }
//        if(description == null || description.isEmpty()){
//            throw new IllegalArgumentException("Description cannot be empty");
//        }
//        if(imageURL == null || imageURL.isEmpty()){
//            throw new IllegalArgumentException("Image URL cannot be empty");
//        }
//        if(catTitle == null || catTitle.isEmpty()){
//            throw new IllegalArgumentException("Category Title cannot be empty");
//        }
    }


    public  Product updateProduct(Product product) {
        Product existingProduct = productRepo.findById(product.getId()).get();
        if(existingProduct.getId() == 0) {
            return null;
        }
        existingProduct.setTitle(product.getTitle());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setImageURL(product.getImageURL());
        existingProduct.setUpdatedAt(new Date());

        return productRepo.save(existingProduct);
    }


    @Override
    public Category getCategoryById(int id) throws ProductNotFoundException {
       Optional<Category> response = categoryRepo.findById(id);
       if(response.isEmpty())
       {
           throw new CategoryNotFoundException("category not found");
       }
        return response.get();
    }

    @Override
    public Category getCategoryByTitle(String title)  {
        Optional<Category> response = categoryRepo.findByTitle(title);
        if(response.isEmpty())
        {
            throw new CategoryNotFoundException("category not found");
        }
        return response.get();
    }

    @Override
    public Page<Product> getPaginatedProducts(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        String sortBy = "title";
        Pageable pagingWithSort = PageRequest.of(pageNo, pageSize, Sort.Direction.ASC ,sortBy);
        Page<Product> pagedResult = productRepo.findAll(pagingWithSort);

//        Page<Product> pagingResult = productRepo.findAll(paging);

        return pagedResult;
    }
}
