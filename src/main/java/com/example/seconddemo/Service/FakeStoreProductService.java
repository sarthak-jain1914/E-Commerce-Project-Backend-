package com.example.seconddemo.Service;


import com.example.seconddemo.DTO.CategoryRequestDTO;
import com.example.seconddemo.DTO.CreateProductRequestDTO;
import com.example.seconddemo.DTO.FakeStoreProductServiceDTO;
import com.example.seconddemo.Models.Category;
import com.example.seconddemo.Models.Product;
import com.example.seconddemo.Repository.CategoryRepo;
import com.example.seconddemo.Repository.Projection.CategoryIdAndTitle;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService {


    private final RestTemplate restTemplate;
    private final CategoryRepo categoryRepo;

    public FakeStoreProductService(RestTemplate restTemplate, CategoryRepo categoryRepo) {
        this.restTemplate = restTemplate;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Product getProductById(Integer id) {
        Product product =   new Product();
        //call fakestore api to get product by id
        ResponseEntity<FakeStoreProductServiceDTO> fakeStoreResponse =
                restTemplate.getForEntity("https://fakestoreapi.com/products/" + id,
                        FakeStoreProductServiceDTO.class);
        //get response from fakestore api
        FakeStoreProductServiceDTO response = fakeStoreResponse.getBody();
        //validate response
        if(response == null) {
            throw new RuntimeException("Product not found");
        }
        //convert response to product object
        product = convertFakstoreResponseToProduct(response);
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
    List<Product> products = new ArrayList<>();

        //call fakestore api to get all products
        ResponseEntity<FakeStoreProductServiceDTO[]> fakeStoreResponse =
                restTemplate.getForEntity("https://fakestoreapi.com/products",
                        FakeStoreProductServiceDTO[].class);
        //get response from fakestore api
        FakeStoreProductServiceDTO[] response = fakeStoreResponse.getBody();
        //validate response
        if(response == null) {
            throw new RuntimeException("Product not found");
        }
        //convert response to product object (ArrayList)
        for (FakeStoreProductServiceDTO product : response) {
            products.add(convertFakstoreResponseToProduct(product));
        }
        return products;
    }

    /**
     * @return
     */
    @Override
    public List<CategoryIdAndTitle> getAllCategories() {
        return List.of();
    }

    /**
     * @return
     */

    public static Product convertFakstoreResponseToProduct(FakeStoreProductServiceDTO response) {

        Product product = new Product();
        Category category = new Category();
        category.setTitle(response.getCategory());

        product.setCategory(category);
        product.setId(response.getId());
        product.setTitle(response.getTitle());
        product.setDescription(response.getDescription());
        product.setImageURL(response.getImageURL());

        return product;
    }

    @Override
    public Product createProduct(String title, String description, String imageURL,
                                 String catTitle) {
        Product product;

        FakeStoreProductServiceDTO FakeStoreProduct = new FakeStoreProductServiceDTO();

        FakeStoreProduct.setTitle(title);
        FakeStoreProduct.setDescription(description);
        FakeStoreProduct.setImageURL(imageURL);
        FakeStoreProduct.setCategory(catTitle);
        //call fakestore api to create product by id and get response from fakestore api to
        // validate response and convert response to product object
        ResponseEntity <FakeStoreProductServiceDTO> productResponse =
                restTemplate.postForEntity("https://fakestoreapi.com/products",
                        FakeStoreProduct, FakeStoreProductServiceDTO.class);

        System.out.println("Status code: " + productResponse.getStatusCode());
        //validate response from fakestore api and convert response to product object
        product = convertFakstoreResponseToProduct(Objects.requireNonNull(productResponse.getBody()));
        return product;
    }

    public Category getCategoryById(int id) {
        Category category = new Category();

        //call fakestore api to get category by title
        ResponseEntity<CategoryRequestDTO> fakeStoreResponse =
                restTemplate.getForEntity("https://fakestoreapi.com/products/category/" + id,
                        CategoryRequestDTO.class);
        //get response from fakestore api
        CategoryRequestDTO response = fakeStoreResponse.getBody();
        //validate response
        if(response == null) {
            throw new RuntimeException("Category not found");
        }
        //convert response to category object

        category.setTitle(response.getTitle());
        category.setId(response.getId());
        return category;
    }

    public Category getCategoryByTitle(String title) {
        Category category = new Category();

        //call fakestore api to get category by title
        ResponseEntity<CategoryRequestDTO> fakeStoreResponse =
                restTemplate.getForEntity("https://fakestoreapi.com/products/category/" + title,
                        CategoryRequestDTO.class);
        //get response from fakestore api
        CategoryRequestDTO response = fakeStoreResponse.getBody();
        //validate response
        if(response == null) {
            throw new RuntimeException("Category not found");
        }
        //convert response to category object

        category.setTitle(response.getTitle());
        category.setId(response.getId());
        return category;
    }

    /**
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Page<Product> getPaginatedProducts(int pageNo, int pageSize) {
        return null;
    }
}
