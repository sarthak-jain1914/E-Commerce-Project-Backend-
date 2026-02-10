package com.example.seconddemo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductServiceDTO {

    private Integer id;

    private String title;

    private String description;
    private String imageURL;

    private String category;
}
