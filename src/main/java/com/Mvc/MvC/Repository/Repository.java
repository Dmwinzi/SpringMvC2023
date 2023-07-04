package com.Mvc.MvC.Repository;


import com.Mvc.MvC.Model.Content;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<Content , Integer> {



}
