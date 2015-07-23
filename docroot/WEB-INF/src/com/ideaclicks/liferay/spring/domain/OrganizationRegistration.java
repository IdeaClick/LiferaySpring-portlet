package com.ideaclicks.liferay.spring.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="X_ORGANIZATION")
public class OrganizationRegistration implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ORGANIZATION_ID")
	@GeneratedValue
	private Integer id;
	
	

	@Column(name="ORGANIZATION_NAME")
	private String orgName;
	
	@Column(name="ORGANIZATION_TYPE")
	private String orgType;
	
	@Column(name="ORGANIZATION_EMAIL")
	private String email;
	
	@Column(name="ORGANIZATION_CONTACT")
	private long contactNo;
	
	@Column(name="STATUS")
	private String status;
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
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
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
	public long getContactNo() {
		return contactNo;
	}

	/**
	 * @param contactNo the contactNo to set
	 */
	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrganizationRegistration [id=" + id + ", orgName=" + orgName
				+ ", orgType=" + orgType + ", email=" + email + ", contactNo="
				+ contactNo + "]";
	}		
}
