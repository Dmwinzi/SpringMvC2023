package com.Mvc.MvC.Security;


import com.Mvc.MvC.Model.Roles;
import com.Mvc.MvC.Model.UserEntity;
import com.Mvc.MvC.Repository.Userrepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Customuserdetailsservice  implements UserDetailsService {


    private Userrepository userrepository;

    public Customuserdetailsservice(Userrepository userrepository) {
        this.userrepository = userrepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user  = userrepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new User(user.getUsername(),user.getPassword(),maprolestoAuthorities(user.getRoles()));
    }

    public Collection<GrantedAuthority> maprolestoAuthorities (List<Roles> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }


}
