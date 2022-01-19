package com.example.springsecurity.filter;

import com.example.springsecurity.services.CustomUserDetailService;
import com.example.springsecurity.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String bearerToken = request.getHeader("Authorization");

        String username = null;

        String token = null;

        if (bearerToken != null && bearerToken.startsWith("Bearer ")){

            token = bearerToken.substring(7);

            try {
                 username = jwtUtil.extractUsername(token);
                 UserDetails userDetails =  customUserDetailService.loadUserByUsername(username);
                 if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                     UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                 }else {
                     System.out.println("Invalid Token");
                 }

            }catch (Exception ex){
                ex.printStackTrace();
            }
        }else {
            System.out.println("Invalid bearer Token");
        }
    }
}
