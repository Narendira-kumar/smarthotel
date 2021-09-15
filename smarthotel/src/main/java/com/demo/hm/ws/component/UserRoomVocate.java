package com.demo.hm.ws.component;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.hm.ws.entity.UserRoom;
import com.demo.hm.ws.repository.UserRoomRepository;

@Component
public class UserRoomVocate {

	@Autowired
	UserRoomRepository userRoomRepository;
	
	@RabbitListener(queues="userroomq")
	public void getMessage(byte[]  message)
	{
	
		 
	     String msg = new String(message) ;
	     long userid1=Long.parseLong(msg);
	     
	     UserRoom userRoom = userRoomRepository.findByUserId(userid1);
	 	if (userRoom != null)
	 	{
	 		userRoom.setRoomId(null);
	 		userRoom.setUserOTP(" ");
	 		userRoom.setUserRoomStatus("");
	 		userRoom.setUserOTP("");
	 		userRoom.setUserOTPAccessed(" ");
	 		 
	 		userRoomRepository.save(userRoom);
	 			 		 		
	 	}
		
	}

}
