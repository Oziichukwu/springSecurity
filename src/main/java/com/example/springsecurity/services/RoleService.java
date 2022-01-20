package com.example.springsecurity.services;

import com.example.springsecurity.data.models.Role;
import com.example.springsecurity.dtos.request.RoleRequest;

import java.util.List;

public interface RoleService {

     RoleRequest createRole(RoleRequest roleRequest);

     List<RoleRequest> getAllRoles();

     RoleRequest getRoleById(Long roleId);

    void deleteRoleById(Long id);
}
