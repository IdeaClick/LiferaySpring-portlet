package com.ideaclicks.liferay.spring.dao;

import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ideaclicks.liferay.spring.base.DataAccessException;
import com.ideaclicks.liferay.spring.domain.CommentPojo;
import com.ideaclicks.liferay.spring.domain.Contact;
import com.ideaclicks.liferay.spring.domain.Ideas;
import com.ideaclicks.liferay.spring.domain.IdeasCategory;
import com.ideaclicks.liferay.spring.domain.OrganizationRegistration;
import com.ideaclicks.liferay.spring.domain.UserRegistration;
import com.ideaclicks.liferay.spring.util.IClicksEncriptionDecription;
@Repository
public class IdeaManagementDAOHibernateImpl implements IdeaManagementDAO{
	//no need to close sessionFactory.close() because it automatically close the connection once operation finished
	/**
	 * This field holds the logger for this class.
	 */
	private static final Log LOG = LogFactory.getLog(IdeaManagementDAOHibernateImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public boolean authenticateUser(String username, String password,String orgcode) {
		LOG.info("In login authentication");
		String sql = "from UserRegistration r " + "where r.email = ? and r.pswd = ? and r.orgCode = ?";
		List list = sessionFactory.getCurrentSession().createQuery(sql)
				.setParameter(0, username)
				.setParameter(1, IClicksEncriptionDecription.encryptPassword(password))
				.setParameter(2, orgcode)
				.list();
System.out.println(".........User Found........"+list);
		if(list.size() != 0){
			LOG.debug("User Found");
			return true;			
		}
		else{
			sql = "from OrganizationRegistration r " + "where r.email = ? and r.pswd = ? and r.orgCode = ?";
			list = sessionFactory.getCurrentSession().createQuery(sql)
					.setParameter(0, username)
					.setParameter(1, IClicksEncriptionDecription.encryptPassword(password))
					.setParameter(2, orgcode)
					.list();
			if(list.size() != 0){
				LOG.debug("User Found");
				System.out.println("...........User Found......"+list);
				return true;
			}
			else{
				LOG.debug("User Not Found");
				return false;
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<OrganizationRegistration> getuserByemail(String username)
			throws DataAccessException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(OrganizationRegistration.class);		
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.property("email"), "email");
		criteria.setProjection(Projections.distinct(projList));
		criteria.setResultTransformer(Transformers.aliasToBean(OrganizationRegistration.class));
		List<OrganizationRegistration> userList = criteria.list();
		System.out.println("List"+userList);
		return userList ;
	}

	@Override
	public void organizationRegistration(OrganizationRegistration registration) {
		System.out.println("Dao..............");
		sessionFactory.getCurrentSession().save(registration);
	}

	public void newUserRegistration(UserRegistration uRegistration) {
		sessionFactory.getCurrentSession().save(uRegistration);
	}

	@SuppressWarnings("unchecked")
	public List<OrganizationRegistration> getOrganizationCodeList() throws DataAccessException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(OrganizationRegistration.class);		
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.property("orgCode"), "orgCode");
		criteria.setProjection(Projections.distinct(projList));
		criteria.setResultTransformer(Transformers.aliasToBean(OrganizationRegistration.class));
		List<OrganizationRegistration> orgCodeList = criteria.list();
		System.out.println("List"+orgCodeList);
		return orgCodeList ;
	}

