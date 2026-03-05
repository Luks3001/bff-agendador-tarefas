package com.lucas.bffagendadortarefas.infrastructure.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Collections;







@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    // Construtor enxugado: recebe apenas o JwtUtil (sem banco de dados!)
    public JwtRequestFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            final String authorizationHeader = request.getHeader("Authorization");

            String username = null;
            String token = null;

            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                token = authorizationHeader.substring(7);
                username = jwtUtil.extrairEmailToken(token);
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                // Valida o token matematicamente usando a chave secreta no JwtUtil
                if (jwtUtil.validateToken(token, username)) {

                    // Cria a autenticação confiando no token (sem UserDetails)
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            username, null, Collections.emptyList());

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }

            chain.doFilter(request, response);

        } catch (ExpiredJwtException | MalformedJwtException e) {
            // Se as exceções customizadas (UnauthorizedException) não existirem no BFF,
            // podemos lançar uma RuntimeException genérica para o Spring lidar e retornar 403/401
            throw new RuntimeException("Erro: token inválido ou expirado", e);
        }
    }
}