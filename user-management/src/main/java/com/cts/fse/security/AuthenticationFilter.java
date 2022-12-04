package com.cts.fse.security;

import com.cts.fse.model.LoginRequest;
import com.cts.fse.service.UserService;
import com.cts.fse.util.Utility;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final UserService userService;


    private final Environment environment;

    @Autowired
    public AuthenticationFilter(UserService userService, Environment environment) {
        this.userService = userService;
        this.environment = environment;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            LoginRequest req = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword(), Utility.getStudentRoles(req.getRoles())));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String userName = ((User) authResult.getPrincipal()).getUsername();

        UserDetails userDto = userService.loadUserByUsername(userName);
        Collection<? extends GrantedAuthority> roles=userDto.getAuthorities();

        Map<String,Object> claims=new HashMap<>();
        claims.put("role",roles);
        claims.put("email",userDto.getUsername());

        // generate token
        String token = Jwts.builder().setClaims(claims).setSubject(userDto.getUsername())
                .setExpiration(
                        new Date(System.currentTimeMillis() + Long.parseLong(environment.getProperty("token.expiration_time"))))
                .signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret")).compact();

        response.addHeader("token", token);
        response.addHeader("userId", userDto.getUsername());
    }
}
