package com.Mvc.MvC.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class Securityconfig {

    private Customuserdetailsservice customuserdetailsservice;

    private JwtAuthEntryPoint jwtAuthEntryPoint;

    public Securityconfig(Customuserdetailsservice customuserdetailsservice, JwtAuthEntryPoint jwtAuthEntryPoint) {
        this.customuserdetailsservice = customuserdetailsservice;
        this.jwtAuthEntryPoint = jwtAuthEntryPoint;
    }

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception{

      httpSecurity
                .csrf(csrf -> csrf.disable())
               .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthEntryPoint))
               .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers(HttpMethod.GET).authenticated()
                                .requestMatchers("/api/auth/**").permitAll()
                                .anyRequest().authenticated()
                        )
               .httpBasic(Customizer.withDefaults());
          httpSecurity.addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class);
      return httpSecurity.build();

    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
         return authenticationConfiguration.getAuthenticationManager();
    }

  @Bean
  PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
  }

  @Bean
  JwtAuthFilter jwtAuthFilter(){
        return new JwtAuthFilter();
  }


}
