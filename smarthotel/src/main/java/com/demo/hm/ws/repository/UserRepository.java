package com.demo.hm.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.hm.ws.entity.User;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

	User findByUserId(Long userId);
	User findByEmail(String email);
}
