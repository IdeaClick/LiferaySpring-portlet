package  com.ideaclicks.liferay.spring.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ideaclicks.liferay.spring.dao.IdeaManagementDAO;
import com.ideaclicks.liferay.spring.domain.CommentPojo;
import com.ideaclicks.liferay.spring.domain.Contact;
import com.ideaclicks.liferay.spring.domain.Ideas;
import com.ideaclicks.liferay.spring.domain.IdeasCategory;
import com.ideaclicks.liferay.spring.domain.OrganizationRegistration;
import com.ideaclicks.liferay.spring.domain.UserRegistration;
import com.ideaclicks.liferay.spring.exception.AdminException;
import com.ideaclicks.liferay.spring.exception.SecurityException;
import com.ideaclicks.liferay.spring.exception.UserException;
import com.ideaclicks.liferay.spring.util.GlobalConstants;
import com.ideaclicks.liferay.spring.util.ServiceStatus;
@Service
public class IdeaManagementServiceImpl implements IdeaManagementService {

	ServiceStatus servicestatus = new ServiceStatus();

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
	public ServiceStatus organizationRegistration(OrganizationRegistration registration)throws AdminException {	

		boolean flag1 = true;
		boolean flag2 = true;
		boolean flag3 = true;
		boolean value = false;

		try{
			System.out.println("we r in org ");
			LOG.debug("ideamanagementDao============" + ideamanagementDAO);

			List<OrganizationRegistration> orgCodelist = ideamanagementDAO.getOrganizationCodeList();
			List<OrganizationRegistration> orgEmaillist = ideamanagementDAO.getOrganizationEmailList(registration.getOrgCode());
			//List<UserRegistration> userEmaillist = ideamanagementDAO.getUserEmailList();
			LOG.debug("got Organization Code list============" + orgCodelist);
			LOG.debug("got Organization Email list============" + orgEmaillist);
			if (orgCodelist.isEmpty()) {
				LOG.debug("User Organization Email and code list empty============");
				System.out.println("1");
			}
			else{
				Iterator<OrganizationRegistration> iterator = orgCodelist.iterator();
				while(iterator.hasNext()) {
					OrganizationRegistration reg = iterator.next();
					if(reg.getOrgCode().equalsIgnoreCase(registration.getOrgCode())){
						LOG.debug("Repeated Organization code============");
						servicestatus.setStatus(GlobalConstants.FAILED);
						servicestatus.setErrorKey(GlobalConstants.ERROR);
						servicestatus.setErrorMsg(GlobalConstants.REPEATED_ORGANIZATION_CODE);
						flag1 = false;
						System.out.println("2"+flag1);
						return servicestatus;
					}
				}
				Iterator<OrganizationRegistration> iterator1 = orgEmaillist.iterator();
				while(iterator1.hasNext()) {
					OrganizationRegistration reg = iterator1.next();
					if(reg.getEmail().equalsIgnoreCase(registration.getEmail())){
						LOG.debug("Repeated Organization email============");	
						servicestatus.setStatus(GlobalConstants.FAILED);
						servicestatus.setErrorKey(GlobalConstants.ERROR1);
						servicestatus.setErrorMsg(GlobalConstants.REPEATED_ORGANIZATION_EMAIL);
						flag2 = false;
						System.out.println("3"+flag2);
						return servicestatus;
					}
				}
				
			}	
			System.out.println("flag1 value"+flag1+"flag2 value"+flag2+"flag3 value"+flag3);
			if(flag1 && flag2 && flag3){
				System.out.println("Service ------------");
				ideamanagementDAO.organizationRegistration(registration);
				value = true;
				servicestatus.setStatus(GlobalConstants.SUCCESS);
				servicestatus.setSuccessMsg(GlobalConstants.REGISTRATION_COMPLETE);
				return servicestatus;
			}

		}catch(DataIntegrityViolationException cve) {
			LOG.debug(" inside organization Registration service========>>"
					+ cve.getClass().getName());
			LOG.error(cve.getMessage());
		
		}
		return servicestatus;
	}

