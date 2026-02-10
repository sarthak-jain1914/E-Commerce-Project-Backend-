package com.example.seconddemo.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter

@MappedSuperclass
public class BaseModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  // AUTO increment part
    private int id;

    @CreatedDate
    @Temporal(value = TemporalType.DATE)
    private Date createdAt;

    @LastModifiedDate
    @Temporal(value = TemporalType.DATE)
    public Date updatedAt;
    private boolean isDeleted;
}
