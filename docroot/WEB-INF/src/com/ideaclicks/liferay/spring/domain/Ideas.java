package com.ideaclicks.liferay.spring.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;

import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Size;

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
	@NotBlank
	private String title;

	@Column(name="IDEASDESCRIPTION")
	@NotBlank
	@Size(min=0 ,max=2000)
	private String desc;

	@Column(name="CATEGORY")
	@NotBlank
	private String category;

	@Column(name="SUBMITTED_BY")
	private String submittedBy;


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

	public String getSubmittedBy() {
		return submittedBy;
	}

	public void setSubmittedBy(String submittedBy) {
		this.submittedBy = submittedBy;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Ideas [id=" + id + ", orgCode=" + orgCode + ", title=" + title
				+ ", desc=" + desc + ", category=" + category + ", submittedBy="
				+ submittedBy + "]";
	}



}
