package com.Mvc.MvC.Service;

import com.Mvc.MvC.Model.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


public interface UserService {

     ResponseEntity <String> adduser(UserEntity userEntity);



}
