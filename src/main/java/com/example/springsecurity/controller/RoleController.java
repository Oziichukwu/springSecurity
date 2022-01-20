package com.example.springsecurity.controller;


import com.example.springsecurity.data.models.Role;
import com.example.springsecurity.dtos.request.RoleRequest;
import com.example.springsecurity.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/create_Roles")
    public RoleRequest createRole(@RequestBody RoleRequest roleRequest){
        return roleService.createRole(roleRequest);
    }

    @GetMapping("/roles")
    public List<RoleRequest> getAllRoles(){
        return roleService.getAllRoles();
    }

    @DeleteMapping("/roles/{roleId}")
    public void deleteRole(@PathVariable Long roleId){
        roleService.deleteRoleById(roleId);
    }
}
