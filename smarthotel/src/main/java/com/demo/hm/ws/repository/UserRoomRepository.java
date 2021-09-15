package com.demo.hm.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.hm.ws.entity.UserRoom;

@Repository
public interface UserRoomRepository extends JpaRepository <UserRoom, Long>{

	UserRoom findByUserId(Long userId);
}
