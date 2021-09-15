package com.demo.hc1.ws.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.hc1.ws.dto.CabBookDto;
import com.demo.hc1.ws.dto.UserEmailDto;
import com.demo.hc1.ws.entity.CabInfo;
import com.demo.hc1.ws.exception.EntityNotFoundException;
import com.demo.hc1.ws.exception.ErrorMessages;
import com.demo.hc1.ws.job.EmailJob;
import com.demo.hc1.ws.model.response.ScheduleEmailRequest;
import com.demo.hc1.ws.repository.CabInfoRepository;
import com.demo.hc1.ws.service.CabService;

@Service
public class CabServiceImpl implements CabService {

	@Autowired
	CabInfoRepository cabInfoRepository;
	
 
	@Autowired
    private Scheduler scheduler;
	
	@Autowired
	private Environment env;

 
	
	@Override
	public CabInfo createCabDetails(CabInfo cabInfo) {

		// Check the userid present in User

		if (cabInfo.getUserId() != null) {

			String forObject1 = new RestTemplate().getForObject(env.getProperty("USERID_URL") + "checkuser/{id}",
					String.class, cabInfo.getUserId());

			if (forObject1.equals("FALIURE"))
				throw new EntityNotFoundException(ErrorMessages.NO_USER_RECORD_FOUND.getErrorMessage());
		}

		cabInfoRepository.save(cabInfo);
		return cabInfo;
	}

	@Override
	public CabInfo bookCab(CabBookDto cabBookDto) {

		// Check the user id present in User

		if (cabBookDto.getUserId() != null) {

			String forObject1 = new RestTemplate().getForObject(env.getProperty("USERID_URL") + "checkuser/{id}",
					String.class, cabBookDto.getUserId());

			if (forObject1.equals("FALIURE"))
				throw new EntityNotFoundException(ErrorMessages.NO_USER_RECORD_FOUND.getErrorMessage());
		}

		CabInfo cabInfo = new CabInfo();
		cabInfo.setBookingDate(cabBookDto.getBookingDate());
		cabInfo.setCabstatus(cabBookDto.getCabstatus());
		cabInfo.setUserId(cabBookDto.getUserId());
		cabInfo.setPlace(cabBookDto.getPlace());
		cabInfoRepository.save(cabInfo);
 
		//get the email details 
		UserEmailDto user = new UserEmailDto();

		UserEmailDto forObject = new RestTemplate()
				.getForObject(env.getProperty("USEREMAIL_URL") + "userEmail/{id}", UserEmailDto.class, cabBookDto.getUserId());

		String emailid = forObject.getEmail();
		String name = forObject.getFirstName();
		String surname = forObject.getLastName();
		
		ScheduleEmailRequest scheduleEmailRequest= new ScheduleEmailRequest();
		
		String subject= new String("Smart Hotel Cab Service");
		
		// create the jobschedule to send email
		scheduleEmailRequest.setBody("Your Cab is ready");
		scheduleEmailRequest.setSubject(subject);
		scheduleEmailRequest.setEmail(forObject.getEmail());
		 
		ZoneId zid
        = ZoneId.of("Asia/Kolkata");
		
		LocalDateTime bookingDate1 =cabBookDto.getBookingDate();
		
		
       ZonedDateTime dateTime = ZonedDateTime.of(bookingDate1.minusHours(1), zid);
		
		JobDetail jobDetail = buildJobDetail(scheduleEmailRequest);
        Trigger trigger = buildJobTrigger(jobDetail, dateTime);
        try {
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		

		return cabInfo;

	}
	private Trigger buildJobTrigger(JobDetail jobDetail, ZonedDateTime startAt) {
		 
		return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "email-triggers")
                .withDescription("Send Email Trigger")
                .startAt(Date.from(startAt.toInstant()))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                .build();
	}

	private JobDetail buildJobDetail(ScheduleEmailRequest scheduleEmailRequest) {
		JobDataMap jobDataMap = new JobDataMap();
		
		jobDataMap.put("email", scheduleEmailRequest.getEmail());
        jobDataMap.put("subject", scheduleEmailRequest.getSubject());
        jobDataMap.put("body", scheduleEmailRequest.getBody());
        
		return JobBuilder.newJob(EmailJob.class)
                .withIdentity(UUID.randomUUID().toString(), "email-jobs")
                .withDescription("Send Email Job")
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
	}
	
	

}
