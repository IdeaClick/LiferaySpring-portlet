package com.ideaclicks.liferay.spring.util;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.swing.JOptionPane;

import com.liferay.util.bridges.mvc.MVCPortlet;
 
public class SendEmail extends MVCPortlet{
	String Username = "amolshirude001@gmail.com";
	String Password = "HappyTime";
	String fromEmailAddress = "amolshirude001@gmail.com";
	String toEmailAddress;
	String subject = "IdeaClicks : Organization Registration details";
	String textMessage = "Welcome ! \n\r\n\rOrganization registered successfully ! ";
	int noOfCAPSAlpha = 1;
    int noOfDigits = 1;
    int noOfSplChars = 1;
    int minLen = 8;
    int maxLen = 12;
    char[] pswd;
    public static String tmessage;
    String password;
    
    public void getDetails(String toEmailAdd,String pass,String orgcode)throws IOException, PortletException 
    {
    	try{
    	System.out.println("Emaillllll"+toEmailAdd+"passsssssss"+pass);
    	password = pass;
    	toEmailAddress=toEmailAdd;
    	textMessage=textMessage.concat("\n\nYour UserName is : ");
    	textMessage=textMessage.concat(toEmailAdd);
    	textMessage=textMessage.concat("\n\nYour Password is : "); 
    	textMessage = textMessage.concat(password);
    	tmessage=textMessage.concat("\n\nPlease make your users visit :\n\r"+
    		"http://ideaclicks.in and to register themselves as NewUser using organization code as:   "+orgcode+
    			"\n Happy innovation !\n"+
    			"Team\n"+
    			"IdeaClicks.");
    	start();
    	}catch(Exception e)
    	{
    		System.out.println("Error"+e);
    	}
    }
/*	
 @Override
	public void processAction(ActionRequest actionRequest,
			ActionResponse actionResponse) throws IOException, PortletException {
		// TODO Auto-generated method stub
	 
         pswd = RandomPasswordGenerator.generatePswd(minLen, maxLen,noOfCAPSAlpha, noOfDigits, noOfSplChars);
         System.out.println("Len = " + pswd.length + ", " + new String(pswd));
         
	    	ss=textMessage.concat(password);
         System.out.println("Pass:"+ss);
         SendEmail snd = new SendEmail();
	     snd.start();
		super.processAction(actionRequest, actionResponse);
	}*/
 
 private void start() {
 
  // For establishment of email client with 
  // Google's gmail use below properties.
  // For TLS Connection use below properties
  // Create a Properties object 
  Properties props = new Properties();
 
  // For SSL Connection use below properties
 
   props.put("mail.smtp.host", "smtp.gmail.com");
   props.put("mail.smtp.socketFactory.port", "465");
   props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
   props.put("mail.smtp.auth", "true");
   props.put("mail.smtp.port", "465");
 
  
  // Create a Session object based on the properties and 
  // Authenticator object
  Session session = Session.getInstance(props,new LoginAuthenticator(Username,Password));
 
  try {
 
   // Create a Message object using the session created above
   Message message = new MimeMessage(session);
 
   // setting email address to Message from where message is being sent
   message.setFrom(new InternetAddress(fromEmailAddress));
 
   // setting the email addressess to which user wants to send message 
   message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(toEmailAddress));
 
   // setting the subject for the email 
   message.setSubject(subject);
 
   // setting the text message which user wants to send to recipients
   message.setText(tmessage);
 
   // Using the Transport class send() method to send message 
   Transport.send(message);
 
   System.out.println("\nYour Message delivered successfully ....");
   //JOptionPane.showMessageDialog(null,"Message sent");
 
  } catch (MessagingException e) {
 
   throw new RuntimeException(e);
 
  }  
 }
}
 
// Creating a class for Username and Password authentication
// provided by the user.
class LoginAuthenticator extends Authenticator {
 PasswordAuthentication authentication = null;
 
 public LoginAuthenticator(String username, String password) {
  authentication = new PasswordAuthentication(username,password);
 }
 
 @Override
 protected PasswordAuthentication getPasswordAuthentication() {
  return authentication;
 }
}