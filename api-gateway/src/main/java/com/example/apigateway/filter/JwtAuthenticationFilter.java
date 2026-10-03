//package com.example.apigateway.filter;
//
//import java.io.IOException;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.example.apigateway.util.JwtUtil;
//
//import io.jsonwebtoken.Claims;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@Component
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    private final JwtUtil jwtUtil;
//
//    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
//        this.jwtUtil = jwtUtil;
//    }
//
//    @Override
//    protected void doFilterInternal(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            FilterChain filterChain)
//            throws ServletException, IOException {
//
//        String path = request.getRequestURI();
//
//        // Auth APIs are public
//        if (path.startsWith("/auth/")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        String authHeader =
//                request.getHeader("Authorization");
//
//        // No token
//        if (authHeader == null ||
//            !authHeader.startsWith("Bearer ")) {
//
//            response.setStatus(
//                    HttpServletResponse.SC_UNAUTHORIZED);
//
//            return;
//        }
//
//        String token = authHeader.substring(7);
//
//        try {
//
//            Claims claims = jwtUtil.validateToken(token);
//
//            String username = claims.getSubject();
//
//            System.out.println(
//                    "Authenticated user: " + username);
//
//            filterChain.doFilter(request, response);
//
//        } catch (Exception e) {
//
//            response.setStatus(
//                    HttpServletResponse.SC_UNAUTHORIZED);
//        }
//    }
//}



package com.example.apigateway.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.apigateway.util.JwtUtil;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        if (path.startsWith("/auth/")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader =
                request.getHeader("Authorization");

        if (authHeader == null ||
            !authHeader.startsWith("Bearer ")) {

            response.setStatus(
                    HttpServletResponse.SC_UNAUTHORIZED);

            return;
        }

        String token = authHeader.substring(7);

        try {

            Claims claims = jwtUtil.validateToken(token);

            String username = claims.getSubject();
            String role = claims.get("role", String.class);

            System.out.println(
                    "Authenticated user: " + username);

            System.out.println(
                    "User role: " + role);

            filterChain.doFilter(request, response);

        } catch (Exception e) {

            response.setStatus(
                    HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}