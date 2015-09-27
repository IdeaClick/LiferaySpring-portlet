package com.ideaclicks.liferay.spring.dao;

import java.util.Map;


import com.liferay.portal.security.auth.*;
import com.liferay.portal.security.auth.Authenticator;

public class ICCustomLoginAuthenticator implements  Authenticator{
	public static void main(String [] args){
	}

	@Override
	public int authenticateByEmailAddress(long arg0, String arg1, String arg2,
			Map<String, String[]> arg3, Map<String, String[]> arg4)
			throws AuthException {
		// TODO Auto-generated method stub
		System.out.println("logincalllllllllllleeeeedddd11");
		return Authenticator.SUCCESS;
	}

	@Override
	public int authenticateByScreenName(long arg0, String arg1, String arg2,
			Map<String, String[]> arg3, Map<String, String[]> arg4)
			throws AuthException {
		// TODO Auto-generated method stub
		System.out.println("logincalllllllllllleeeeedddd12");
		return Authenticator.SUCCESS;
	}

	@Override
	public int authenticateByUserId(long arg0, long arg1, String arg2,
			Map<String, String[]> arg3, Map<String, String[]> arg4)
			throws AuthException {
		// TODO Auto-generated method stub
		System.out.println("logincalllllllllllleeeeedddd13");
		return Authenticator.SUCCESS;
	}
}

