package com.Mvc.MvC.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "Users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userid;

    private String username;

    private  String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "User_Roles", joinColumns = @JoinColumn(name = "userid", referencedColumnName = "userid") ,
            inverseJoinColumns = @JoinColumn(name = "roleid", referencedColumnName = "roleid"))
    private List<Roles>  roles  = new ArrayList<>();

}
