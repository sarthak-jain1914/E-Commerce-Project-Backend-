package com.example.seconddemo.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateProductRequestDTO {

    private String title;
    private String description;
    private String imageURL;
    private CategoryRequestDTO category;

}
