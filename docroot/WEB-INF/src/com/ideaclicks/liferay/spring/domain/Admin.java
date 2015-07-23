package com.ideaclicks.liferay.spring.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TT_ADMIN_INFO")
public class Admin implements Serializable {
	@Id
	@Column(name="ADMIN_ID")
	@GeneratedValue
	private long id;
	
	@Column(name="ADMIN_NAME")
	private String admin_name;

	@Column(name="ORG_NAME")
	private String org_name;

	@Column(name="EMAIL_ID")
	private String email;
	
	@Column(name="MOBILE_NO")
	private String mobile_no;
	
	@Column(name="DATE_OF_BIRTH")
	private String dob;
	
	@Column(name="GENDER")
	private String sex;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="COUNTRY")
	private String country;
	
	@Column(name="STATE")
	private String state;
	
	@Column(name="CITY")
	private String city;
	
	@Column(name="IMAGE_NAME")
	private String image_name;
	
    @Column(name="PROFILE_IMAGE", nullable=false, columnDefinition="mediumblob")
    private byte[] image;

	/**
	 * @return the image_name
	 */
	public String getImage_name() {
		return image_name;
	}

	/**
	 * @param image_name the image_name to set
	 */
	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}

	/**
	 * @return the image
	 */
	public byte[] getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(byte[] image) {
		this.image = image;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the admin_name
	 */
	public String getAdmin_name() {
		return admin_name;
	}

	/**
	 * @param admin_name the admin_name to set
	 */
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}

	/**
	 * @return the org_name
	 */
	public String getOrg_name() {
		return org_name;
	}

	/**
	 * @param org_name the org_name to set
	 */
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
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
	 * @return the mobile_no
	 */
	public String getMobile_no() {
		return mobile_no;
	}

	/**
	 * @param mobile_no the mobile_no to set
	 */
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	/**
	 * @return the dob
	 */
	public String getDob() {
		return dob;
	}

	/**
	 * @param dob the dob to set
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Admin [id=" + id + ", admin_name=" + admin_name + ", org_name="
				+ org_name + ", email=" + email + ", mobile_no=" + mobile_no
				+ ", dob=" + dob + ", sex=" + sex + ", address=" + address
				+ ", country=" + country + ", state=" + state + ", city="
				+ city + "]";
	}
	
	
	
}
