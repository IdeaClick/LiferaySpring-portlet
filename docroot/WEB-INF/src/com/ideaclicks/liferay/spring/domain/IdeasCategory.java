package com.ideaclicks.liferay.spring.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;

@Entity
@Table(name="X_IDEACATEGORY")
public class IdeasCategory implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CATEGORY_ID")
	@GeneratedValue
	private Integer id;

	@Column(name="OrganizationCode")
	private String OrgCode;
	
	@Column(name="CATEGORY",length=25,columnDefinition="varchar(17) default 'DefaultCategory'")
	@NotBlank
	private String category;
	
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
	 * @return the orgCode
	 */
	public String getOrgCode() {
		return OrgCode;
	}

	/**
	 * @param orgCode the orgCode to set
	 */
	public void setOrgCode(String orgCode) {
		OrgCode = orgCode;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "IdeasCategory [id=" + id + ", OrgCode=" + OrgCode
				+ ", category=" + category + "]";
	}

}
