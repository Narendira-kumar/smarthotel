package com.demo.hm.ws.service.impl;

import static com.demo.hm.ws.exception.ConstantMessages.IN;
import static com.demo.hm.ws.exception.ConstantMessages.LOGIN_SUCCESS;
import static com.demo.hm.ws.exception.ConstantMessages.OTP_INCORRECT;
import static com.demo.hm.ws.exception.ConstantMessages.OTP_SENT;
import static com.demo.hm.ws.exception.ConstantMessages.OUT;
import static com.demo.hm.ws.exception.ConstantMessages.ROOM_OCCUIPIED;
import static com.demo.hm.ws.exception.ConstantMessages.ROOM_STATUS_UPDATED;
import static com.demo.hm.ws.exception.ConstantMessages.USERROOM_UPDATED;
import static com.demo.hm.ws.exception.ConstantMessages.INCORRECT_ENTRY;
import static com.demo.hm.ws.exception.ConstantMessages.OTP_PROCCESSED;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.hm.ws.component.EmailService;
import com.demo.hm.ws.dto.RoomBookDto;
import com.demo.hm.ws.dto.RoomEntryDto;
import com.demo.hm.ws.dto.UserLogInDto;
import com.demo.hm.ws.dto.UserOTPDto;
import com.demo.hm.ws.entity.User;
import com.demo.hm.ws.entity.UserRoom;
import com.demo.hm.ws.repository.UserRepository;
import com.demo.hm.ws.repository.UserRoomRepository;
import com.demo.hm.ws.service.UserRoomService;

@Service
public class UserRoomServiceImpl implements UserRoomService {

	@Autowired
	UserRoomRepository userRoomRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Autowired
	@Qualifier("emailsender")
	private EmailService emailService;

	@Override
	public String loginUser(UserLogInDto userLogInDao) {

		if (userLogInDao.getUserId() != null) {
			User existingUser = userRepository.findByUserId(userLogInDao.getUserId());

			if (existingUser == null)
				throw new EntityNotFoundException(
						com.demo.hm.ws.exception.ErrorMessages.NO_USER_RECORD_FOUND.getErrorMessage());
			
			UserRoom userRoom = new UserRoom();
			userRoom.setLogin(userLogInDao.getLogin());
			userRoom.setUserId(userLogInDao.getUserId());
			UserRoom existingRoomUser = userRoomRepository.findByUserId(userLogInDao.getUserId());
			if(existingRoomUser != null)
			{
				userRoom.setRoomId(existingRoomUser.getRoomId());
				userRoom.setUserOTP(existingRoomUser.getUserOTP());
				userRoom.setUserRoomStatus(existingRoomUser.getUserRoomStatus());
			}
			userRoom.setLogin(userLogInDao.getLogin());
			userRoom.setPassword(bCryptPasswordEncoder.encode(userLogInDao.getPassword()));
			userRoomRepository.save(userRoom);
		}
	
		return LOGIN_SUCCESS;
	}

	/*
	 * @Override // @CircuitBreaker(name="default",
	 * fallbackMethod="hardcodedresponse") public UserRoom bookRoom(UserRoom
	 * userRoom) {
	 * 
	 * if (userRoom.getUserId() != null) { UserRoom existingUserRoom =
	 * userRoomRepository.findByUserId(userRoom.getUserId());
	 * 
	 * System.out.println(existingUserRoom.getUserOTP()); if
	 * ((userRoom.getUserOTP().toString()).equals(existingUserRoom.getUserOTP().
	 * toString())) {
	 * 
	 * JSONObject json = new JSONObject(); json.put("userid",
	 * userRoom.getUserId().toString()); json.put("roomid",
	 * userRoom.getRoomId().toString());
	 * 
	 * rabbitTemplate.convertAndSend("room-book", "roombook",
	 * json.toString().getBytes());
	 * 
	 * 
	 * if (userRoom.getRoomId() !=null) { String forObject = new RestTemplate()
	 * .postForObject("Http://localhost:8081/SmartHotelService/updRoom", userRoom,
	 * String.class); }
	 * 
	 * 
	 * 
	 * userRoom.setUserRoomStatus("IN"); userRoomRepository.save(userRoom);
	 * 
	 * } } return null; }
	 */

