package com.eduardo.spoilerappnetwork.security.jwt.filter;

import com.eduardo.spoilerappnetwork.security.jwt.service.JwtService;
import com.eduardo.spoilerappnetwork.security.userdetails.UserDetailsServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private UserDetailsService userDetailsService;

    public JwtRequestFilter(JwtService jwtService, UserDetailsServiceImpl userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        String token = "";
        String username = "";

        if(header != null && header.startsWith("Bearer ")){
            token = header.substring(7);
            username = jwtService.getUsernameFromToken(token);
        }

        if(isNotInContext(username)){
            addToContext(username, token, request);
        }

        filterChain.doFilter(request, response);
    }

    private void addToContext(String username, String token, HttpServletRequest request) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
        if(jwtService.isTokenValid(token, userDetails)){
            UsernamePasswordAuthenticationToken usernamePassToken = new UsernamePasswordAuthenticationToken(userDetails, null, null);
            usernamePassToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePassToken);
        }
    }

    private boolean isNotInContext(String username) {
        return !username.trim().isEmpty() && SecurityContextHolder.getContext().getAuthentication() == null;
    }
}
