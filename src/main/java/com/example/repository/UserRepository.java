package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	User findbyEmail(String email);
}
