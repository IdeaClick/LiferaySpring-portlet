package com.ideaclicks.liferay.spring.domain;

import java.io.Serializable;

import org.springmodules.validation.bean.conf.loader.annotation.handler.Email;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;

public class ResetPassword implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	private String oldpswd;
	
	@NotBlank
	private String newpswd;
	
	@NotBlank
	private String confirmpswd;

	/**
	 * @return the oldpswd
	 */
	public String getOldpswd() {
		return oldpswd;
	}

	/**
	 * @param oldpswd the oldpswd to set
	 */
	public void setOldpswd(String oldpswd) {
		this.oldpswd = oldpswd;
	}

	/**
	 * @return the newpswd
	 */
	public String getNewpswd() {
		return newpswd;
	}

	/**
	 * @param newpswd the newpswd to set
	 */
	public void setNewpswd(String newpswd) {
		this.newpswd = newpswd;
	}

	/**
	 * @return the confirmpswd
	 */
	public String getConfirmpswd() {
		return confirmpswd;
	}

	/**
	 * @param confirmpswd the confirmpswd to set
	 */
	public void setConfirmpswd(String confirmpswd) {
		this.confirmpswd = confirmpswd;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ResetPassword [oldpswd=" + oldpswd
				+ ", newpswd=" + newpswd + ", confirmpswd=" + confirmpswd + "]";
	}
	
	

}