	@Override
	public String sendOTP(UserOTPDto userOTPDao) {

		User existingUser = userRepository.findByUserId(userOTPDao.getUserId());
		if (existingUser == null)
			throw new EntityNotFoundException(
					com.demo.hm.ws.exception.ErrorMessages.NO_USER_RECORD_FOUND.getErrorMessage());
		String OTP = RandomStringUtils.randomNumeric(4);

		String emailid = existingUser.getEmail();
		String name = existingUser.getFirstName();
		String surname = existingUser.getLastName();

		emailService.sendmail(name, surname, emailid, OTP);

		if (userOTPDao.getUserId() != null) {
			UserRoom existingUserRoom = userRoomRepository.findByUserId(userOTPDao.getUserId());

			if (null != existingUserRoom) {
				existingUserRoom.setUserOTP(OTP);
				existingUserRoom.setUserOTPAccessed(" ");
				userRoomRepository.save(existingUserRoom);
			}
		}

		return OTP_SENT;
	}

	@Override
	public String roomEntry(RoomEntryDto roomEntryDao) {

		if (roomEntryDao.getUserId() != null) {
			UserRoom existingUserRoom = userRoomRepository.findByUserId(roomEntryDao.getUserId());
			if (existingUserRoom == null)
			throw new EntityNotFoundException(
					com.demo.hm.ws.exception.ErrorMessages.NO_USER_RECORD_FOUND.getErrorMessage());
			
			if ((existingUserRoom.getUserRoomStatus() != null) && (existingUserRoom.getUserRoomStatus().equals("IN") )) {
				return INCORRECT_ENTRY; 
			}
			 
			
			if ((roomEntryDao.getUserOTP().toString()).equals(existingUserRoom.getUserOTP().toString())){
				
				if (existingUserRoom.getUserOTPAccessed().equals("Y"))
						return OTP_PROCCESSED;
				existingUserRoom.setUserRoomStatus(IN);
				existingUserRoom.setUserOTPAccessed("Y");
				userRoomRepository.save(existingUserRoom);
			}
			else
				return OTP_INCORRECT;
		}
		return ROOM_OCCUIPIED;

	}

	@Override
	public String updateRoomId(RoomBookDto roomBookDao) {

		if (roomBookDao.getUserId() != null) {
			UserRoom existingUserRoom = userRoomRepository.findByUserId(roomBookDao.getUserId());
			if (existingUserRoom == null)
				throw new EntityNotFoundException(
						com.demo.hm.ws.exception.ErrorMessages.NO_USER_RECORD_FOUND.getErrorMessage());

			existingUserRoom.setRoomId(roomBookDao.getRoomId());
			userRoomRepository.save(existingUserRoom);
		}
		return USERROOM_UPDATED;
	}

	@Override
	public String roomExit(RoomEntryDto roomEntryDao) {

		if (roomEntryDao.getUserId() != null) {
			UserRoom existingUserRoom = userRoomRepository.findByUserId(roomEntryDao.getUserId());
			if (existingUserRoom == null)
				throw new EntityNotFoundException(
						com.demo.hm.ws.exception.ErrorMessages.NO_USER_RECORD_FOUND.getErrorMessage());

			if (existingUserRoom.getUserRoomStatus().equals("OUT") ) {
				return INCORRECT_ENTRY; 
			}
			if ((roomEntryDao.getUserOTP().toString()).equals(existingUserRoom.getUserOTP().toString()) 
			 && (existingUserRoom.getUserRoomStatus().equals("IN")) ){
				if (existingUserRoom.getUserOTPAccessed().equals("Y"))
					return OTP_PROCCESSED;
				existingUserRoom.setUserRoomStatus(OUT);
				existingUserRoom.setUserOTPAccessed("Y");
				userRoomRepository.save(existingUserRoom);
			} else
				return OTP_INCORRECT;
		}
		return ROOM_STATUS_UPDATED;

	}
}
