package com.example.springsecurity.services;

import com.example.springsecurity.data.models.Role;
import com.example.springsecurity.data.repositories.RoleRepository;
import com.example.springsecurity.dtos.request.RoleRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleRepository roleRepository;

    @Override
    public RoleRequest createRole(RoleRequest roleRequest) {

        Role role = new Role();

        BeanUtils.copyProperties(roleRequest, role);

        Role role1 = roleRepository.save(role);

        BeanUtils.copyProperties(role1, roleRequest);

        return roleRequest;
    }

    @Override
    public List<RoleRequest> getAllRoles() {

        List<Role> roles = roleRepository.findAll();
        List<RoleRequest> roleRequests = new ArrayList<>();
        RoleRequest roleRequest = null;
        for (Role re : roles) {
            roleRequest = new RoleRequest();
            BeanUtils.copyProperties(re, roleRequest);
            roleRequests.add(roleRequest);
        }

        return roleRequests;
    }

    @Override
    public RoleRequest getRoleById(Long roleId) {
        Role role = roleRepository.findById(roleId).get();
        RoleRequest roleRequest = new RoleRequest();
        BeanUtils.copyProperties(role, roleRequest);

        return roleRequest;
    }

    @Override
    public void deleteRoleById(Long id) {
        roleRepository.deleteById(id);
    }
}
