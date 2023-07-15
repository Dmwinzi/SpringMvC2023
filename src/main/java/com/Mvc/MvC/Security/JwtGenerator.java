package com.Mvc.MvC.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.hibernate.annotations.Comment;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtGenerator {


    public String generatetoken(Authentication authentication){

        String username = authentication.getName();
        Date currentdate  = new Date();
        Date  expirydate = new Date(currentdate.getTime() + SecurityConstants.Jwt_expiry);

        String token  = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expirydate)
                .signWith(SignatureAlgorithm.HS512,SecurityConstants.Jwt_secret)
                .compact();
        return token;
    }

    public String getusernamefromJWT(String token){

        Claims claims  = Jwts.parser().setSigningKey(SecurityConstants.Jwt_secret)
                .parseClaimsJwt(token)
                .getBody();
       return claims.getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(SecurityConstants.Jwt_secret).parseClaimsJwt(token);
            return true;
        }catch (Exception e){
              throw new AuthenticationCredentialsNotFoundException("Incorrect or expired Jwts");
        }
    }

}
