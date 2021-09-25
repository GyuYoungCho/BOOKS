package com.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findUserById(String id);

    boolean existsById(String id);
    boolean existsByNickname(String user_nickname);
    Optional<User> findUserByNickname(String user_nickname);
    //Optional<User> findUserByToken(String token);
}
