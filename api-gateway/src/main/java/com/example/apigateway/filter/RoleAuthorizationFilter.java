//package com.example.apigateway.filter;
//
//public class RoleAuthorizationFilter {
//
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
public class RoleAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public RoleAuthorizationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        String method = request.getMethod();

        // Authentication endpoints are public
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

            String role =
                    claims.get("role", String.class);

            /*
             * Book modification operations
             * require ADMIN role.
             */
            if (path.startsWith("/books")) {

                if (method.equals("POST") ||
                    method.equals("PUT") ||
                    method.equals("DELETE")) {

                    if (!"ADMIN".equals(role)) {

                        response.setStatus(
                                HttpServletResponse.SC_FORBIDDEN);

                        return;
                    }
                }
            }

            filterChain.doFilter(request, response);

        } catch (Exception e) {

            response.setStatus(
                    HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
