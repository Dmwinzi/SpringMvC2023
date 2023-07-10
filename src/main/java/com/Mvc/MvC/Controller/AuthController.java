package com.Mvc.MvC.Controller;

import com.Mvc.MvC.Model.UserEntity;
import com.Mvc.MvC.Repository.Rolerepository;
import com.Mvc.MvC.Service.UserserviceImpl;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private UserserviceImpl userservice;

    private Rolerepository rolerepository;


    public AuthController(UserserviceImpl userservice, Rolerepository rolerepository) {
        this.userservice = userservice;
        this.rolerepository = rolerepository;
    }


    @PostMapping("/Register")
    public ResponseEntity<String> register(@RequestBody UserEntity userEntity){
        return userservice.adduser(userEntity);
    }




}
