package com.ideaclicks.liferay.spring.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.List;
import com.ideaclicks.liferay.spring.domain.AreaOfBusiness;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ideaclicks.liferay.spring.domain.Campaign;
import com.ideaclicks.liferay.spring.domain.OrganizationRegistration;
import com.ideaclicks.liferay.spring.domain.Ideas;
import com.ideaclicks.liferay.spring.domain.Admin;
import com.ideaclicks.liferay.spring.domain.userRegistration;
import com.ideaclicks.liferay.spring.service.IdeaManagementServiceImpl;
import com.ideaclicks.liferay.spring.base.DataAccessException;;
@Repository
public class IdeaManagementDAOHibernateImpl implements IdeaManagementDAO{
	//no need to close sessionFactory.close() because it automatically close the connection once operation finished
	/**
     * This field holds the logger for this class.
     */
    private static final Log LOG = LogFactory.getLog(IdeaManagementDAOHibernateImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
		    
	public boolean authenticateUser(String username, String password) {
		LOG.info("In login authentication");
		
		String sql = "from OrganizationRegistration r " + "where r.email = ? and r.pswd = ?";
		List list = sessionFactory.getCurrentSession().createQuery(sql)
		.setParameter(0, username)
		.setParameter(1, password)
		.list();
		
		if(list.size() != 0){
			LOG.debug("User Found");
			//System.out.println("User found");
			return true;
		}
		else
		{
			LOG.debug("User not Found");
			//System.out.println("User Not found");
			return false;
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
		
		sessionFactory.getCurrentSession().save(registration);
	
	}
	
	public void newUserRegistration(userRegistration uRegistration) {
		
		sessionFactory.getCurrentSession().save(uRegistration);
	
	}
	
	@SuppressWarnings("unchecked")
	public List<OrganizationRegistration> getOrganizationList() throws DataAccessException {
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
	public List<OrganizationRegistration> getOrgRegEmailList() throws DataAccessException {
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
	public List<userRegistration> getUserList() throws DataAccessException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(userRegistration.class);		
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.property("userName"), "userName");
		criteria.setProjection(Projections.distinct(projList));
		criteria.setResultTransformer(Transformers.aliasToBean(userRegistration.class));
		List<userRegistration> userList = criteria.list();
		System.out.println("User List"+userList);
		return userList ;
	}
	
	/*@Override
	public String forgetPassword(String email) throws DataAccessException {
		String sql = "from Registration r " + "where r.email = :emailid";
		List list=sessionFactory.getCurrentSession().createQuery(sql)
		.setParameter("emailid", email)
		.list();
		OrganizationRegistration reg=null;
		if(list.size() == 0|| list.size()>1) {
			throw new DataAccessException("Exception : No password with " + email + " emailid  found. The record may have been deleted.");	
		}
		else {
			reg = (OrganizationRegistration) list.get(0);
			String password = reg.getPswd();
			System.out.println("Password:"+password);
			return password;
		}
		
	}*/
	
	@Override
	public boolean SubmitIdea(Ideas idea)
			throws DataAccessException {
		boolean b = sessionFactory.getCurrentSession().save(idea) != null;
		return b;
		
	}

	
}
