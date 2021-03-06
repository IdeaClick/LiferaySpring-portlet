package  com.ideaclicks.liferay.spring.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.ideaclicks.liferay.spring.domain.Admin;
import com.ideaclicks.liferay.spring.domain.Campaign;
import com.ideaclicks.liferay.spring.domain.Ideas;
import com.ideaclicks.liferay.spring.domain.AreaOfBusiness;
import com.ideaclicks.liferay.spring.domain.userRegistration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ideaclicks.liferay.spring.domain.OrganizationRegistration;
import com.ideaclicks.liferay.spring.base.BusinessException;
import com.ideaclicks.liferay.spring.dao.IdeaManagementDAO;
import com.ideaclicks.liferay.spring.exception.SecurityException;
import com.ideaclicks.liferay.spring.exception.UserException;
import com.ideaclicks.liferay.spring.exception.AdminException;
@Service
public class IdeaManagementServiceImpl implements IdeaManagementService {
	
	/**
     * This field holds the logger for this class.
     */
    private static final Log LOG = LogFactory.getLog(IdeaManagementServiceImpl.class);

	@Autowired
	private IdeaManagementDAO ideamanagementDAO;
	
	@Transactional
	public boolean authenticateUser(String email, String password) throws SecurityException{
		 LOG.debug("Calling Griffing to Authenticate User >>>>");
		
		 boolean result= false;
		 try{
			  	LOG.debug("ideamanagementDao============" + ideamanagementDAO);
			 	List<OrganizationRegistration> list = ideamanagementDAO.getuserByemail(email);
			 	LOG.debug("got the list============" + list);
			 	// If user name does not exist in the db return false
			 	if (list.isEmpty()) {
			 		LOG.debug("User email is invalid============");
			 		SecurityException securityException = new SecurityException("email invalid");
			 		securityException.setErrorCode("email invalid");
			 		throw securityException;
         } else if (!list.isEmpty()) { // If user email exists in db
		      	 
			result = ideamanagementDAO.authenticateUser(email, password);
			LOG.debug("Valid user===="+result);	
         }
		}catch(Exception e) {
            LOG.error(e.getMessage());
            throw new SecurityException(e.getMessage());
        }
		 return result;
	}

	@Transactional
	public void organizationRegistration(OrganizationRegistration registration)throws AdminException {	
	Boolean flag= true;
	
		try{
			LOG.debug("ideamanagementDao============" + ideamanagementDAO);
		 	List<OrganizationRegistration> orglist = ideamanagementDAO.getOrganizationList();
		 	LOG.debug("got the list============" + orglist);
		 	if (orglist.isEmpty()) {
		 		LOG.debug("User Organization list empty============");	
		 	}
		 	else{
		 		Iterator<OrganizationRegistration> iterator = orglist.iterator();
				while(iterator.hasNext()) {
					OrganizationRegistration reg = iterator.next();
					if(reg.getOrgName().equalsIgnoreCase(registration.getOrgName())){
						LOG.debug("Repeated Organization name============");	
						flag=false;
						AdminException adminException = new AdminException("Repeated Organization name");
						adminException.setErrorCode("Repeated Organization name");
				 		throw adminException;
					}
				}
		 	}	
		 	if(flag==true){
		 		registration.setStatus("DEACTIVATE");
				ideamanagementDAO.organizationRegistration(registration);
			}
		}catch(DataIntegrityViolationException cve) {
            LOG.debug(" inside organization Registration service========>>"
                    + cve.getClass().getName());
            LOG.error(cve.getMessage());
            throw new AdminException(
                    " organization Name already exist please enter a new one.",
                    "error.exist.orgName");
        }

	}
	
