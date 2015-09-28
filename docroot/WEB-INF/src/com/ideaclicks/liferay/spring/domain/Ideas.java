package com.ideaclicks.liferay.spring.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;

@Entity
@Table(name="X_IDEAS")
public class Ideas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="IDEASID",length = 10)
	@GeneratedValue
	private String id;

	@Column(name="ORGANIZATION_CODE",length = 10)
	private String orgCode;

	@Column(name="IDEASTITILE",length = 255 )
	@NotBlank 
	private String title;

	@Column(name="IDEASDESCRIPTION",length=2555)
	@NotBlank
	private String desc;

	@Column(name="CATEGORY",length = 50)
	@NotBlank
	private String category;

	@Column(name="SUBMITTED_BY",length = 50)
	private String submittedBy;

	
	@Column(name="IDEA_STATUS",length = 20)
	private String ideaStatus;
	
	

	@Column(name="LIKE_COUNT")
	private String likes_count;

	/**
	 * @return the likes_count
	 */
	public String getLikes_count() {
		return likes_count;
	}

	/**
	 * @param likes_count the likes_count to set
	 */
	public void setLikes_count(String likes_count) {
		this.likes_count = likes_count;
	}
	/**
	 * @return the dislikes_count
	 */
	public String getDislikes_count() {
		return dislikes_count;
	}
	/**
	 * @param dislikes_count the dislikes_count to set
	 */
	public void setDislikes_count(String dislikes_count) {
		this.dislikes_count = dislikes_count;
	}

	@Column(name="DISLIKE_COUNT")
	private String dislikes_count;


	/**
	 * @return the ideaStatus
	 */
	public String getIdeaStatus() {
		return ideaStatus;
	}
	/**
	 * @param ideaStatus the ideaStatus to set
	 */
	public void setIdeaStatus(String ideaStatus) {
		this.ideaStatus = ideaStatus;
	}

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
				+ ", desc=" + desc + ", category=" + category
				+ ", submittedBy=" + submittedBy + ", ideaStatus=" + ideaStatus
				+ ", likes_count=" + likes_count + ", dislikes_count="
				+ dislikes_count + "]";
	}	
}
