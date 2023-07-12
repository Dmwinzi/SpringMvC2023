package com.Mvc.MvC.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Securityconfig {

    private Customuserdetailsservice customuserdetailsservice;

    public Securityconfig(Customuserdetailsservice customuserdetailsservice) {
        this.customuserdetailsservice = customuserdetailsservice;
    }

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception{

       return httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers(HttpMethod.GET).authenticated()
                                .requestMatchers("/api/auth/**").permitAll()
                                .anyRequest().authenticated()
                        )
               .build();


    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
         return authenticationConfiguration.getAuthenticationManager();
    }

  @Bean
  PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
  }

}
