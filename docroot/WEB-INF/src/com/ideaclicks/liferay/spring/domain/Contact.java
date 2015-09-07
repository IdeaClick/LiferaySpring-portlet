package com.ideaclicks.liferay.spring.domain;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springmodules.validation.bean.conf.loader.annotation.handler.Email;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;
@Entity
@Table(name="CONTACTS")
public class Contact implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private String id;

	@Column(name="Name")
	@NotBlank
	private String yourName;


	@Column(name="Email")
	@NotBlank
	@Email
	private String email;

	@Column(name="Message")
	@NotBlank
	private String message;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the yourName
	 */
	public String getYourName() {
		return yourName;
	}

	/**
	 * @param yourName the yourName to set
	 */
	public void setYourName(String yourName) {
		this.yourName = yourName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Contact [id=" + id + ", yourName=" + yourName + ", email="
				+ email + ", message=" + message + "]";
	}

	
}
