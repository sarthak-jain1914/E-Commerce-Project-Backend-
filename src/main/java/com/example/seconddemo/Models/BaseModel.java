package com.example.seconddemo.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter

@MappedSuperclass
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  // AUTO increment part
    private int id;

    private Date createdAt;
    public Date updatedAt;
    private boolean isDeleted;
}
