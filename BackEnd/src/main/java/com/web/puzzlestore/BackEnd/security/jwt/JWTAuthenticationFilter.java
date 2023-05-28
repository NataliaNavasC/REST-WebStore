package com.web.puzzlestore.BackEnd.security.jwt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.puzzlestore.BackEnd.security.Constants.Constants;

import org.springframework.security.core.userdetails.User;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            com.web.puzzlestore.BackEnd.model.entities.Person credenciales = new ObjectMapper().readValue(request.getInputStream(), com.web.puzzlestore.BackEnd.model.entities.User.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            credenciales.getUsername(), credenciales.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication auth) throws IOException, ServletException 
    {
        final String authorities = auth.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(","));

        final Map<String, Object> claims = new HashMap<>();
        claims.put("Authorities", authorities);

        String token = Jwts.builder()
            .setIssuedAt(new Date())
            .setIssuer(Constants.ISSUER_INFO)
            .setClaims(claims)
			.setSubject(((User)auth.getPrincipal()).getUsername())
			.setExpiration(new Date(System.currentTimeMillis() + Constants.TOKEN_EXPIRATION_TIME))
			.signWith(SignatureAlgorithm.HS512, Constants.SUPER_SECRET_KEY)
            .compact();
		response.addHeader(Constants.HEADER_AUTHORIZATION_KEY, Constants.TOKEN_BEARER_PREFIX + token);

    }
}
