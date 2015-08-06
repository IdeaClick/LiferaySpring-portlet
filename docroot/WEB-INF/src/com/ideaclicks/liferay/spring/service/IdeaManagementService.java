package com.ideaclicks.liferay.spring.service;

import java.util.List;
import com.ideaclicks.liferay.spring.exception.UserException;
import com.ideaclicks.liferay.spring.exception.AdminException;
import com.ideaclicks.liferay.spring.domain.Ideas;
import com.ideaclicks.liferay.spring.domain.OrganizationRegistration;
import com.ideaclicks.liferay.spring.domain.IdeasCategory;
import com.ideaclicks.liferay.spring.domain.userRegistration;

import com.ideaclicks.liferay.spring.exception.SecurityException;

/**
 * IdeaManagementService defines methods that are used by the Web layer of the 
 * IdeaManagement portlets.
 * 
 * @author Amol Shirude
 */
public interface IdeaManagementService {
	
	/**
	 * Check Login credentials 
	 * 
	 * @param String email, String password
	 * @return success/failure of the addition
	 * @throws SecurityException
	 * 
	 */
	public boolean authenticateUser(String email, String password,String orgcode)
			throws SecurityException;
	
	/**
	 * Adds a new Organization Registration in the system.
	 * 
	 * @param Registration registration domain object
	 * @return success/failure of the addition
	 * @throws AdminException
	 */
	public boolean organizationRegistration(OrganizationRegistration registration)
			throws AdminException;
	
	/**
	 * Adds a new User Registration in the system.
	 * 
	 * @param User_Registration registration domain object
	 * @return success/failure of the addition
	 * @throws UserException
	 */
	public boolean newUserRegistration(userRegistration uRegistration)
			throws UserException;
	
	/***
     * This method returns the list of Organization.
     * @return List of Organization objects
     *  @throws AdminException
     */
    public List<OrganizationRegistration> getOrganizationNameList()throws AdminException;
    /**
	 * Forget password .
	 * 
	 * @param email object
	 * @return new password
	 * @throws AdminException
	 */
	
   public String forgetPassword(String email)throws AdminException;
   
   
   /**
	 * Submit Idea .
	 * 
	 * @param Ideas object
	 * @return  success/failure
	 * @throws UserException
	 */
   public boolean SubmitIdea(Ideas idea)throws UserException;
   
   /***
    * This method returns the list of Organization.
    * @return List of Organization objects
    *  @throws AdminException
    */
   public List<Ideas> getIdeaList(String orgzcode)throws AdminException;
   
   /***
    * This method returns the list of Ideas Category.
    * @return List of Ideas Category
    *  @throws AdminException
    */
   public List<IdeasCategory> getIdeasCategoryList()throws AdminException;
   
}
