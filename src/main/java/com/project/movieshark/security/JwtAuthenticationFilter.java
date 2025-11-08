package com.project.movieshark.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.project.movieshark.Util.JwtUtil;
import com.project.movieshark.service.CustomUserDetailService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private CustomUserDetailService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");
		String token = null;
		String username = null;

		// Step 1: Extract JWT token from the Authorization header
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
		    token = authHeader.substring(7); // remove "Bearer " prefix
		    username = jwtUtil.extractUsername(token); // extract username from token
		}

		// Step 2: Validate the token and set authentication
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
		    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

		    if (jwtUtil.validateToken(token, userDetails.getUsername())) {
		        UsernamePasswordAuthenticationToken authToken =
		                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

		        // Step 3: Set authentication in the SecurityContext
		        SecurityContextHolder.getContext().setAuthentication(authToken);
		    }
		}
		// Step 4: Continue the filter chain
		filterChain.doFilter(request, response);
		
	}

}