	@Transactional
	public boolean newUserRegistration(userRegistration uRegistration)
			 throws UserException {
		boolean flag= true;
		List<userRegistration> userlist;
		try{
			System.out.println("1");
			LOG.debug("ideamanagementDao============" + ideamanagementDAO);
		 	userlist = ideamanagementDAO.getUserList();
	
		 	LOG.debug("user list============" + userlist);
		
		 	if (userlist.isEmpty()) {
		 		System.out.println("2");
		 		LOG.debug("User list empty============");	
		 	}
		 	else{
		 		System.out.println("3");
		 		Iterator<userRegistration> iterator = userlist.iterator();
				while(iterator.hasNext()) {
					userRegistration uReg = iterator.next();
					if(uReg.getUserName().equalsIgnoreCase(uRegistration.getUserName())){
						LOG.debug("Repeated user name============");	
						flag=false;
						UserException userException = new UserException("Repeated user name");
						userException.setErrorCode("Repeated user name");
				 		throw userException;
					}
				}
		 	}
		 	if(flag==true){
		 		uRegistration.setStatus("DEACTIVATE");		 	
				ideamanagementDAO.newUserRegistration(uRegistration);
			}
		}catch(DataIntegrityViolationException cve) {
            LOG.debug(" inside IdeaManagement user Registration service========>>"
                    + cve.getClass().getName());
            LOG.error(cve.getMessage());
            throw new UserException(
                    " userName already exist please enter a new one.",
                    "error.exist.username");
        }

		return flag;
	}
	
	
	
	

	@Transactional
	public List<OrganizationRegistration> getOrganizationList() throws AdminException {
		
		 List<OrganizationRegistration> list ;
		 List<OrganizationRegistration> newList = null;
		try{
				LOG.debug("ideamanagementDao============" + ideamanagementDAO);
				list = ideamanagementDAO.getOrganizationList();
				LOG.debug("Organization list============" + list);
				if (list.isEmpty()) {
			 		AdminException adminException = new AdminException("Empty Organization List");
			 		adminException.setErrorCode("Empty Organization List");
			 		throw adminException;
         } else if (!list.isEmpty()) { 
        	 LOG.debug("Organization List===="+list);	
        	 newList = new ArrayList<OrganizationRegistration>(list);
         }
		}catch(Exception e) {
            LOG.error(e.getMessage());
            throw new AdminException(e.getMessage());
        }
		return newList;
	}
	
	
	/*@Transactional
	public String forgetPassword(String email) throws AdminException {
		boolean flag =	false;
		String pass = null;
		 List<OrganizationRegistration> emailList ;
		try{
			LOG.debug("ideamanagementDao============" + ideamanagementDAO);
			emailList = ideamanagementDAO.getOrgRegEmailList();
			LOG.debug("Registed Organization Email Id list============" + emailList);
			if (!emailList.isEmpty()) {
				Iterator<OrganizationRegistration> iterator = emailList.iterator();
				while(iterator.hasNext()) {
					OrganizationRegistration reg = iterator.next();
					if(reg.getEmail().equalsIgnoreCase(email)){
						LOG.debug("email Id matched============");	
						flag = true;
						break;
					}
				}
				if(flag){
					pass= ideamanagementDAO.forgetPassword(email);
					LOG.debug("Your Password is============"+pass);	
				}
				else
				{
					LOG.debug("Incorrect emailId============");	
					pass=null;
				}
						 		
     } else if (emailList.isEmpty()) { 
    	    AdminException adminException = new AdminException("Empty Organization Email ID List");
	 		adminException.setErrorCode("Empty Organization Email ID List");
	 		throw adminException;	
     }
	}catch(Exception e) {
        LOG.error(e.getMessage());
        throw new AdminException(e.getMessage());
    }
		System.out.print("Return Value:"+pass);
		return pass;
}*/

	

	@Transactional
	public boolean SubmitIdea(Ideas idea)
			throws UserException  {
		boolean flag = false;
		try{
				flag=ideamanagementDAO.SubmitIdea(idea);
				
		}catch(DataIntegrityViolationException cve) {
            LOG.debug(" inside Submit Idea service========>>"
                    + cve.getClass().getName());
            LOG.error(cve.getMessage());
            throw new UserException(
                    "error in submit idea page ",
                    "error.exist.submitIdea");
        }
		return flag;
	}
}
