//package com.example.authservice.service;
//
//public class AuthService {
//
//}


//package com.example.authservice.service;
//
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.example.authservice.model.User;
//import com.example.authservice.repository.UserRepository;
//
//@Service
//public class AuthService {
//
//    private final UserRepository repository;
//    private final PasswordEncoder passwordEncoder;
//
//    public AuthService(UserRepository repository,
//                       PasswordEncoder passwordEncoder) {
//        this.repository = repository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    public User register(User user) {
//
//        user.setPassword(
//                passwordEncoder.encode(user.getPassword())
//        );
//
//        return repository.save(user);
//    }
//
//    public User login(String username, String password) {
//
//        User user = repository.findByUsername(username)
//                .orElse(null);
//
//        if (user == null) {
//            return null;
//        }
//
//        if (!passwordEncoder.matches(
//                password,
//                user.getPassword())) {
//
//            return null;
//        }
//
//        return user;
//    }
//}




package com.example.authservice.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.authservice.dto.LoginResponse;
import com.example.authservice.model.User;
import com.example.authservice.repository.UserRepository;
import com.example.authservice.util.JwtUtil;

@Service
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository repository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {

        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public User register(User user) {

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        return repository.save(user);
    }

    public LoginResponse login(String username, String password) {

        User user = repository.findByUsername(username)
                .orElse(null);

        if (user == null) {
            return null;
        }

        if (!passwordEncoder.matches(
                password,
                user.getPassword())) {

            return null;
        }

        String token =
                jwtUtil.generateToken(
                        user.getUsername(),
                        user.getRole()
                );

        return new LoginResponse(token);
    }
}
