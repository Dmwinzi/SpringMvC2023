package com.Mvc.MvC.Repository;

import com.Mvc.MvC.Model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Rolerepository  extends JpaRepository<Roles, Integer> {
    Optional<Roles> findByName(String name);


}
