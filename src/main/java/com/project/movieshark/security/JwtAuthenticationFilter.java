package com.project.movieshark.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.project.movieshark.Util.JwtUtil;
import com.project.movieshark.service.CustomUserDetailService;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            try {
                username = jwtUtil.extractUsername(token);
            } catch (Exception ex) {
                // invalid token: let request continue (it will be rejected by security later)
                logger.debug("Invalid JWT: " + ex.getMessage());
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // load user from DB (so we can fallback to DB roles if needed)
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Validate token and set Authentication
            if (jwtUtil.validateToken(token, userDetails.getUsername())) {

                Claims claims = jwtUtil.extractAllClaims(token);

                // Try to read roles as list from token (preferred)
                List<String> rolesFromToken = claims.get("roles", List.class);

                List<SimpleGrantedAuthority> authorities;

                if (rolesFromToken != null && !rolesFromToken.isEmpty()) {
                    authorities = rolesFromToken.stream()
                            .map(r -> r.startsWith("ROLE_") ? r : "ROLE_" + r)
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());
                } else {
                    // fallback to authorities from UserDetails (DB)
                    authorities = userDetails.getAuthorities()
                            .stream()
                            .map(a -> new SimpleGrantedAuthority(a.getAuthority()))
                            .collect(Collectors.toList());
                }

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, authorities);

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
