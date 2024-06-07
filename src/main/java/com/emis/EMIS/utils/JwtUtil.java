package com.emis.EMIS.utils;
import com.emis.EMIS.models.RolesEntity;
import com.emis.EMIS.repositories.RolesRepo;
import com.emis.EMIS.services.DataService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

import io.jsonwebtoken.Claims;

@Service
public class JwtUtil {
    @Autowired
    private RolesRepo rolesRepo;
    @Autowired
    private DataService dataService;


    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


    public String generateToken(String userName){

        var user = dataService.findByEmail(userName).get();
        var profileEntity = dataService.findById(user.getProfileId()).get();
        List<RolesEntity> rolesEntities = rolesRepo.findByProfileId(profileEntity.getProfileId());
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for(RolesEntity role: rolesEntities){
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        Map<String,Object> claims=new HashMap<>();
        claims.put("privileges", authorities);

        //Encode the  email on the JWT
        claims.put("email", user.getEmail());
        return createToken(claims,userName);
    }

    private String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }



}

