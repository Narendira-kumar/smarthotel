package com.demo.hr.ws.component;

import org.json.JSONObject;

import java.time.LocalDateTime;

import org.json.JSONException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.hr.ws.entity.Room;
import com.demo.hr.ws.exception.ConstantMessage;
import com.demo.hr.ws.repository.RoomRepository;

@Component
public class RoomVocate {
	
	@Autowired
	RoomRepository roomRepository;
	
	@RabbitListener(queues="roomq")
	public void getMessage(byte[]  message)
	{
	
	 
		 String msg = new String(message);
	        
	     long userid2=Long.parseLong(msg);
	     
	     Room room1 = roomRepository.findByUserId(userid2);
	 	if (room1 != null)
	 	{
	 		room1.setRoomStatus(ConstantMessage.AVAILABLE);
	 		room1.setUserId(null);
	 		room1.setCheckInDate(room1.getCheckInDate());
	 		room1.setCheckOutDate(LocalDateTime.now());
	 		roomRepository.save(room1);
	 			 		 		 
	 	}
		
	}

}
