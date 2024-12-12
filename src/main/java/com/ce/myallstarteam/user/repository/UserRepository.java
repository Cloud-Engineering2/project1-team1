package com.ce.myallstarteam.user.repository;

<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ce.myallstarteam.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
=======
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ce.myallstarteam.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    User findByUsername(String username);

>>>>>>> c13504eddba48c97ce6d3134d499ce33107bd9e8
}
