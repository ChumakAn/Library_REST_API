package com.example.library.controller;

import com.example.library.domain.User;
import com.example.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityExistsException;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) throws Exception {

        Authentication authObject = null;
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authObject);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Wrong credentials, please try again", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>("User was loged in successfully", HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody User user){
        try{
            userService.createUser(user);
            return new ResponseEntity<>("User was created", HttpStatus.OK);
        }
        catch (EntityExistsException e){
            return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);
        }
    }

}
