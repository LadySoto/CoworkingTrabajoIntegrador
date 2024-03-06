package com.backend.digitalhouse.coworking.service;

import com.backend.digitalhouse.coworking.entity.Usuario;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    @Value("${security.jwt.expiration-minutes}")
    private long EXPIRATION_MINUTES;
       public String generateToken(Usuario usuario, Map<String, Object> extraClaims) {
           Date issuedAt = new Date(System.currentTimeMillis());
           Date expiration = new Date(issuedAt.getTime() + (EXPIRATION_MINUTES * 60 * 1000));

           Jwts.builder()
                   .setClaims(extraClaims)
                   .setSubject(usuario.getNombre())
                   .setIssuedAt(issuedAt)
                   .setExpiration(expiration)
                   .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                   .signWith(generateKey(),SignatureAlgoritm.HS256);
    }
}
