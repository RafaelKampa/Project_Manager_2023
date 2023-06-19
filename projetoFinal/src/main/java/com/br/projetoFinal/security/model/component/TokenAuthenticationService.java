package com.br.projetoFinal.security.model.component;

import com.br.projetoFinal.entity.Usuario;
import com.br.projetoFinal.security.model.Autenticacao;
import io.jsonwebtoken.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class TokenAuthenticationService {

    // EXPIRATION_TIME = 100 anos
    static final long EXPIRATION_TIME = (100 * 365 * 24 * 60 * 60 * 1000);
    static final String SECRET = "AulaPuc";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

    public static Autenticacao getToken(HttpServletResponse response, Usuario username,
                                        Collection<? extends GrantedAuthority> authorities) {

        JwtBuilder jwtBuilder = Jwts.builder()
                .setSubject(username.getUsername())
                .claim("fullName", username.getNome())
                .claim("userId", username.getIdUsuario())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET);

        // Adicionar as authorities ao token
        if (authorities != null && !authorities.isEmpty()) {
            List<String> authorityList = new ArrayList<>();
            for (GrantedAuthority authority : authorities) {
                authorityList.add(authority.getAuthority());
            }
            jwtBuilder.claim("authorities", authorityList);
        }

        String token = jwtBuilder.compact();

        Autenticacao autenticacao = new Autenticacao();
        autenticacao.setToken(token);
        autenticacao.setLogin(username.getUsername());

        return autenticacao;
    }

    @SuppressWarnings("unchecked")
    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);

        if (token != null) {
            // faz parse do token
            Claims claims = null;

            try {
                claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();
            } catch (ExpiredJwtException e) {
                claims = e.getClaims();
            }

            if (claims != null) {
                String username = claims.getSubject();
                List<String> authorityList = (List<String>) claims.get("authorities");
                Collection<? extends GrantedAuthority> authorities = parseAuthorities(authorityList);
                return new UsernamePasswordAuthenticationToken(username, null, authorities);
            }
        }
        return null;
    }

    private static Collection<? extends GrantedAuthority> parseAuthorities(List<String> authorityList) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String authority : authorityList) {
            authorities.add(new SimpleGrantedAuthority(authority));
        }
        return authorities;
    }

    public static Map<String, String> getValuesFromToken(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);

        if (token != null) {
            // faz parse do token
            Claims claims = null;

            try {
                claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();
            } catch (ExpiredJwtException e) {
                claims = e.getClaims();
            }

            if (claims != null) {
                Map<String, String> values = new HashMap<String, String>();
                Integer guidUsuario = (Integer) claims.get("GUIDUSUARIO");
                values.put("guidUsuario", guidUsuario == null ? null : guidUsuario.toString());
                return values;
            }
        }
        return null;
    }
}
