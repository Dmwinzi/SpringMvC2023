package com.Mvc.MvC.Repository;

import com.Mvc.MvC.Model.UserEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Userrepository extends JpaRepository<UserEntity,Integer> {

    Optional<UserEntity> findByUsername(String username);

    Boolean existsByUsername (String usernmae);

}
