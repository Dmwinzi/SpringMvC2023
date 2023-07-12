package com.Mvc.MvC.Service;

import com.Mvc.MvC.Model.LoginEntity;
import com.Mvc.MvC.Model.UserEntity;
import org.springframework.http.ResponseEntity;


public interface AuthService {

     ResponseEntity <String> adduser (UserEntity userEntity);

     ResponseEntity<String> login (LoginEntity loginEntity);

}
