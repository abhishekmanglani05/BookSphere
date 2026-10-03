//package com.example.authservice.controller;
//
//public class AuthController {
//
//}



package com.example.authservice.controller;

import org.springframework.web.bind.annotation.*;

import com.example.authservice.model.User;
import com.example.authservice.service.AuthService;
import com.example.authservice.dto.LoginResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return service.register(user);
    }

//    @PostMapping("/login")
//    public User login(
//            @RequestParam String username,
//            @RequestParam String password) {
//
//        return service.login(username, password);
//    }
    
    @PostMapping("/login")
    public LoginResponse login(
            @RequestParam String username,
            @RequestParam String password) {

        return service.login(username, password);
    }
}
