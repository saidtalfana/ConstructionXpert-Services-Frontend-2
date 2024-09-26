package org.example.gatewayservice.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtUtil {

    private static final String SECRET_KEY_BASE64 = "357638792F423F4428472B4B6250655368566D597133743677397A2443264629";

    private static final Key SECRET_KEY = Keys.hmacShaKeyFor(java.util.Base64.getDecoder().decode(SECRET_KEY_BASE64));

    public void validateToken(final String token) {
        Jwts.parser().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
    }


}