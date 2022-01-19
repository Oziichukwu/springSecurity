package com.example.springsecurity.data.repositories;

import com.example.springsecurity.data.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
