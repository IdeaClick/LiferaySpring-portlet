package com.ideaclicks.liferay.spring.dao;

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
		System.out.println("Encrypted password"+IClicksEncriptionDecription.encryptPassword(password));
		String sql = "from UserRegistration r " + "where r.email = ? and r.pswd = ? and r.orgCode = ?";
		List list = sessionFactory.getCurrentSession().createQuery(sql)
				.setParameter(0, username)
				.setParameter(1, IClicksEncriptionDecription.encryptPassword(password))
				.setParameter(2, orgcode)
				.list();

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
	public List<OrganizationRegistration> getOrganizationEmailList() throws DataAccessException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(OrganizationRegistration.class);		
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.property("email"), "email");
		criteria.setProjection(Projections.distinct(projList));
		criteria.setResultTransformer(Transformers.aliasToBean(OrganizationRegistration.class));
		List<OrganizationRegistration> emailList = criteria.list();
		System.out.println("List"+emailList);
		return emailList ;
	}

	@SuppressWarnings("unchecked")
	public List<Ideas> getIdeaList(String orgzcode) throws DataAccessException {
		System.out.println("Orgss code"+orgzcode);
		String sql = "from Ideas where orgCode = :orgscode ORDER BY id DESC";
		List<Ideas> list=sessionFactory.getCurrentSession().createQuery(sql)
				.setParameter("orgscode", orgzcode)
				.list();
		System.out.println("List"+list);
		return list ;
	}

	@SuppressWarnings("unchecked")
	public List<IdeasCategory> getIdeasCategoryList() throws DataAccessException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(IdeasCategory.class);		
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.property("category"), "category");
		criteria.setProjection(Projections.distinct(projList));
		criteria.setResultTransformer(Transformers.aliasToBean(IdeasCategory.class));
		List<IdeasCategory> IdeaCategoryList = criteria.list();
		System.out.println("List"+IdeaCategoryList);
		return IdeaCategoryList ;
	}

	@SuppressWarnings("unchecked")	
	public List<UserRegistration> getUserEmailList() throws DataAccessException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UserRegistration.class);		
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.property("email"), "email");
		criteria.setProjection(Projections.distinct(projList));
		criteria.setResultTransformer(Transformers.aliasToBean(UserRegistration.class));
		List<UserRegistration> userList = criteria.list();
		System.out.println("User List"+userList);
		return userList ;
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
		boolean b = sessionFactory.getCurrentSession().save(idea) != null;
		return b;
	}

	@Override
	public boolean contactUs(Contact contact) throws DataAccessException {
		boolean flag = sessionFactory.getCurrentSession().save(contact) != null;
		System.out.println("Valllllllllllll"+flag);
		return flag;
	}
}