	@Transactional
	public String newUserRegistration(UserRegistration uRegistration)
			throws UserException {
		boolean flag = true;
		String msg = null;
		String codefound  = "";
		try{
			System.out.println("1");
			LOG.debug("ideamanagementDao============" + ideamanagementDAO);

			List<OrganizationRegistration> orgCodelist = ideamanagementDAO.getOrganizationCodeList();
			List<UserRegistration> userEmaillist = ideamanagementDAO.getUserEmailList(uRegistration.getOrgCode());
			//List<OrganizationRegistration> orgEmailList = ideamanagementDAO.getOrganizationEmailList();
			LOG.debug("Organization Code list============" + orgCodelist);
			LOG.debug("user Email list============" + userEmaillist);

			if(!orgCodelist.isEmpty()){
				Iterator<OrganizationRegistration> iterator1 = orgCodelist.iterator();
				while(iterator1.hasNext()) {
					OrganizationRegistration reg = iterator1.next();
					if(reg.getOrgCode().equalsIgnoreCase(uRegistration.getOrgCode())){
						LOG.debug("Organization code Found============");	
						codefound="codefound";	
					}
				}
				if(!codefound.isEmpty())
				{
					if(!userEmaillist.isEmpty()){
						System.out.println("3");
						Iterator<UserRegistration> iterator = userEmaillist.iterator();
						while(iterator.hasNext()) {
							UserRegistration uReg = iterator.next();
							if(uReg.getEmail().equalsIgnoreCase(uRegistration.getEmail())){
								LOG.debug("Repeated user email============");	
								msg = "user already registered";
								System.out.println("4");
								flag = false;
								break;
							}
						}
					}	
				}
				else{
					System.out.println("5");
					flag = false;
					msg = "your organization not registered";
				}
			}
			else{
				System.out.println("6");
				flag = false;
				msg = "your organization not registered";
			}
			if(flag==true){
				System.out.println("7"); 	
				ideamanagementDAO.newUserRegistration(uRegistration);
				msg = "user registration successful";
			}


		}catch(DataIntegrityViolationException cve) {
			LOG.debug(" inside IdeaManagement user Registration service========>>"
					+ cve.getClass().getName());
			LOG.error(cve.getMessage());
		}
		System.out.println("Flag======"+flag+msg);
		return msg;
	}

