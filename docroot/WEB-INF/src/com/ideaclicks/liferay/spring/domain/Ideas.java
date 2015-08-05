package com.ideaclicks.liferay.spring.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="X_IDEAS")
public class Ideas implements Serializable {

	private static final long serialVersionUID = 1L;

@Id
@Column(name="IDEASID")
@GeneratedValue
private Integer id;

@Column(name="ORGANIZATION_CODE")
private String orgCode;

@Column(name="IDEASTITILE")
private String title;

@Column(name="IDEASDESCRIPTION")
private String desc;

@Column(name="CATEGORY")
private String category;

@Column(name="AUTHOR")
private String author;


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

public String getOrgCode() {
	return orgCode;
}


public void setOrgCode(String orgCode) {
	this.orgCode = orgCode;
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

/**
 * @return the author
 */
public String getAuthor() {
	return author;
}

/**
 * @param author the author to set
 */
public void setAuthor(String author) {
	this.author = author;
}
/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "Ideas [id=" + id + ", title=" + title + ", desc=" + desc
			+ ", category=" + category + ", author=" + author + "]";
}

}
