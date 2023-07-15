package com.Mvc.MvC.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.net.http.HttpRequest;

public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    JwtGenerator jwtGenerator;

    @Autowired
    Customuserdetailsservice customuserdetailsservice;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = getJwtFromRequest(request);

        if (StringUtils.hasText(token) && jwtGenerator.validateToken(token)){
            String username  = jwtGenerator.getusernamefromJWT(token);

            UserDetails userDetails  = customuserdetailsservice.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken  = new UsernamePasswordAuthenticationToken(userDetails,
                    null,userDetails.getAuthorities());

            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(request,response);

    }

    public String getJwtFromRequest(HttpServletRequest request){
        String bearertoken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearertoken) && bearertoken.startsWith("Bearer ")){
            return bearertoken.substring(7, bearertoken.length());
        } else {
            return  null;
        }
    }





}