	@Transactional
	public List<OrganizationRegistration> getOrganizationNameList() throws AdminException {

		List<OrganizationRegistration> list ;
		List<OrganizationRegistration> newList = null;
		try{
			LOG.debug("ideamanagementDao============" + ideamanagementDAO);
			list = ideamanagementDAO.getOrganizationNameList();
			System.out.println("Registered Organization list============" + list);
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
	public List<Ideas> getIdeaList(String orgsCode,String loggedInUser) throws AdminException {

		List<Ideas> list ;
		List<Ideas> newList = null;
		try{
			LOG.debug("ideamanagementDao============" + ideamanagementDAO);
			list = ideamanagementDAO.getIdeaList(orgsCode,loggedInUser);
			LOG.debug("Ideas list============" + list);

			if (!list.isEmpty()) { 
				LOG.debug("Ideas List===="+list);	
				newList = new ArrayList<Ideas>(list);
			}
		}catch(Exception e) {
			LOG.error(e.getMessage());
			throw new AdminException(e.getMessage());
		}
		return newList;
	}

	@Transactional
	public List<Ideas> getIdeaListForAdmin(String orgscode)
			throws AdminException {
		List<Ideas> list ;
		List<Ideas> newList = null;
		try{
			LOG.debug("ideamanagementDao============" + ideamanagementDAO);
			list = ideamanagementDAO.getIdeaListForAdmin(orgscode);
			LOG.debug("Ideas list============" + list);

			if (!list.isEmpty()) { 
				LOG.debug("Ideas List===="+list);	
				newList = new ArrayList<Ideas>(list);
			}
		}catch(Exception e) {
			LOG.error(e.getMessage());
			throw new AdminException(e.getMessage());
		}
		return newList;
	}


	@Transactional
	public List<Ideas> getIdeaFilterList(String orgsCode, String loggedInUser,String filterIdeaCategory) throws AdminException {
		List<Ideas> list;
		try{
			list = ideamanagementDAO.getIdeaFilterList(orgsCode, loggedInUser, filterIdeaCategory);
			LOG.info("Filter Ideas List===="+list);
		}catch(Exception e) {
			LOG.error(e.getMessage());
			throw new AdminException(e.getMessage());
		}
		return list;
	}

	@Transactional
	public List<IdeasCategory> getDefaultIdeasCategoryList() throws AdminException {
		List<IdeasCategory> list ;
		List<IdeasCategory> newList = null;
		try{
			LOG.debug("ideamanagementDao============" + ideamanagementDAO);
			list = ideamanagementDAO.getDefaultIdeasCategoryList();
			if (!list.isEmpty()) { 
				newList = new ArrayList<IdeasCategory>(list);
			}
		}catch(Exception e) {
			LOG.error(e.getMessage());
			throw new AdminException(e.getMessage());
		}
		return newList;
	}
	@Transactional
	public List<IdeasCategory> getOrganizationIdeasCategoryList(String OrgCode)throws AdminException{
		List<IdeasCategory> list ;
		List<IdeasCategory> newList = null;
		try{
			LOG.debug("ideamanagementDao============" + ideamanagementDAO);
			list = ideamanagementDAO.getOrganizationIdeasCategoryList(OrgCode);
			if (!list.isEmpty()) { 
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
		String pass = null;
		try{	
			pass= ideamanagementDAO.forgetPassword(email);
			LOG.debug("Your Password is============"+pass);	
			return pass;

		}catch(Exception e) {
			LOG.error(e.getMessage());
		}
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

	@Transactional
	public boolean contactUs(Contact contact) throws AdminException {
		boolean flag = false;
		try{
			flag=ideamanagementDAO.contactUs(contact);

		}catch(DataIntegrityViolationException cve) {
			LOG.debug(" inside Contact us service========>>"
					+ cve.getClass().getName());
			LOG.error(cve.getMessage());
		}
		return flag;
	}

	@Transactional
	public boolean ResetPassword(String email, String oldpswd, String newpswd) {

		return ideamanagementDAO.ResetPassword(email, oldpswd, newpswd);
	}

	@Transactional
	public List<Ideas> getSingleIdea(String ideasId) throws AdminException {
		List list =ideamanagementDAO.getSingleIdea(ideasId);
		return list;
	}

	@Transactional
	public String getUserType(String email) throws AdminException {
		String userType = ideamanagementDAO.getUserType(email);
		return userType;
	}

	@Transactional
	public boolean addCategory(IdeasCategory categoryObj) throws AdminException {
		List<IdeasCategory> orgCategoryList;
		List<IdeasCategory> defaultCategoryList;
		IdeasCategory ideaCatObj;
		LOG.debug("ideamanagementDao============" + ideamanagementDAO);
		defaultCategoryList = ideamanagementDAO.getDefaultIdeasCategoryList();
		orgCategoryList = ideamanagementDAO.getOrganizationIdeasCategoryList(categoryObj.getOrgCode());
		if (!defaultCategoryList.isEmpty()||!orgCategoryList.isEmpty()) { 
			Iterator<IdeasCategory> iterator = defaultCategoryList.iterator();
			Iterator<IdeasCategory> iterator1 = orgCategoryList.iterator();
			while(iterator.hasNext()) {
				ideaCatObj = iterator.next();
				if(ideaCatObj.getCategory().equalsIgnoreCase(categoryObj.getCategory())){
					LOG.debug("Repeated Organization Category============");
					return false;
				}
			}
			while(iterator1.hasNext()) {
				ideaCatObj = iterator1.next();
				if(ideaCatObj.getCategory().equalsIgnoreCase(categoryObj.getCategory())){
					LOG.debug("Repeated Organization Category============");
					return false;
				}
			}
		}
		return ideamanagementDAO.addCategory(categoryObj);
	}

	@Transactional
	public boolean deleteCategory(Integer categoryId) throws AdminException {
		return ideamanagementDAO.deleteCategory(categoryId);
	}

	@Transactional
	public void saveComment(CommentPojo comment) throws AdminException {
		ideamanagementDAO.saveComment(comment);
		//return list;
	}

	@Transactional
	public List<CommentPojo> getComment(String ideasId) throws AdminException {
		ArrayList<CommentPojo> list =(ArrayList<CommentPojo>) ideamanagementDAO.getComment(ideasId);
		Integer level =1;
		for(CommentPojo cp:list){
			cp.setLevel(level*20);
			getChildComment(cp,level);
		}
		System.out.println(list);
		return list;
	}

	private List<CommentPojo> getChildComment(CommentPojo comment,Integer level){
		ArrayList<CommentPojo> list =(ArrayList<CommentPojo>) ideamanagementDAO.getChildComment(comment.getCommentsId());
		comment.setChildComment(list);
		for(CommentPojo cp:list){
			cp.setLevel(++level*20);
			getChildComment(cp, level);
			--level;
		}
		
		return list;		
	}

	@Transactional
	public boolean deleteIdea(String ideaId) throws AdminException {
	return ideamanagementDAO.deleteIdea(ideaId);
	} 
	
}


