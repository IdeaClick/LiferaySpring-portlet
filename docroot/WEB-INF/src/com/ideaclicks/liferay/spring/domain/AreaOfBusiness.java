package com.ideaclicks.liferay.spring.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_AREA_OF_BUSINESS")
public class AreaOfBusiness implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="BUSINESS_ID")
	@GeneratedValue
	private long id;
	
	@Column(name="AREA_OF_BUSINESS")
	private String area_of_business;
	
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
	 * @return the area_of_business
	 */
	public String getArea_of_business() {
		return area_of_business;
	}

	/**
	 * @param area_of_business the area_of_business to set
	 */
	public void setArea_of_business(String area_of_business) {
		this.area_of_business = area_of_business;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AreaOfBusiness [id=" + id + ", area_of_business="
				+ area_of_business + "]";
	}

	

	
	

}
