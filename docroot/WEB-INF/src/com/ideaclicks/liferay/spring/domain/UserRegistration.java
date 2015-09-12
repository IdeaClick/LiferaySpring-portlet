package com.ideaclicks.liferay.spring.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Email;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Length;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;
import org.springmodules.validation.bean.conf.loader.annotation.handler.RegExp;

@Entity
@Table(name="X_USERS")
public class UserRegistration implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final Log LOG = LogFactory.getLog(UserRegistration.class);

	@Column(name="ORGANIZATION_CODE")
	@NotBlank
	@Length(min=3 ,max=10)
	@RegExp("[A-Za-z0-9]+") 
	private String orgCode;

	@Column(name="USER_NAME")
	@NotBlank
	@RegExp("[A-Za-z0-9\\s]+") 
	private String userName;

	@Column(name="USER_EMAIL")
	@NotBlank
	@Email
	private String email;

	@Column(name="PASSWORD")
	@NotBlank
	private String pswd;


	@Column(name="USER_CONTACT")
	@NotBlank
	@RegExp("[0-9]+") 
	private String contact;

	@Column(name="STATUS")
	@NotBlank
	private String status;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	@Column(name="USER_ID")
	private String id;

	/**
	 * @return the id
	 */
	public String getId() {
		LOG.debug("inside get id"+id);
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		LOG.debug("inside set id"+id);
		this.id = id;
	}

	/**
	 * @return the contact
	 */
	public String getContact() {
		LOG.debug("inside get Contact"+contact);
		return contact;
	}

	/**
	 * @param contact the contact to set
	 */
	public void setContact(String contact) {
		LOG.debug("inside set Contact"+contact);
		this.contact = contact;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		LOG.debug("inside get email"+email);
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		LOG.debug("inside get email"+email);
		this.email = email;
	}



	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "userRegistration [id=" + id + ", userName=" + userName
				+ ", orgName=" + ", email=" + email + ", contact="
				+ contact + ", status=" + status + "]";
	}
}
