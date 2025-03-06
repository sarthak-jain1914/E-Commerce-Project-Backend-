package com.example.seconddemo.Repository;

import com.example.seconddemo.Models.Category;
import com.example.seconddemo.Repository.Projection.CategoryIdAndTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

    //JPA query method
    Optional<Category> findByTitle(String title);

    Optional<Category> findById(int id);

    List<Category> findAll();

    // Hibernate query
    @Query("select c from Category c")
    List<CategoryIdAndTitle> findAllCategories();
}