	@SuppressWarnings("unchecked")
	public List<OrganizationRegistration> getOrganizationNameList() throws DataAccessException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(OrganizationRegistration.class);		
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.property("orgName"), "orgName");
		criteria.setProjection(Projections.distinct(projList));
		criteria.setResultTransformer(Transformers.aliasToBean(OrganizationRegistration.class));
		List<OrganizationRegistration> orgList = criteria.list();
		System.out.println("List"+orgList);
		return orgList ;
	}

	@SuppressWarnings("unchecked")
	public List<OrganizationRegistration> getOrganizationEmailList(String OrgCode) throws DataAccessException {
		System.out.println("Heooooooooooo========================");
		String sql = "from OrganizationRegistration r where r.orgCode = :orgcode";
		List<OrganizationRegistration> registeredOrgEmailList=sessionFactory.getCurrentSession().createQuery(sql)
				.setParameter("orgcode",OrgCode).list();
		System.out.println("\n\n\n\n\nOrganization Email List\n\n\n"+registeredOrgEmailList);
		return registeredOrgEmailList;
	}
	
	@SuppressWarnings("unchecked")	
	public List<UserRegistration> getUserEmailList(String OrgCode) throws DataAccessException {
		String sql = "from UserRegistration u where u.orgCode = :orgcode";
		List<UserRegistration> registeredUserEmailList=sessionFactory.getCurrentSession().createQuery(sql)
				.setParameter("orgcode",OrgCode).list();
		System.out.println("User Email List"+registeredUserEmailList);
		return registeredUserEmailList ;
	}

	@SuppressWarnings("unchecked")
	public List<Ideas> getIdeaList(String orgsCode,String loggedInUser) throws DataAccessException {
		System.out.println("Orgss code"+orgsCode);
		String sql = "from Ideas where (orgCode = :orgscode and ideaStatus = :status) or (orgCode = :orgscode and submittedBy = :loggedInUser) ORDER BY id DESC";
		List<Ideas> list=sessionFactory.getCurrentSession().createQuery(sql)
				.setParameter("orgscode", orgsCode)
				.setParameter("status","public")
				.setParameter("loggedInUser",loggedInUser)
				.list();
		System.out.println("List"+list);
		return list ;
	}
	
	@SuppressWarnings("unchecked")
	public List<Ideas> getIdeaListForAdmin(String orgsCode)
			throws DataAccessException {
		System.out.println("Orgss code"+orgsCode);
		String sql = "from Ideas where orgCode = :orgscode ORDER BY id DESC";
		List<Ideas> list=sessionFactory.getCurrentSession().createQuery(sql)
				.setParameter("orgscode", orgsCode)
				.list();
		System.out.println("List"+list);
		return list ;
	}
	
	@SuppressWarnings("unchecked")
	public List<Ideas> getIdeaFilterList(String orgsCode, String loggedInUser,String filterIdeaCategory) throws DataAccessException {
		String sql = "from Ideas where (orgCode = :orgscode and ideaStatus = :status and category = :IdeaCategory) or (orgCode = :orgscode and submittedBy = :loggedInUser and category = :IdeaCategory) ORDER BY id DESC";
		List<Ideas> list=sessionFactory.getCurrentSession().createQuery(sql)
				.setParameter("orgscode", orgsCode)
				.setParameter("status","public")
				.setParameter("loggedInUser",loggedInUser)
				.setParameter("IdeaCategory",filterIdeaCategory)
				.list();
		System.out.println("List"+list);
		return list ;
	}

	@SuppressWarnings("unchecked")
	public List<IdeasCategory> getDefaultIdeasCategoryList() throws DataAccessException {
		String sql = "from IdeasCategory c where c.OrgCode = :defaultcategory";
		List<IdeasCategory> list=sessionFactory.getCurrentSession().createQuery(sql)
				.setParameter("defaultcategory", "DefaultCategory").list();
		LOG.info("Default Category List"+list);
		return list ;
	}

	@SuppressWarnings("unchecked")
	public List<IdeasCategory> getOrganizationIdeasCategoryList(String OrgCode) throws DataAccessException {
		String sql = "from IdeasCategory c where c.OrgCode = :orgcode ORDER BY id DESC";
		List<IdeasCategory> list=sessionFactory.getCurrentSession().createQuery(sql)
				.setParameter("orgcode",OrgCode).list();
		LOG.info("Organization Category List"+list);
		return list ;
	}
	
	@Override
	public String forgetPassword(String email) throws DataAccessException {
		String sql = "from OrganizationRegistration r " + "where r.email = :emailid";
		List Elist=sessionFactory.getCurrentSession().createQuery(sql)
				.setParameter("emailid", email)
				.list();
		OrganizationRegistration reg=null;
		if(Elist.size() != 0|| Elist.size()==1) {
			reg = (OrganizationRegistration) Elist.get(0);
			String password = reg.getPswd();
			System.out.println("Password:"+password);
			return password;	
		}
		else{
			String sql1 = "from UserRegistration r " + "where r.email = :emailid";
			List emailList=sessionFactory.getCurrentSession().createQuery(sql1)
					.setParameter("emailid", email)
					.list();
			System.out.println(email+"List"+emailList);
			UserRegistration userReg=null;
			if(emailList.size()!=0 || emailList.size()==1){
				userReg = (UserRegistration) emailList.get(0);
				String password = userReg.getPswd();
				System.out.println("Password:"+password);
				return password;	
			}
			else{
				return null;
			}
		}
	}

	@Override
	public boolean SubmitIdea(Ideas idea)
			throws DataAccessException {
		LOG.info("Idea Values"+idea);
		boolean b = sessionFactory.getCurrentSession().save(idea)!=null;
		return b;
	}

	@Override
	public boolean contactUs(Contact contact) throws DataAccessException {
		boolean flag = sessionFactory.getCurrentSession().save(contact) != null;
		System.out.println("Valllllllllllll"+flag);
		return flag;
	}

	@Override
	public boolean ResetPassword(String email, String oldpswd, String newpswd)
			throws DataAccessException {
		String sql;
		int value;
		sql = "UPDATE UserRegistration u " + "SET u.pswd = ? " + "where u.email = ? and u.pswd = ?";
		value = sessionFactory.getCurrentSession().createQuery(sql)
				.setParameter(0, IClicksEncriptionDecription.encryptPassword(newpswd))
				.setParameter(1, email)
				.setParameter(2, IClicksEncriptionDecription.encryptPassword(oldpswd))
				.executeUpdate();
		 System.out.println("Value"+value);
		 
		if(value == 0){
			sql = "UPDATE OrganizationRegistration u " + "SET u.pswd = ? " + "where u.email = ? and u.pswd = ?";
			value = sessionFactory.getCurrentSession().createQuery(sql)
					.setParameter(0, IClicksEncriptionDecription.encryptPassword(newpswd))
					.setParameter(1, email)
					.setParameter(2, IClicksEncriptionDecription.encryptPassword(oldpswd))
					.executeUpdate();
			System.out.println("Value1"+value);
			if(value !=0){
				return true;
			}
			else
			{
				return false;
			}
		}
		else{
			return true;
		}
		
	}

	@Override
	public List<Ideas> getSingleIdea(String ideasId) throws DataAccessException {
		String sql = "from Ideas r " + "where r.id = ?";
		List list = sessionFactory.getCurrentSession().createQuery(sql)
				.setParameter(0, ideasId)
				.list();

		return list;
	}

	@Override
	public String getUserType(String email,String orgcode) throws DataAccessException {
		String sql = "from OrganizationRegistration r " + "where r.email = :emailid and r.orgCode = :orgcode";
		List Elist=sessionFactory.getCurrentSession().createQuery(sql)
				.setParameter("emailid", email)
				.setParameter("orgcode", orgcode)
				.list();
		OrganizationRegistration reg=null;
		if(Elist.size() != 0|| Elist.size()==1) {
			reg = (OrganizationRegistration) Elist.get(0);
			String userType = reg.getUsertype();
			System.out.println("User Type:"+userType);
			return userType;	
		}
		else{
			String sql1 = "from UserRegistration r " + "where r.email = :emailid and r.orgCode = :orgcode";
			List emailList=sessionFactory.getCurrentSession().createQuery(sql1)
					.setParameter("emailid", email)
					.setParameter("orgcode", orgcode)
					.list();
			System.out.println(email+"List"+emailList);
			UserRegistration userReg=null;
			if(emailList.size()!=0 || emailList.size()==1){
				userReg = (UserRegistration) emailList.get(0);
				String userType = userReg.getUsertype();
				System.out.println("User Type:"+userType);
				return userType;	
			}
			else{
				return null;
			}
		}
	}

	@Override
	public boolean addCategory(IdeasCategory category) throws DataAccessException {
		return sessionFactory.getCurrentSession().save(category) != null;
	}

	@Override
	public boolean deleteCategory(Integer categoryId) throws DataAccessException {
		String sql = "delete from IdeasCategory i " + "where i.id = :categoryId";
	int flag = sessionFactory.getCurrentSession().createQuery(sql)
			.setParameter("categoryId", categoryId)
			.executeUpdate();
	System.out.println("Flag"+flag);
	if(flag==1)
		return true;
	else
		return false;
	}
	
	@Override
	public List<CommentPojo> getComment(String ideasId) throws DataAccessException {
		String sql = "from CommentPojo c " + "where c.parentIdeaId = ?";
		List list = sessionFactory.getCurrentSession().createQuery(sql)
				.setParameter(0, ideasId)
				.list();
		System.out.println("first level Comment"+list);
		return list;
	}
	

	@Override
	public List<CommentPojo> getChildComment(String commentId) throws DataAccessException {
		String sql = "from CommentPojo c " + "where c.parentCommentsId = ?";
		List list = sessionFactory.getCurrentSession().createQuery(sql)
				.setParameter(0, commentId)
				.list();
		System.out.println("child Comment"+list);
		return list;
	}
	
	@Override
	public void saveComment(CommentPojo c) throws DataAccessException {		
		boolean b = sessionFactory.getCurrentSession().save(c)!=null;
		System.out.println("comment saved = "+b);
	}

	@Override
	public boolean deleteIdea(String ideaId) throws DataAccessException {
		String sql = "delete from Ideas i " + "where i.id = :ideaId";
		int flag = sessionFactory.getCurrentSession().createQuery(sql)
				.setParameter("ideaId", ideaId)
				.executeUpdate();
		System.out.println("Flag"+flag);
		if(flag==1)
			return true;
		else
			return false;
		}
}
