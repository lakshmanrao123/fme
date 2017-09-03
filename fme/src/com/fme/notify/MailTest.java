package com.fme.notify;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailTest {
	public static void main(String[] args) {
		String to="lakshmanaraok5@gmail.com";
		String subject="HeloollsfkASDTLs Test";
		String msg="sdfghjkl;";
 
final String user="team@fmenterprises.co";//change accordingly  p3plcpnl1009.prod.phx3.secureserver.net
final String pass="team123*";

//1st step) Get the session object	
Properties props = new Properties(); 
props.put("mail.smtp.host", "p3plcpnl1009.prod.phx3.secureserver.net");    
props.put("mail.smtp.socketFactory.port", "465");    
props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");    
props.put("mail.smtp.auth", "true");    
props.put("mail.smtp.port", "465"); //mail.smtp.starttls.enable=true
props.put("mail.smtp.starttls.enable", true);


/*
props.put("mail.smtp.host", "p3plcpnl1009.prod.phx3.secureserver.net");  
props.put("mail.smtp.auth", "true");   
props.put("mail.smtp.starttls.enable", "true");
props.put("mail.smtp.port", "587");
props.put("mail.smtp.socketFactory.port", "465");    
props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");    
props.put("mail.smtp.port", "465");*/
 

/*if (smtp_connection.equals("TLS")) {
         props.put("mail.smtp.host", "p3plcpnl1009.prod.phx3.secureserver.net");  
props.put("mail.smtp.auth", "true");   
props.put("mail.smtp.starttls.enable", "true");
props.put("mail.smtp.port", "587");
 }  */
Session session = Session.getDefaultInstance(props,
 new javax.mail.Authenticator() {
  protected PasswordAuthentication getPasswordAuthentication() {
   return new PasswordAuthentication(user,pass);
   }
});
//2nd step)compose message
try {
 MimeMessage message = new MimeMessage(session);
 message.setFrom(new InternetAddress(user));
 message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
 message.setSubject(subject);
 message.setText(msg);
 //Session session = Session.getInstance(props);
 session.setDebug(true);
 //3rd step)send message
 Transport.send(message);

 System.out.println("Done");

 } catch (MessagingException e) {
	throw new RuntimeException(e);
 }
	
}
}
