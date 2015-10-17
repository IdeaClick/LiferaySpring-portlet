package com.ideaclicks.liferay.spring.service;

import java.util.List;

import com.ideaclicks.liferay.spring.domain.CommentPojo;
import com.ideaclicks.liferay.spring.domain.Contact;
import com.ideaclicks.liferay.spring.domain.Ideas;
import com.ideaclicks.liferay.spring.domain.IdeasCategory;
import com.ideaclicks.liferay.spring.domain.OrganizationRegistration;
import com.ideaclicks.liferay.spring.domain.UserRegistration;
import com.ideaclicks.liferay.spring.exception.AdminException;
import com.ideaclicks.liferay.spring.exception.MinervaException;
import com.ideaclicks.liferay.spring.exception.SecurityException;
import com.ideaclicks.liferay.spring.exception.UserException;
import com.ideaclicks.liferay.spring.util.ServiceStatus;

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
	public ServiceStatus organizationRegistration(OrganizationRegistration registration)
			throws AdminException;
	
	/**
	 * Adds a new User Registration in the system.
	 * 
	 * @param User_Registration registration domain object
	 * @return success/failure of the addition
	 * @throws UserException
	 */
	public String newUserRegistration(UserRegistration uRegistration)
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
    * This method returns the list of Ideas for User.
    * @return List of Ideas
    *  @throws AdminException
    */
   public List<Ideas> getIdeaList(String orgzcode,String loggedInUser)throws AdminException;
   
   /***
    * This method returns the list of Ideas for User.
    * @return List of Ideas
    *  @throws AdminException
    */
   public List<Ideas> getIdeaListForAdmin(String orgzcode)throws AdminException;
   
   /***
    * This method returns the list of filter Ideas by category.
    * @return List of filter Ideas
    *  @throws AdminException
    */
   public List<Ideas> getIdeaFilterList(String orgscode,String loggedInUser,String filterIdeaCategory)throws AdminException;
   
   /***
    * This method returns the list of Ideas Category.
    * @return List of Ideas Category
    *  @throws AdminException
    */
   public List<IdeasCategory> getDefaultIdeasCategoryList()throws AdminException;
   public List<IdeasCategory> getOrganizationIdeasCategoryList(String OrgCode)throws AdminException;
   
   /***
    * @return boolean value
    *  @throws AdminException
    */
   public boolean contactUs(Contact contact)throws AdminException;
   
   public boolean ResetPassword(String email,String oldpswd,String newpswd)throws MinervaException;
   
   public List<Ideas> getSingleIdea(String ideasId)throws AdminException;
   
   public String getUserType(String email,String orgcode)throws AdminException;
   
   public boolean addCategory(IdeasCategory category)throws AdminException;
   
   public boolean deleteCategory(Integer categoryId)throws AdminException;
   
   public List<CommentPojo> getComment(String ideasId) throws AdminException;
   
   public void saveComment(CommentPojo c)throws AdminException;
   
   public boolean deleteIdea(String ideaId)throws AdminException;
   
}
