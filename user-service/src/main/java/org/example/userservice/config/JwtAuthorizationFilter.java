package org.example.userservice.config;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;

    public JwtAuthorizationFilter(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("////////////////////");
        String authorizationToken = request.getHeader("Authorization");
        if (authorizationToken != null && authorizationToken.startsWith("Bearer ")) {
            try {
                String jwt = authorizationToken.substring(7);
                Claims claims = Jwts.parser()
                        .setSigningKey(JwtAuth.SECRET_KEY)
                        .build()
                        .parseClaimsJws(jwt)
                        .getBody();
                String username = claims.getSubject();
//                String role = claims.get("role", String.class);

                // Utilisez le UserDetailsService pour charger les détails de l'utilisateur
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//                SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        userDetails.getAuthorities()

                );
                System.out.println("//////////////////////:");
                System.out.println("/////////////"+userDetails.getAuthorities().toString());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                return; // Arrêter le traitement en cas d'erreur
            }
        }
        filterChain.doFilter(request, response);
    }
}
