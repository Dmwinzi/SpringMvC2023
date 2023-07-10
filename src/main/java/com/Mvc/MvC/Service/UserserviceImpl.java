package com.Mvc.MvC.Service;

import com.Mvc.MvC.Model.Roles;
import com.Mvc.MvC.Model.UserEntity;
import com.Mvc.MvC.Repository.Rolerepository;
import com.Mvc.MvC.Repository.Userrepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserserviceImpl implements UserService{


    private Userrepository userrepository;
    private Rolerepository rolerepository;

    private PasswordEncoder passwordEncoder;


    public UserserviceImpl(Userrepository userrepository, Rolerepository rolerepository, PasswordEncoder passwordEncoder) {
        this.userrepository = userrepository;
        this.rolerepository = rolerepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<String> adduser(UserEntity userEntity) {
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
}
