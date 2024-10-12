package com.nisum.java.users_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nisum.java.users_api.User;

public interface UsersRepository extends JpaRepository<User, Integer>{
	Optional<User> findByUsername(String username);
}
