package com.ideaclicks.liferay.spring.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="A_IDEAS")
public class Ideas implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Id
@Column(name="Ideas_Id")
@GeneratedValue
private Integer id;

@Column(name="Ideas_Title")
private String title;

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
 * @return the title
 */
public String getTitle() {
	return title;
}

/**
 * @param title the title to set
 */
public void setTitle(String title) {
	this.title = title;
}

/**
 * @return the desc
 */
public String getDesc() {
	return desc;
}

/**
 * @param desc the desc to set
 */
public void setDesc(String desc) {
	this.desc = desc;
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

@Column(name="Ideas_description")
private String desc;

@Column(name="category")
private String category;


}
