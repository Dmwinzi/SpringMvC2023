package com.Mvc.MvC.Service;

import com.Mvc.MvC.Model.LoginEntity;
import com.Mvc.MvC.Model.Roles;
import com.Mvc.MvC.Model.UserEntity;
import com.Mvc.MvC.Repository.Rolerepository;
import com.Mvc.MvC.Repository.Userrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthserviceImpl implements AuthService {


    private Userrepository userrepository;
    private Rolerepository rolerepository;

    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;


    @Autowired
    public AuthserviceImpl(Userrepository userrepository, Rolerepository rolerepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userrepository = userrepository;
        this.rolerepository = rolerepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public ResponseEntity<String> adduser (UserEntity userEntity) {
        try{
            if (userrepository.existsByUsername(userEntity.getUsername())){
                return ResponseEntity.ok("User already taken");
            }

            UserEntity  user = new UserEntity();
            user.setUsername(userEntity.getUsername());
            user.setPassword(passwordEncoder.encode(userEntity.getPassword()));
            Roles  roles  = rolerepository.findByName("USER").get();
            user.setRoles(Collections.singletonList(roles));
            userrepository.save(user);
            return ResponseEntity.ok("Registered SUccesfully");
        }catch (Exception e){
           return ResponseEntity.ok(e.getMessage().toString());
        }
    }

    @Override
    public ResponseEntity<String> login (LoginEntity loginEntity) {
       Authentication authentication  =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginEntity.getUsername(),loginEntity.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok("Signed in Succesfully");
    }

}
