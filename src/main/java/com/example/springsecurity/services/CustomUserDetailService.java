package com.example.springsecurity.services;

import com.example.springsecurity.data.models.User;
import com.example.springsecurity.data.repositories.UserRepository;
import com.example.springsecurity.dtos.request.UserRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Lazy
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserRequest register (UserRequest userRequest){

        User user = new User();
        BeanUtils.copyProperties(userRequest, user);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        BeanUtils.copyProperties(user, userRequest);
        return  userRequest;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if( user == null){
            throw new UsernameNotFoundException("User does not exist");
        }

        UserRequest userRequest = new UserRequest();
        BeanUtils.copyProperties(user, userRequest);

        return userRequest;
    }
}
