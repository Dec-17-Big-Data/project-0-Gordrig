package com.project0.services;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.project0.dao.UserDao;
import com.project0.models.User_Model;
import com.project0.oracle.UserOracle;

public class UserService {
	
	private static UserService userService = null;
	private static UserDao userDao = null;
	private static final Logger log = LogManager.getLogger(UserService.class);

	private UserService() {
		// TODO Auto-generated constructor stub
		userDao = UserOracle.getDao();
	}
	
	public static UserService getService() {
		log.traceEntry();
		if (userService == null) {
			userService = new UserService();
		}
		
		return log.traceExit(userService);
	}
	
	public Optional<List<User_Model>> getUsers() {
		log.traceEntry();
		Optional<List<User_Model>> op = userDao.getUsers();
		
		log.traceExit(op);
		return op;
	}
	
	public void createUser(String username, String password) {
		userDao.createUser(username, password);
		return;
	}
	
	

}
