package com.project0.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class User_Model {
	
	private static User_Model current = null;
	private static final Logger log = LogManager.getLogger(User_Model.class);
	
	private Long userID;
	private String userName, password;
	private Boolean superUser, activated;
	

	public User_Model() {
		super();
		log.traceEntry();		
		log.traceExit();
	}

	public User_Model(Long userID, String userName, String password, Boolean superUser, Boolean activated) {
		super();
		log.traceEntry();
		this.userID = userID;
		this.userName = userName;
		this.password = password;
		this.superUser = superUser;
		this.activated = activated;
		log.traceExit();
	}

	public static User_Model getCurrent() {
		return current;
	}

	public static void setCurrent(User_Model current) {
		User_Model.current = current;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getSuper() {
		return superUser;
	}

	public void setSuper(Boolean super1) {
		superUser = super1;
	}

	public static Logger getLog() {
		return log;
	}
	
	public Boolean getActivated() {
		return activated;
	}

	public void setActivated(Boolean activated) {
		this.activated = activated;
	}

	@Override
	public int hashCode() {
		log.traceEntry();
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activated == null) ? 0 : activated.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((superUser == null) ? 0 : superUser.hashCode());
		result = prime * result + ((userID == null) ? 0 : userID.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return log.traceExit(result);
	}

	@Override
	public boolean equals(Object obj) {
		log.traceEntry();
		if (this == obj)
			return log.traceExit(true);
		if (obj == null)
			return log.traceExit(false);
		if (getClass() != obj.getClass())
			return log.traceExit(false);
		User_Model other = (User_Model) obj;
		if (activated == null) {
			if (other.activated != null)
				return log.traceExit(false);
		} else if (!activated.equals(other.activated))
			return log.traceExit(false);
		if (password == null) {
			if (other.password != null)
				return log.traceExit(false);
		} else if (!password.equals(other.password))
			return log.traceExit(false);
		if (superUser == null) {
			if (other.superUser != null)
				return log.traceExit(false);
		} else if (!superUser.equals(other.superUser))
			return log.traceExit(false);
		if (userID == null) {
			if (other.userID != null)
				return log.traceExit(false);
		} else if (!userID.equals(other.userID))
			return log.traceExit(false);
		if (userName == null) {
			if (other.userName != null)
				return log.traceExit(false);
		} else if (!userName.equals(other.userName))
			return log.traceExit(false);
		return log.traceExit(true);
	}

	@Override
	public String toString() {
		log.traceEntry();
		return log.traceExit("User_Model [userID=" + userID + ", userName=" + userName + ", password=" + password + ", superUser="
				+ superUser + ", activated=" + activated + "]");
	}
	
	
	
	
	
	

}
