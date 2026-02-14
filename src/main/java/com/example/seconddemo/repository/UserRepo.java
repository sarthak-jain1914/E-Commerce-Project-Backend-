package com.example.seconddemo.repository;

import com.example.seconddemo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.email = email")
    Optional<User> findByEmail(String email);

    User findById(int id);
}
