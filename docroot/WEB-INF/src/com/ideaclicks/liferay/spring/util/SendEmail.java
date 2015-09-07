package com.ideaclicks.liferay.spring.util;


import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.portlet.PortletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ideaclicks.liferay.spring.controller.ForgetPasswordController;
import com.liferay.util.bridges.mvc.MVCPortlet;

public class SendEmail extends MVCPortlet{
	
	/**
	 * This field holds the logger for this class.
	 */
	private static final Log LOG = LogFactory.getLog(SendEmail.class);
	
	String Username = GlobalConstants.EMAIL_USERNAME;
	String Password = GlobalConstants.EMAIL_PASSWORD;
	String fromEmailAddress = GlobalConstants.EMAIL_USERNAME;
	String toEmailAddress = "";
	String subject = "";
	int noOfCAPSAlpha = 1;
	int noOfDigits = 1;
	int noOfSplChars = 1;
	int minLen = 8;
	int maxLen = 12;
	char[] pswd;
	public static String tmessage;
	String password;

	public void sendEmailOrganization(String orgName,String orgCode, String toEmailAdd,String pswd, String url)throws IOException, PortletException 
	{
		try{
			tmessage="";
			subject = "IdeaClicks : Organization Registration details";
			
			String textMessage = "Welcome Administrator !";
			
			textMessage = textMessage.concat("\n\r Your Organization"+orgName+"has been registered successfully");
			textMessage = textMessage.concat("\n\r Your UserName is : "+toEmailAdd);
			textMessage = textMessage.concat("\n\r Your Password is : "+pswd);
			textMessage = textMessage.concat("\n\r Your Organization Code is : "+orgCode);
			textMessage = textMessage.concat("\n\r Your Organization specific URL is : \n\r"+url);
			textMessage = textMessage.concat("\n\r This URL would be your entry point to the portal going forward.");
			textMessage = textMessage.concat("\n\r You can now invite users to :"+
				"\n\t- register themselves (under your Organization)"+ 
				"\n\t- submit ideas"+ 
				"\n\t- view ideas"+
				"\n\t- comment and vote ideas");
			tmessage = textMessage.concat("\n\r Happy innovation !\n\r Team IdeaClicks.");
			start();
			}catch(Exception e)
		{
			System.out.println("Error"+e);
		}
	}
	
	public void sendEmailUser(String toEmailAdd,String pswd,String orgcode, String url)throws IOException, PortletException 
	{
		try{
			tmessage="";
			subject = "IdeaClicks : User Registration details";
			String textMessage = "Welcome to IdeaClicks ! ";
			
			textMessage = textMessage.concat("\n\r Your UserName is : "+toEmailAdd);
			textMessage = textMessage.concat("\n\r Your Password is : "+pswd);
			textMessage = textMessage.concat("\n\r Your Organization specific URL is : \n\r"+url);
			tmessage = textMessage.concat("\n\r You can now start submitting ideas using this URL."+
											"\n\r Happy innovation !"+
											"\n\r Team IdeaClicks.");
			start();
			}catch(Exception e)
		{
			System.out.println("Error"+e);
		}
	}


	public void sendEmailForgetPassword(String toEmailId,String pswd)throws IOException, PortletException 
	{
		try{
			tmessage="";
			subject = "IdeaClicks : Your Password";
			String textMessage = "Welcome to IdeaClicks ! ";
		
			textMessage = textMessage.concat("\n\nYour UserName is : "+toEmailId);
			textMessage = textMessage.concat("\n\nYour Password is : "+pswd); 
			
			tmessage = textMessage.concat("\n\n You can now start submitting ideas using this URL. \n\r"+ GlobalConstants.LOGIN_URL +
					"\n\r Happy innovation !"+
					"\n\r Team IdeaClicks.");
			start();
		}catch(Exception e)
		{
			System.out.println("Error"+e);
		}
	}


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

		System.out.println(" create session b4 ");
		// Create a Session object based on the properties and 
		// Authenticator object
		Session session = Session.getInstance(props,new LoginAuthenticator(Username,Password));

		System.out.println(" create session after ");

		try {

			// Create a Message object using the session created above
			Message message = new MimeMessage(session);

			// setting email address to Message from where message is being sent
			message.setFrom(new InternetAddress(fromEmailAddress));

			// setting the email addressess to which user wants to send message 
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmailAddress));

			// setting the subject for the email 
			message.setSubject(subject);

			// setting the text message which user wants to send to recipients
			message.setText(tmessage);


			System.out.println(" b4 message sent .send()");

			// Using the Transport class send() method to send message 
			Transport.send(message);
			System.out.println(" after message sent .send()");
			System.out.println("\nYour Message delivered successfully ....");
			//JOptionPane.showMessageDialog(null,"Message sent");
			System.out.println(" message sent ");
		} catch (MessagingException e) {
			LOG.error("Email Exception:"+e.getStackTrace());
			throw new RuntimeException(e);

		}  
	}
}

// Creating a class for User name and Password authentication
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