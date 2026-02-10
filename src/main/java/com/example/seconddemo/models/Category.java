package com.example.seconddemo.models;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

@Entity
public class Category extends BaseModel implements Serializable {

    private String title;
    @OneToMany(mappedBy = "category" , cascade = {CascadeType.REMOVE} , fetch = FetchType.LAZY)
    private List<Product> products;


}
