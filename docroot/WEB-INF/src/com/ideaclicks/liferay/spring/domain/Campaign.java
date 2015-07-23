package com.ideaclicks.liferay.spring.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_CAMPAIGN")
public class Campaign implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CAMPAIGNID")
	@GeneratedValue
	private Integer id;
	
	@Column(name="CAMPAIGNNAME")
	private String campaignName;
	
	@Column(name="ADMINID")
	private Integer adminId;
	
	@Column(name="STARTDATE")
	private Date startDate;
	
	@Column(name="ENDDATE")
	private Date endDate;
	
	@Column(name="NOTIFYFREQENCY")
	private String notifyFreqency;
	
	
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
	 * @return the campaignName
	 */
	public String getCampaignName() {
		return campaignName;
	}

	/**
	 * @param campaignName the campaignName to set
	 */
	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @param adminId the adminId to set
	 */
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	/**
	 * @return the notifyFreqency
	 */
	public String getNotifyFreqency() {
		return notifyFreqency;
	}

	/**
	 * @param notifyFreqency the notifyFreqency to set
	 */
	public void setNotifyFreqency(String notifyFreqency) {
		this.notifyFreqency = notifyFreqency;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Campaign [id=" + id + ", campaignName=" + campaignName
				+ ", adminId=" + adminId + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", notifyFreqency=" + notifyFreqency
				+ "]";
	}

	
}
