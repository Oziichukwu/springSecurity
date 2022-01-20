package com.example.springsecurity.controller;


import com.example.springsecurity.dtos.request.JwtRequest;
import com.example.springsecurity.dtos.request.UserRequest;
import com.example.springsecurity.dtos.response.JwtResponse;
import com.example.springsecurity.services.CustomUserDetailService;
import com.example.springsecurity.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class JwtController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/register")
    public ResponseEntity<UserRequest> register(@RequestBody UserRequest userRequest){

         UserRequest userRequest1 = customUserDetailService.register(userRequest);

         ResponseEntity<UserRequest> response = new ResponseEntity<>(userRequest1, HttpStatus.CREATED);

         return response;
    }

    @GetMapping("/currentUser")
    public UserRequest getCurrentUser(Principal principal){
       UserDetails userDetails = this.customUserDetailService.loadUserByUsername(principal.getName());
       return (UserRequest) userDetails;
    }


    @PostMapping("/login")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest){

        UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword());

        authenticationManager.authenticate(upat);

        UserDetails userDetails = customUserDetailService.loadUserByUsername(jwtRequest.getUsername());

        String jwtToken = jwtUtil.generateToken(userDetails);

        JwtResponse jwtResponse = new JwtResponse(jwtToken);

        return new ResponseEntity<JwtResponse>(jwtResponse, HttpStatus.OK);
    }
}
