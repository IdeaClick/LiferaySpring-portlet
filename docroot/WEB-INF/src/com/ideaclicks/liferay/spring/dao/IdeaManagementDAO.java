package com.ideaclicks.liferay.spring.dao;

import java.util.List;

import com.ideaclicks.liferay.spring.base.DataAccessException;
import com.ideaclicks.liferay.spring.domain.CommentPojo;
import com.ideaclicks.liferay.spring.domain.Contact;
import com.ideaclicks.liferay.spring.domain.Ideas;
import com.ideaclicks.liferay.spring.domain.IdeasCategory;
import com.ideaclicks.liferay.spring.domain.OrganizationRegistration;
import com.ideaclicks.liferay.spring.domain.UserRegistration;
public interface IdeaManagementDAO {
	/*
	 * This method check login credentials
	 * @return boolean value true or false
	 * @throws DataAccessException
	 */
	public boolean authenticateUser(String username, String password ,String orgcode)
			throws DataAccessException;

	public List<OrganizationRegistration> getuserByemail(String username)
			throws DataAccessException;
	/*
	 * This method for the Organization Registration
	 * @throws DataAccessException
	 */
	public void organizationRegistration(OrganizationRegistration registration)
			throws DataAccessException;
	/*
	 * This method for the User Registration
	 * @throws DataAccessException
	 */
	public void newUserRegistration(UserRegistration registration)
			throws DataAccessException;
	/**
	 * This method returns the collection of Organization
	 * @return List Of Organization
	 * @throws DataAccessException
	 */
	public List<OrganizationRegistration> getOrganizationNameList()throws DataAccessException;
	/**
	 * This method returns the collection of Organization
	 * @return List Of Organization
	 * @throws DataAccessException
	 */
	public List<OrganizationRegistration> getOrganizationCodeList()throws DataAccessException;
	/**
	 * This method returns the collection of Ideas for User
	 * @return List Of Ideas
	 * @throws DataAccessException
	 */
	public List<Ideas> getIdeaList(String orgsCode,String loggedInUser)throws DataAccessException;
	/**
	 * This method returns the collection of Ideas for User
	 * @return List Of Ideas
	 * @throws DataAccessException
	 */
	public List<Ideas> getIdeaListForAdmin(String orgsCode)throws DataAccessException;
	
	/**
	 * This method returns the collection of filter Ideas by category
	 * @return List Of Ideas
	 * @throws DataAccessException
	 */
	public List<Ideas> getIdeaFilterList(String orgsCode,String loggedInUser,String filterIdeaCategory)throws DataAccessException;
	/**
	 * This method returns the collection of Ideas Category
	 * @return List Of Ideas Category
	 * @throws DataAccessException
	 */
	public List<IdeasCategory> getDefaultIdeasCategoryList()throws DataAccessException;
	public List<IdeasCategory> getOrganizationIdeasCategoryList(String OrgCode) throws DataAccessException;
	/**
	 * This method returns the list of user
	 * @return List Of user
	 * @throws DataAccessException
	 */
	public List<UserRegistration> getUserEmailList(String orgcode)throws DataAccessException;
	/**
	 * This method returns the list Of registered organization email List for forget password  
	 * @return List Of registered organization email List 
	 * @throws DataAccessException
	 */
	public List<OrganizationRegistration> getOrganizationEmailList(String OrgCode) throws DataAccessException;
	/**
	 * Forget password.
	 * @param String email
	 * @return string password
	 * @throws DataAccessException
	 */
	public String forgetPassword(String email) throws DataAccessException;
	/**
	 * Submit Idea .
	 * @param Ideas object
	 * @return  success/failure
	 * @throws DataAccessException
	 */
	public boolean SubmitIdea(Ideas idea)throws DataAccessException;
	
	/**
	 * Contact Us .
	 * @param Contact object
	 * @return  success/failure
	 * @throws DataAccessException
	 */
	public boolean contactUs(Contact contact)throws DataAccessException;
	
	public boolean ResetPassword(String email,String oldpswd,String newpswd)throws DataAccessException;
	
	 public List<Ideas> getSingleIdea(String ideasId)throws DataAccessException;
	 
	 public String getUserType(String email,String orgcode)throws DataAccessException;
	 
	 public boolean addCategory(IdeasCategory category) throws DataAccessException;
	 
	 public boolean deleteCategory(Integer categoryId)throws DataAccessException;
	 
	 public List<CommentPojo> getComment(String ideasId) throws DataAccessException;
	 
	 public	void saveComment(CommentPojo c) throws DataAccessException;

	public List<CommentPojo> getChildComment(String commentId) throws DataAccessException;
		
	public boolean deleteIdea(String ideaId) throws DataAccessException;
}
