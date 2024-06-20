package com.ByteCard.api.Config.security;

import com.ByteCard.api.Config.token.TokenService;
import com.ByteCard.api.Infra.Persistence.User.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenJWT = recuperaToken(request);
        System.out.println(tokenJWT);
        if(tokenJWT != null){
            String subject = tokenService.getSubject(tokenJWT);
            UserDetails user = repository.findByLogins(subject);
            var authentication = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request,response);
    }

    private String recuperaToken(HttpServletRequest request) {
        String token =  request.getHeader("Authorization");
        if(token != null){
            return token.replace("Bearer ","");
        }
        return null;

    }
}
