package com.emis.EMIS.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.databind.json.JsonMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.*;


@Slf4j

public class CustomAuthorization  extends OncePerRequestFilter {

    public boolean pathMatches(ArrayList<String> allowedServletPaths, String servletPath){
        for (String allowedServletPath : allowedServletPaths) {
            if (servletPath.startsWith(allowedServletPath)) {
                return true;
            }
        }
        return false;
    }


    private void returnFailString(HttpServletResponse response, String message){
        response.setContentType("application/json");
        JsonMapper jsonMapper = new JsonMapper();
        Map<String, String> map = new HashMap<>();
        map.put("response", message);
        try {
            jsonMapper.writeValue(response.getOutputStream(), map);
        }catch (Exception e){
            log.warn(e.getMessage());
        }


}

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        ArrayList<String> allowedServletPaths = new ArrayList<>();
        allowedServletPaths.add("/v1/user/all/");
        if(this.pathMatches(allowedServletPaths, request.getServletPath())){
            log.info(request.getServletPath()+ ": has been let through");
            filterChain.doFilter(request, response);
        }else{
            String token = request.getHeader("Authorization");
            if(token == null){
                log.warn("Token is null");
                this.returnFailString(response,"Token is null" );
            }else if(!token.startsWith("Bearer")){
                log.warn("token not valid");
                this.returnFailString(response, "token not valid");
            }else{
                Algorithm algo = Algorithm.HMAC256("Stella".getBytes());
                JWTVerifier verifier = com.auth0.jwt.JWT.require(algo).build();
                token = token.substring("Bearer ".length());

                try {
                    DecodedJWT jwt = verifier.verify(token);
                    List<String> list = jwt.getClaim("roles").asList(String.class);
                    log.info(list.get(0).toString());
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    authorities.add(new SimpleGrantedAuthority(list.get(0).toString()));
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(jwt.getSubject(), null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request,response);
                }catch (Exception e){
                    log.warn(e.getMessage());
                    this.returnFailString(response, e.getMessage());
                }
            }
        }
    }

}
