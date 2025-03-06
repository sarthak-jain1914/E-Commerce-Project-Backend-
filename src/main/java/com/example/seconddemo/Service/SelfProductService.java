package com.example.seconddemo.Service;


import com.example.seconddemo.Models.Category;
import com.example.seconddemo.Models.Product;
import com.example.seconddemo.Repository.CategoryRepo;
import com.example.seconddemo.Repository.ProductRepo;
import com.example.seconddemo.Repository.Projection.CategoryIdAndTitle;
import com.example.seconddemo.exception.ProductNotFoundException;
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
    public Product getProductById(Integer id) {
        Optional<Product> response = productRepo.findById(id);
        if(response.isEmpty()){
            throw new IllegalArgumentException("product not found ");
        }
        System.out.println("Fetched product: " + response);
        return response.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public List<CategoryIdAndTitle> getAllCategories() {
        return categoryRepo.findAllCategories();
    }

    @Override
    public Product createProduct(String title, String description, String imageURL,
                                 String catTitle) throws ProductNotFoundException {

        Product product = new Product();
        Category category = new Category();

        product.setTitle(title);
        product.setDescription(description);
        product.setImageURL(imageURL);
        product.setCreatedAt(new Date());
        product.setUpdatedAt(new Date());


        Optional<Category> ExistingCategory = categoryRepo.findByTitle(catTitle);

        if(ExistingCategory.isPresent()){
            product.setCategory(ExistingCategory.get());
        }
        else{
            category.setCreatedAt(new Date());
            category.setUpdatedAt(new Date());
            category.setTitle(catTitle);
            categoryRepo.save(category);
            product.setCategory(category);
        }

        return productRepo.save(product);

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
           throw new ProductNotFoundException("category not found");
       }
        return response.get();
    }

    @Override
    public Category getCategoryByTitle(String title)  {
        return categoryRepo.findByTitle(title).orElse(null);

    }
}
