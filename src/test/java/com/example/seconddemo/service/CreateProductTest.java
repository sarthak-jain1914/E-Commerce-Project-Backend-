package com.example.seconddemo.service;

import com.example.seconddemo.dto.CategoryRequestDTO;
import com.example.seconddemo.dto.CreateProductRequestDTO;
import com.example.seconddemo.models.Category;
import com.example.seconddemo.models.Product;
import com.example.seconddemo.repository.CategoryRepo;
import com.example.seconddemo.repository.ProductRepo;
import com.example.seconddemo.exception.ProductNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
//import org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static reactor.core.publisher.Mono.when;

@ExtendWith(MockitoExtension.class)
public class CreateProductTest {


    @Mock
    CategoryRepo categoryRepo;

    @Mock
    ProductRepo productRepo;

    @InjectMocks
    SelfProductService service;

    @Test
    void createProduct_categoryExists() throws ProductNotFoundException {
        CreateProductRequestDTO requestDTO = new CreateProductRequestDTO();
        requestDTO.setTitle("title");
        requestDTO.setDescription("description");
        requestDTO.setImageURL("imageURL");

        CategoryRequestDTO categoryRequestDTO = new CategoryRequestDTO();
        categoryRequestDTO.setId(1);
        categoryRequestDTO.setTitle("newCategory");
        requestDTO.setCategory(categoryRequestDTO);



        Category category = new Category();
        category.setTitle("newCategory");
        Mockito.when(categoryRepo.findByTitle("newCategory")).thenReturn(Optional.of(category));
        Product product = new Product();
        product.setCategory(category);
        Mockito.when(productRepo.save(any(Product.class))).thenReturn(product);

        Product result = service.createProduct(requestDTO);

        assertNotNull(result);
        assertEquals("newCategory", result.getCategory().getTitle());
        verify(categoryRepo, never()).save(any(Category.class));
        verify(productRepo).save(any(Product.class));
    }

    @Test
    void createProduct_categoryNotExist() throws  ProductNotFoundException {

        CreateProductRequestDTO requestDTO = new CreateProductRequestDTO();
        requestDTO.setTitle("title");
        requestDTO.setDescription("description");
        requestDTO.setImageURL("imageURL");

        CategoryRequestDTO categoryRequestDTO = new CategoryRequestDTO();
        categoryRequestDTO.setId(1);
        categoryRequestDTO.setTitle("newCategory");
        requestDTO.setCategory(categoryRequestDTO);




        Mockito.when(categoryRepo.findByTitle("newCategory")).thenReturn(Optional.empty());
        Category category = new Category();
        category.setTitle("newCategory");
        Product product = new Product();
        product.setCategory(category);
        Mockito.when(categoryRepo.save(any(Category.class))).thenReturn(category);

        Mockito.when(productRepo.save(any(Product.class))).thenReturn(product);

        Product result = service.createProduct(requestDTO);

        assertNotNull(result);
        assertEquals("newCategory", result.getCategory().getTitle());
        verify(categoryRepo).save(any(Category.class));
        verify(productRepo).save(any(Product.class));

    }

    @Test
    void getProductById_IfProductFound() throws ProductNotFoundException {
        Integer id = 1;
        Product product = new Product();
        product.setId(id);
        product.setTitle("productTitle");
        Mockito.when(productRepo.findById(id)).thenReturn(Optional.of(product));

        Product result = service.getProductById(id);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(id, result.getId());
        Assertions.assertEquals("productTitle", result.getTitle());
        verify(productRepo).findById(id);
    }


    @Test
    void getAllProducts() throws ProductNotFoundException {

        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();

        product1.setId(1);
        product1.setTitle("product1");
        product2.setId(2);
        product2.setTitle("product2");
        product3.setId(3);
        product3.setTitle("product3");
        ArrayList<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        Mockito.when(productRepo.findAll()).thenReturn(products);

        List<Product> result = service.getAllProducts();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(3, result.size());
        verify(productRepo).findAll();
    }

}












































//
//@Test
//void createProduct_categoryDoesNotExist() throws ProductNotFoundException {
//    Mockito.when(categoryRepo.findByTitle("cat")).thenReturn(Optional.empty());
//    Category category = new Category();
//    Mockito.when(categoryRepo.save(any(Category.class))).thenReturn(category);
//    Product product = new Product();
//    Mockito.when(productRepo.save(any(Product.class))).thenReturn(product);
//
//    Product result = service.createProduct("title", "desc", "url", "cat");
//
//    assertNotNull(result);
//    verify(categoryRepo).save(any(Category.class));
//    verify(productRepo).save(any(Product.class));
//}