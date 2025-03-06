package com.example.seconddemo.Repository;

import com.example.seconddemo.Models.Category;
import com.example.seconddemo.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

    Optional<Product> findById(int id);

    Optional<Product> findByTitle(String title);

    List<Product> findAll();

    void deleteById(int id);

    void deleteByTitle(String title);

    void deleteAllByCategory(Category category);

}
