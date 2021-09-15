package com.demo.hm.ws.component;

import static com.demo.hm.ws.component.EmailConstant.DEFAULT_PORT;
import static com.demo.hm.ws.component.EmailConstant.FROM_EMAIL;
import static com.demo.hm.ws.component.EmailConstant.GMAIL_SMTP_SERVER;
import static com.demo.hm.ws.component.EmailConstant.SIMPLE_MAIL_TRANSFER_PROTOCAL;
import static com.demo.hm.ws.component.EmailConstant.USERNAME;

import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

 
@Component("emailsender")
@Service
public class EmailService {
	
	@Autowired 
	JavaMailSender mailSender;
	
	public void sendmail(String userName,String surname,String emailid ,String OTP) {
		
		Properties properties = new Properties();  
		properties.put("mail.smtp.host",GMAIL_SMTP_SERVER);  
		properties.put("mail.smtp.port",DEFAULT_PORT); 
		 
		properties.put("mail.smtp.auth", "true"); 
		properties.put(SIMPLE_MAIL_TRANSFER_PROTOCAL,"mail.transport.protocol");
		
		// Get the default Session object.
		Session session = Session.getInstance(properties,new javax.mail.Authenticator() {  
		     protected PasswordAuthentication getPasswordAuthentication() {  
		       return new PasswordAuthentication(USERNAME,"");
		       }  
		       });
		
		 		
		try{
		MimeMessage message = new MimeMessage(session);
		MimeMessageHelper helper = new MimeMessageHelper (message,true);
		helper.setFrom(FROM_EMAIL);
		helper.setTo(emailid);
		helper.setSubject(EmailConstant.EMAIL_SUBJECT);
		helper.setText("Hello "+ userName +" \n\n Your OPT number is :" + OTP +"\n\n Support Team" );
		
		 
        
		mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
			 
		}


}
}
