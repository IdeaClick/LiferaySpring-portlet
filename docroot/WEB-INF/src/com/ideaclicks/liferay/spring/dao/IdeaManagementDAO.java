package com.ideaclicks.liferay.spring.dao;

import java.util.List;
import com.ideaclicks.liferay.spring.domain.AreaOfBusiness;
import com.ideaclicks.liferay.spring.domain.Campaign;
import com.ideaclicks.liferay.spring.domain.Ideas;
import com.ideaclicks.liferay.spring.domain.OrganizationRegistration;
import com.ideaclicks.liferay.spring.domain.Admin;
import com.ideaclicks.liferay.spring.domain.userRegistration;
import com.ideaclicks.liferay.spring.exception.AdminException;
import com.ideaclicks.liferay.spring.exception.UserException;
import com.ideaclicks.liferay.spring.base.BusinessException;
import com.ideaclicks.liferay.spring.base.DataAccessException;
public interface IdeaManagementDAO {
	
	/*
	 * This method check login credentials
	 * @return boolean value true or false
	 * @throws DataAccessException
	 * */
	public boolean authenticateUser(String username, String password)
			throws DataAccessException;
	
	public List<OrganizationRegistration> getuserByemail(String username)
			throws DataAccessException;
	
	/*
	 * This method for the Organization Registration
	 * @throws DataAccessException
	 * */
	public void organizationRegistration(OrganizationRegistration registration)
			throws DataAccessException;
	/*
	 * This method for the User Registration
	 * @throws DataAccessException
	 * */
	public void newUserRegistration(userRegistration registration)
			throws DataAccessException;
	
    
    /**
     * This method returns the collection of Organization
     * 
     * @return List Of Organization
     * @throws DataAccessException
     */
    public List<OrganizationRegistration> getOrganizationList()throws DataAccessException;
    
    /**
     * This method returns the list of user
     * 
     * @return List Of user
     * @throws DataAccessException
     */
    public List<userRegistration> getUserList()throws DataAccessException;
    
    /**
     * This method returns the list Of registered organization email List for forget password  
     * 
     * @return List Of registered organization email List 
     * @throws DataAccessException
     */
    public List<OrganizationRegistration> getOrgRegEmailList() throws DataAccessException;
	/**
	 * Forget password.
     * @param String email
	 * @return string password
	 * @throws DataAccessException
	 */
   /* public String forgetPassword(String email) throws DataAccessException;*/
    /**
	 * Submit Idea .
	 * 
	 * @param Ideas object
	 * @return  success/failure
	 * @throws DataAccessException
	 */
    public boolean SubmitIdea(Ideas idea)throws DataAccessException;
}
