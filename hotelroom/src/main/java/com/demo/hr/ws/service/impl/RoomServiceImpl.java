package com.demo.hr.ws.service.impl;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.hr.ws.dto.RoomBookDto;
import com.demo.hr.ws.dto.RoomDto;
import com.demo.hr.ws.entity.Room;
import com.demo.hr.ws.exception.ConstantMessage;
import com.demo.hr.ws.exception.ErrorMessages;
import com.demo.hr.ws.repository.RoomRepository;
import com.demo.hr.ws.service.RoomService;

import io.github.resilience4j.retry.annotation.Retry;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private Environment env;

	@Autowired
	RoomRepository roomRepository;

	@Override
	public Room createRoom(RoomDto roomDto) {

		Room room = new Room();
		if (roomDto.getRoomId() != null) {
			Room existingRoom = roomRepository.findByRoomId(roomDto.getRoomId());
			if (existingRoom == null)
				throw new EntityNotFoundException(ErrorMessages.NO_ROOM_RECORD_FOUND.getErrorMessage());

			room.setRoomId(existingRoom.getRoomId());
		}
		room.setCapacity(roomDto.getCapacity());
		room.setLocation(roomDto.getLocation());
        room.setRoomType(roomDto.getRoomType());
        
		room.setRoomStatus(ConstantMessage.AVAILABLE);
		roomRepository.save(room);

		return room;
	}

	@Override
	// @CircuitBreaker(name="default", fallbackMethod="bookroomresponse")
	@Retry(name = "config", fallbackMethod = "bookroomresponse")
	public String bookRoom(RoomBookDto roomBookDao) {

		if (roomBookDao.getUserId() != null) {

			String forObject1 = new RestTemplate().getForObject(env.getProperty("USERID_URL") + "checkuser/{id}",
					String.class, roomBookDao.getUserId());

			if (forObject1.equals("FALIURE")) {
				throw new EntityNotFoundException(ErrorMessages.NO_USER_RECORD_FOUND.getErrorMessage());
			}
		}
		Room existingRoom = roomRepository.findByRoomId(roomBookDao.getRoomId());
		if (existingRoom == null)
			throw new EntityNotFoundException(ErrorMessages.NO_ROOM_RECORD_FOUND.getErrorMessage());
		if (existingRoom.getRoomStatus().contains(ConstantMessage.NOT_AVAILABLE))
			throw new EntityNotFoundException(ErrorMessages.ROOM_ALREADY_OCCUIPIED.getErrorMessage());
		if (null != existingRoom) {
			existingRoom.setUserId(roomBookDao.getUserId());
			existingRoom.setRoomStatus(ConstantMessage.NOT_AVAILABLE);

			roomRepository.save(existingRoom);
		}

		// update the roomid in userroom database
		String forObject2 = new RestTemplate().postForObject(env.getProperty("ROOMIDUPD_URL") + "updateRoomId",
				roomBookDao, String.class);

		return forObject2;
	}

	public String bookroomresponse(Exception ex) {

		if (ex instanceof EntityNotFoundException)
			return ex.getMessage();
		else
			return ConstantMessage.FALLBACKMETHOD;

	}

}
