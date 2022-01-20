package com.example.springsecurity.data.repositories;

import com.example.springsecurity.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

     User findByUsername(String username);
}
