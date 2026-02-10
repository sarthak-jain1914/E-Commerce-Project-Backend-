package com.example.seconddemo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter

@Entity
public class Product extends BaseModel implements Serializable {

    private String title;
    private String description;
    private String  imageURL;

    @ManyToOne
    @JsonIgnore
    private Category category;
    private String CreatedByUserName;
}
