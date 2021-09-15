package com.demo.hr.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.hr.ws.entity.Room;

public interface RoomRepository extends JpaRepository <Room, Long>{

	Room findByRoomId(Long roomId);
	Room findByUserId(Long userId);
}
