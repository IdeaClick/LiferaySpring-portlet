package com.ideaclicks.liferay.spring.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_ORG_INFO")
public class Registration implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ORGANIZATION_ID")
	@GeneratedValue
	private Integer id;
	
	@Column(name="ORGANIZATION_NAME")
	private String org_name;
	
	@Column(name="PASSWORD")
	private String pswd;
	
	@Column(name="AREA_OF_BUSINESS")
	private String areabuss;
	
	@Column(name="CONTACT_NO")
	private long phno;
	
	@Column(name="EMAIL_ID")
	private String email;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="COUNTRY")
	private String country;
	
	@Column(name="STATE")
	private String state;
	
	@Column(name="CITY")
	private String city;

	private String oldpass;
	private String newpass;
	private String confirmpass;
	/**
	 * @return the oldpass
	 */
	public String getOldpass() {
		return oldpass;
	}

	/**
	 * @param oldpass the oldpass to set
	 */
	public void setOldpass(String oldpass) {
		this.oldpass = oldpass;
	}

	/**
	 * @return the newpass
	 */
	public String getNewpass() {
		return newpass;
	}

	/**
	 * @param newpass the newpass to set
	 */
	public void setNewpass(String newpass) {
		this.newpass = newpass;
	}

	/**
	 * @return the confirmpass
	 */
	public String getConfirmpass() {
		return confirmpass;
	}

	/**
	 * @param confirmpass the confirmpass to set
	 */
	public void setConfirmpass(String confirmpass) {
		this.confirmpass = confirmpass;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
	 * @return the areabuss
	 */
	public String getAreabuss() {
		return areabuss;
	}

	/**
	 * @param areabuss the areabuss to set
	 */
	public void setAreabuss(String areabuss) {
		this.areabuss = areabuss;
	}

	/**
	 * @return the phno
	 */
	public long getPhno() {
		return phno;
	}

	/**
	 * @param phno the phno to set
	 */
	public void setPhno(long phno) {
		this.phno = phno;
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
		return "Registration [id=" + id + ", org_name=" + org_name + ", pswd="
				+ pswd + ", areabuss=" + areabuss + ", phno=" + phno
				+ ", email=" + email + ", address=" + address + ", country="
				+ country + ", state=" + state + ", city=" + city + "]";
	}

	
		
}
