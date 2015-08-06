package  com.ideaclicks.liferay.spring.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.ideaclicks.liferay.spring.domain.IdeasCategory;
import com.ideaclicks.liferay.spring.domain.Ideas;
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
	public boolean authenticateUser(String email, String password,String orgcode) throws SecurityException{
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
		      	 
			result = ideamanagementDAO.authenticateUser(email, password,orgcode);
			LOG.debug("Valid user===="+result);	
         }
		}catch(Exception e) {
            LOG.error(e.getMessage());
            throw new SecurityException(e.getMessage());
        }
		 return result;
	}

	@Transactional
	public boolean organizationRegistration(OrganizationRegistration registration)throws AdminException {	
	
		boolean flag1 = true;
		boolean flag2 = true;
		boolean value = false;
		try{
			LOG.debug("ideamanagementDao============" + ideamanagementDAO);
			
		 	List<OrganizationRegistration> orgCodelist = ideamanagementDAO.getOrganizationCodeList();
		 	List<OrganizationRegistration> orgEmaillist = ideamanagementDAO.getOrganizationEmailList();
		 	
		 	LOG.debug("got the list============" + orgCodelist);
		 	if (orgCodelist.isEmpty() && orgEmaillist.isEmpty()) {
		 		LOG.debug("User Organization Email and code list empty============");	
		 	}
		 	else{
		 		Iterator<OrganizationRegistration> iterator = orgCodelist.iterator();
				while(iterator.hasNext()) {
					OrganizationRegistration reg = iterator.next();
					if(reg.getOrgCode().equalsIgnoreCase(registration.getOrgCode())){
						LOG.debug("Repeated Organization code============");	
						flag1 = false;
					}
				}
				Iterator<OrganizationRegistration> iterator1 = orgEmaillist.iterator();
				while(iterator1.hasNext()) {
					OrganizationRegistration reg = iterator.next();
					if(reg.getEmail().equalsIgnoreCase(registration.getEmail())){
						LOG.debug("Repeated Organization email============");	
						flag2 = false;
					}
				}			
		 	}	
		 	System.out.println("flag1 value"+flag1+"flag2 value"+flag2);
		 	if(flag1 && flag2){
		 		System.out.println("Service ------------");
		 		ideamanagementDAO.organizationRegistration(registration);
		 		value = true;
			}
		 	else{
		 		value = false;
		 	}
		}catch(DataIntegrityViolationException cve) {
            LOG.debug(" inside organization Registration service========>>"
                    + cve.getClass().getName());
            LOG.error(cve.getMessage());
            throw new AdminException(
                    " organization Name already exist please enter a new one.",
                    "error.exist.orgName");
        }
		
		return value;
	}
	
	@Transactional
	public boolean newUserRegistration(userRegistration uRegistration)
			 throws UserException {
		boolean flag= true;
		List<userRegistration> userlist;
		try{
			System.out.println("1");
			LOG.debug("ideamanagementDao============" + ideamanagementDAO);
		 	userlist = ideamanagementDAO.getUserEmailList();
	
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
					if(uReg.getEmail().equalsIgnoreCase(uRegistration.getEmail())){
						LOG.debug("Repeated user email============");	
						flag=false;
						
					}
				}
		 	}
		 	if(flag==true){
		 			System.out.println("4"); 	
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
			System.out.println("Flag======"+flag);
		return flag;
	}
	
	
	
	

	@Transactional
	public List<OrganizationRegistration> getOrganizationNameList() throws AdminException {
		
		 List<OrganizationRegistration> list ;
		 List<OrganizationRegistration> newList = null;
		try{
				LOG.debug("ideamanagementDao============" + ideamanagementDAO);
				list = ideamanagementDAO.getOrganizationNameList();
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
	
	@Transactional
	public List<Ideas> getIdeaList(String orgzcode) throws AdminException {
		
		 List<Ideas> list ;
		 List<Ideas> newList = null;
		try{
				LOG.debug("ideamanagementDao============" + ideamanagementDAO);
				list = ideamanagementDAO.getIdeaList(orgzcode);
				LOG.debug("Ideas list============" + list);
				
			if (!list.isEmpty()) { 
        	 LOG.debug("Organization List===="+list);	
        	 newList = new ArrayList<Ideas>(list);
         }
		}catch(Exception e) {
            LOG.error(e.getMessage());
            throw new AdminException(e.getMessage());
        }
		return newList;
	}
	
	@Transactional
	public List<IdeasCategory> getIdeasCategoryList() throws AdminException {
		List<IdeasCategory> list ;
		 List<IdeasCategory> newList = null;
		try{
				LOG.debug("ideamanagementDao============" + ideamanagementDAO);
				list = ideamanagementDAO.getIdeasCategoryList();
				LOG.debug("Ideas list============" + list);
				
			if (!list.isEmpty()) { 
       	 LOG.debug("Ideas Category List===="+list);	
       	 newList = new ArrayList<IdeasCategory>(list);
        }
		}catch(Exception e) {
           LOG.error(e.getMessage());
           throw new AdminException(e.getMessage());
       }
		return newList;
	}
	
	@Transactional
	public String forgetPassword(String email) throws AdminException {
		boolean flag =	false;
		String pass = null;
		 List<OrganizationRegistration> emailList ;
		try{
			LOG.debug("ideamanagementDao============" + ideamanagementDAO);
			emailList = ideamanagementDAO.getOrganizationEmailList();
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
						 		
     } else { 
    	 	pass = null;
     }
	}catch(Exception e) {
        LOG.error(e.getMessage());
    }
	System.out.print("Return Value:"+pass);
	return pass;	
}

	

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
