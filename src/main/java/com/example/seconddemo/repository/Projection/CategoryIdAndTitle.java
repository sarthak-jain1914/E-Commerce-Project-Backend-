package com.example.seconddemo.repository.Projection;


import java.util.Date;

public interface CategoryIdAndTitle {
    Integer getId();
    String getTitle();
    Date getCreatedAt();
    Date getUpdatedAt();
}
