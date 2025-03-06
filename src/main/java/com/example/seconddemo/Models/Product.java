package com.example.seconddemo.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

@Entity
public class Product extends BaseModel {

    private String title;
    private String description;
    private String  imageURL;

    @ManyToOne
    @JsonIgnore
    private Category category;
    private String CreatedByUserName;
}
