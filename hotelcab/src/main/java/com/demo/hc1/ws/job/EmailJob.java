package com.demo.hc1.ws.job;

import java.nio.charset.StandardCharsets;

import javax.mail.internet.MimeMessage;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.MessagingException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class EmailJob extends QuartzJobBean {

	@Autowired
    private JavaMailSender mailSender;

     
    
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		 
		JobDataMap jobDataMap = context.getMergedJobDataMap();
        String subject = jobDataMap.getString("subject");
        String body = jobDataMap.getString("body");
        String recipientEmail = jobDataMap.getString("email");
        String fromEmail = "mailapp@valtech.co.in";
         
			try {
				sendMail(fromEmail, recipientEmail, subject, body);
			} catch (javax.mail.MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		
	}

	private void sendMail(String fromEmail, String toEmail, String subject, String body) throws javax.mail.MessagingException  {
		
		try
		{
		MimeMessage message = mailSender.createMimeMessage();
		
		MimeMessageHelper messageHelper = new MimeMessageHelper(message, StandardCharsets.UTF_8.toString());
        messageHelper.setSubject(subject);
        messageHelper.setText(body, true);
        messageHelper.setFrom(fromEmail);
        messageHelper.setTo(toEmail);
        
        
        
        mailSender.send(message);
		}catch (MessagingException ex) {
			
		}
	}

}
