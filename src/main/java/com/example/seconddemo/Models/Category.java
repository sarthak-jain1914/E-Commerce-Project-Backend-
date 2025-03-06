package com.example.seconddemo.Models;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter

@Entity
public class Category extends BaseModel {

    private String title;
    @OneToMany(mappedBy = "category" , cascade = {CascadeType.REMOVE} , fetch = FetchType.LAZY)
    private List<Product> products;


}
