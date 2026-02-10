package com.example.seconddemo.ControllerTest;

import com.example.seconddemo.controller.ProductController;
import com.example.seconddemo.dto.CategoryRequestDTO;
import com.example.seconddemo.dto.CreateProductRequestDTO;
import com.example.seconddemo.models.Category;
import com.example.seconddemo.models.Product;
import com.example.seconddemo.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ProductController.class)
public class productControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @MockitoBean(name = "SelfProductService")
    public ProductService productService;


    @Test
    void createProduct_Success() throws Exception {
        //input creation
        CreateProductRequestDTO requestDTO = new CreateProductRequestDTO();
        requestDTO.setTitle("title");
        requestDTO.setDescription("description");
        requestDTO.setImageURL("imageURL");

        CategoryRequestDTO categoryRequestDTO = new CategoryRequestDTO();
        categoryRequestDTO.setId(1);
        categoryRequestDTO.setTitle("newCategory");
        requestDTO.setCategory(categoryRequestDTO);

        //output creation
        Product savedProduct = new Product();
        savedProduct.setId(1);
        savedProduct.setTitle("title");
        savedProduct.setDescription("description");
        savedProduct.setImageURL("imageURL");

        Category category = new Category();
        category.setId(1);
        category.setTitle("newCategory");
        savedProduct.setCategory(category);

        //mocking part
        Mockito.when(productService.createProduct(Mockito.any(CreateProductRequestDTO.class)))
                .thenReturn(savedProduct);

        //actual call and test part through api call
        mockMvc.perform(
                post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO))
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.title").value("title"))
        .andExpect(jsonPath("$.description").value("description"))
        .andExpect(jsonPath("$.imageURL").value("imageURL"))
        .andExpect(jsonPath("$.id").value(1));
    }

}
