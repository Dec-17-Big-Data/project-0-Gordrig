package com.project0.oracle;

import com.project0.dao.BankAccountDao;
import com.project0.models.Bank_Account_Model;
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

public class BankAccountOracle implements BankAccountDao{

	private static BankAccountOracle instance = null;
	private static final Logger log = LogManager.getLogger(BankAccountOracle.class);
	
	private BankAccountOracle() {
	}
	
	public static BankAccountDao getDao() {
		log.traceEntry();
		if (instance == null) {
			instance = new BankAccountOracle();
		}
		return log.traceExit(instance);
	}

	public Optional<List<Bank_Account_Model>> getAllAccounts() {
		// TODO Auto-generated method stub
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
			
			CallableStatement cs = con.prepareCall("{call get_all_accounts(?,?, ?)}");
			cs.setString(1, User_Model.getCurrent().getUserName());
			cs.setString(2, User_Model.getCurrent().getPassword());
			cs.registerOutParameter(3, OracleTypes.CURSOR);
			
			cs.execute();
			
			rs = (ResultSet)cs.getObject(3);
			
			List<Bank_Account_Model> listOfAccounts = new ArrayList<Bank_Account_Model>();
			
			while (rs.next()) {
				listOfAccounts.add(new Bank_Account_Model(rs.getLong("accountid"), rs.getLong("owner"), rs.getLong("balance"), (rs.getInt("activated")>0)));
			}
			
			return log.traceExit(Optional.of(listOfAccounts));
		} catch (SQLException e) {
			log.catching(e);
			log.error("SQL exception occurred", e);
		}
		
		log.traceExit(Optional.empty());
		return Optional.empty();
	}

	
	public Optional<List<Bank_Account_Model>> getAccountsOfUser(long userID) {
		// TODO Auto-generated method stub
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
			
			CallableStatement cs = con.prepareCall("{call get_account_by_userid(?,?,?,?)}");
			cs.setString(1, User_Model.getCurrent().getUserName());
			cs.setString(2, User_Model.getCurrent().getPassword());
			cs.setLong(3, userID);
			cs.registerOutParameter(4, OracleTypes.CURSOR);
			
			cs.execute();
			
			rs = (ResultSet)cs.getObject(4);
			
			List<Bank_Account_Model> listOfAccounts = new ArrayList<Bank_Account_Model>();
			
			while (rs.next()) {
				listOfAccounts.add(new Bank_Account_Model(rs.getLong("accountid"), rs.getLong("owner"), rs.getLong("balance"), (rs.getInt("activated")>0)));
			}
			
			return log.traceExit(Optional.of(listOfAccounts));
		} catch (SQLException e) {
			log.catching(e);
			log.error("SQL exception occurred", e);
		}
		
		log.traceExit(Optional.empty());
		return Optional.empty();
	}
	
	
	public void createAccount() {
		log.traceEntry();
		
		Connection con = null;
		
		try {
			con = ConnectionUtil.getConnection();
		} catch (Exception e) {
			log.catching(e);
			log.traceExit();
		}
		
		if (con == null) {
			log.traceExit();
			return;
		}
		
		try {
			CallableStatement cs = con.prepareCall("{call insert_account(?)}");
			cs.setLong(1, User_Model.getCurrent().getUserID());
			
			cs.execute();
			
			log.traceExit();
			return;
		} catch (SQLException e) {
			log.catching(e);
			log.error("SQL exception occurred", e);
		}
		
		log.traceExit();
		return;
	}
}
