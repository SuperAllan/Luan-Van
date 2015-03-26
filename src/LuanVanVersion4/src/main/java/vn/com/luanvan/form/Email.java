package vn.com.luanvan.form;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

public class Email{
	@Autowired
    private JavaMailSender mailSender;
	
	public void send(String to,String subject, String content) throws AddressException, MessagingException{
		MimeMessage message = mailSender.createMimeMessage();  
		message.setFrom(new InternetAddress("luanvan111327@gmail.com"));
		message.setRecipient(Message.RecipientType.TO,new InternetAddress(to)); 		
		message.setSubject(subject, "UTF-8");
		message.setText(content, "text/html; charset=UTF-8"); 
	    //sending message  
		mailSender.send(message);
	}
}