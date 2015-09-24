package com.ideaclicks.liferay.spring.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springmodules.validation.bean.conf.loader.annotation.handler.Email;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Length;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;
import org.springmodules.validation.bean.conf.loader.annotation.handler.RegExp;

@Entity
@Table(name="X_ORGANIZATION")
public class OrganizationRegistration implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ORGANIZATION_ID")
	@GeneratedValue
	private String id;

	@Column(name="ORGANIZATION_NAME")
	@NotBlank
	private String orgName;

	@Column(name="ORGANIZATION_CODE")
	@NotBlank
	private String orgCode;

	@Column(name="ORGANIZATION_TYPE")
	@NotBlank
	private String orgType;

	@Column(name="ORGANIZATION_EMAIL")
	@NotBlank
	@Email
	private String email;

	@Column(name="PASSWORD")
	@NotBlank
	private String pswd;
	
	private String cpswd;
	
	@Column(name="ORGANIZATION_CONTACT")
	@NotBlank
	private String contact;

	@Column(name="STATUS")
	@NotBlank
	private String status;


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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = "DEACTIVATE";
	}

	/**
	 * @return the orgName
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * @param orgName the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * @return the orgType
	 */
	public String getOrgType() {
		return orgType;
	}

	/**
	 * @param orgType the orgType to set
	 */
	public void setOrgType(String orgType) {
		this.orgType = orgType;
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
	 * @return the contactNo
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * @param contactNo the contactNo to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * @return the pswd
	 */
	public String getPswd() {
		return pswd;
	}

	/**
	 * @param pswd the pswd to set
	 */
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	/**
	 * @return the cpswd
	 */
	public String getCpswd() {
		return cpswd;
	}
	/**
	 * @param cpswd the cpswd to set
	 */
	public void setCpswd(String cpswd) {
		this.cpswd = cpswd;
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
		return "OrganizationRegistration [id=" + id + ", orgName=" + orgName
				+ ", orgCode=" + orgCode + ", orgType=" + orgType + ", email="
				+ email + ", pswd=" + pswd + ", cpswd=" + cpswd
				+ ", contactNo=" + contact + ", status=" + status + "]";
	}
}
