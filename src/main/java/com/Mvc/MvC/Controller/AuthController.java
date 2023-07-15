package com.Mvc.MvC.Controller;

import com.Mvc.MvC.DTO.AuthresponseDTO;
import com.Mvc.MvC.Model.LoginEntity;
import com.Mvc.MvC.Model.UserEntity;
import com.Mvc.MvC.Repository.Rolerepository;
import com.Mvc.MvC.Service.AuthserviceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private AuthserviceImpl authservice;

    public AuthController(AuthserviceImpl authservice) {
        this.authservice = authservice;
    }


    @PostMapping("/Register")
    public ResponseEntity<String> register(@RequestBody UserEntity userEntity){
        return authservice.adduser(userEntity);
    }


    @PostMapping("/login")
    public ResponseEntity<AuthresponseDTO> log(@RequestBody LoginEntity loginEntity){
         return authservice.login(loginEntity);
    }


}
