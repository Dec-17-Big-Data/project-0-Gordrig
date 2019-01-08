package com.project0.oracle;

import com.project0.dao.UserDao;
import com.project0.models.User_Model;

import com.project0.util.ConnectionUtil;

import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserOracle implements UserDao{

	private static UserOracle instance = null;
	private static final Logger log = LogManager.getLogger(UserOracle.class);
	
	private UserOracle() {
	}
	
	public static UserDao getDao() {
		log.traceEntry();
		if (instance == null) {
			instance = new UserOracle();
		}
		return log.traceExit(instance);
	}

	public Optional<List<User_Model>> getUsers() {
		log.traceEntry();
		
		Connection con = null;
		
		try {
			con = ConnectionUtil.getConnection();
		} catch (Exception e) {
			log.catching(e);
			log.traceExit();
		}
		
		if (con == null) {
			log.traceExit(Optional.empty());
			return Optional.empty();
		}
		
		try {
			
			ResultSet rs;
			
			CallableStatement cs = con.prepareCall("{call get_users(?,?,?)}");
			cs.setString(1, User_Model.getCurrent().getUserName());
			cs.setString(2, User_Model.getCurrent().getPassword());
			cs.registerOutParameter(3, OracleTypes.CURSOR);
			
			cs.execute();
			
			rs = (ResultSet)cs.getObject(3);
			
			List<User_Model> listOfUsers = new ArrayList<User_Model>();
			
			while (rs.next()) {
				listOfUsers.add(new User_Model(rs.getLong("userid"), rs.getString("username"), rs.getString("pw"), rs.getBoolean("super"), rs.getBoolean("activated")));
			}
			
			return log.traceExit(Optional.of(listOfUsers));
		} catch (SQLException e) {
			log.catching(e);
			log.error("SQL exception occurred", e);
		}
		log.traceExit(Optional.empty());
		return Optional.empty();
	}

	public Optional<User_Model> getUserByID(long userID) {
		log.traceEntry();

		Connection con = null;
		
		try {
			con = ConnectionUtil.getConnection();
		} catch (Exception e) {
			log.catching(e);
			log.traceExit();
		}
		
		if (con == null) {
			log.traceExit(Optional.empty());
			return Optional.empty();
		}
		
		try {
			
			ResultSet rs;
			
			CallableStatement cs = con.prepareCall("{call get_user_by_id(?,?)}");
			cs.setLong(1, User_Model.getCurrent().getUserID());
			cs.registerOutParameter(2, OracleTypes.CURSOR);
			
			cs.execute();
			
			rs = (ResultSet)cs.getObject(3);
			
			User_Model user = new User_Model();
			
			while (rs.next()) {
				user = new User_Model(rs.getLong("userid"), rs.getString("username"), rs.getString("pw"), rs.getBoolean("super"), rs.getBoolean("activated"));
			}
			
			log.traceExit(Optional.of(user));
			return Optional.of(user);
		} catch (SQLException e) {
			log.catching(e);
			log.error("SQL exception occurred", e);
		}		
		
		log.traceExit(Optional.empty());
		return Optional.empty();
	}

	public void createUser(String username, String password) {
		//TODO
		log.traceEntry();

		Connection con = null;
		
		try {
			con = ConnectionUtil.getConnection();
		} catch (Exception e) {
			log.catching(e);
			log.traceExit();
			return;
		}
		
		if (con == null) {
			log.traceExit();
			return;
		}
		
		try {
			
			CallableStatement cs = con.prepareCall("{call insert_user(?,?)}");
			cs.setString(1, username);
			cs.setString(2, password);
			
			cs.execute();
			
		} catch (SQLException e) {
			log.catching(e);
			log.error("SQL exception occurred", e);
		}		
		
		log.traceExit();
		return;
	}


}
