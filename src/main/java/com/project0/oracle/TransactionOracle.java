package com.project0.oracle;

import com.project0.dao.TransactionDao;
import com.project0.models.Transaction_Model;
import com.project0.models.User_Model;
import com.project0.models.Transaction_Model.Action;
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

public class TransactionOracle implements TransactionDao {
	
	private static TransactionOracle instance = null;
	private static final Logger log = LogManager.getLogger(TransactionOracle.class);
	
	private TransactionOracle() {
		super();
	}
	
	public static TransactionDao getDao() {
		log.traceEntry();
		if (instance == null) {
			instance = new TransactionOracle();
		}
		return log.traceExit(instance);
	}

	public Optional<List<Transaction_Model>> getAllTransactions() {
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
			
			CallableStatement cs = con.prepareCall("{call get_all_transactions(?,?,?,?)}");
			cs.setString(1, User_Model.getCurrent().getUserName());
			cs.setString(2, User_Model.getCurrent().getPassword());
			cs.setLong(3, User_Model.getCurrent().getUserID());
			cs.registerOutParameter(4, OracleTypes.CURSOR);
			
			cs.execute();
			
			rs = (ResultSet)cs.getObject(4);
			
			List<Transaction_Model> listOfTransactions = new ArrayList<Transaction_Model>();
			
			while (rs.next()) {
				Transaction_Model.Action action = null;
				if (rs.getLong("action") == 0) {
					action = Transaction_Model.Action.DEPOSIT;
				} else if (rs.getLong("action") == 1) {
					action = Transaction_Model.Action.WITHDRAWAL;
				} else if (rs.getLong("action") == 2) {
					action = Transaction_Model.Action.TRANSFER;
				}
				listOfTransactions.add(new Transaction_Model(rs.getLong("transactionid"), rs.getLong("person"), rs.getLong("account1"), rs.getLong("account2"), rs.getDouble("amount"), rs.getTimestamp("transactiontime"), action));
			}
			
			return log.traceExit(Optional.of(listOfTransactions));
		} catch (SQLException e) {
			log.catching(e);
			log.error("SQL exception occurred", e);
		}
		
		log.traceExit(Optional.empty());
		return Optional.empty();

	}

	public Optional<List<Transaction_Model>> getAllTransactionsOfUser(long userID) {
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
			
			CallableStatement cs = con.prepareCall("{call get_transactions_by_userid(?,?,?,?)}");
			cs.setString(1, User_Model.getCurrent().getUserName());
			cs.setString(2, User_Model.getCurrent().getPassword());
			cs.setLong(3, userID);
			cs.registerOutParameter(4, OracleTypes.CURSOR);
			
			cs.execute();
			
			rs = (ResultSet)cs.getObject(4);
			
			List<Transaction_Model> listOfTransactions = new ArrayList<Transaction_Model>();
			
			while (rs.next()) {
				listOfTransactions.add(new Transaction_Model());
			}
			
			return log.traceExit(Optional.of(listOfTransactions));
		} catch (SQLException e) {
			log.catching(e);
			log.error("SQL exception occurred", e);
		}
		
		log.traceExit(Optional.empty());
		return Optional.empty();
		
	}

	public void createTransaction(Action type, long account1, double amount) {
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
			log.traceExit();
			return;
		}
		
		try {
			int typeInt = 0;
			if (type == Action.DEPOSIT) {
				typeInt = 1;
			} else if (type == Action.WITHDRAWAL) {
				typeInt = 2;
			} else if (type == Action.TRANSFER) {
				typeInt = 3;
			}
			
			CallableStatement cs = null;
			if (typeInt == 0) {
				return; //TODO
			}
			if (typeInt == 1 || typeInt == 2) {
				log.trace("preparing call");
				cs = con.prepareCall("{call insert_transactiondw(?,?,?,?)}");
				cs.setLong(1, User_Model.getCurrent().getUserID());
				cs.setLong(2, typeInt-1);
				cs.setLong(3, account1);
				cs.setDouble(4, amount);
			}
			
			log.trace("executing call");
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
	
	public void createTransaction(Action type, long account1, long account2, double amount) {
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
			log.traceExit();
			return;
		}
		
		try {
			int typeInt = 0;
			if (type == Action.DEPOSIT) {
				typeInt = 1;
			} else if (type == Action.WITHDRAWAL) {
				typeInt = 2;
			} else if (type == Action.TRANSFER) {
				typeInt = 3;
			}
			
			CallableStatement cs = null;
			if (typeInt == 0) {
				return; //TODO
			}
			if (typeInt == 1 || typeInt == 2) {
				cs = con.prepareCall("{call insert_transactiondw(?,?,?,?)}");
				cs.setLong(1, User_Model.getCurrent().getUserID());
				cs.setLong(2, typeInt-1);
				cs.setLong(3, account1);
				cs.setDouble(4, amount);
			}
			else
			{
				cs = con.prepareCall("{call insert_transactiont(?,?,?,?,?)}");
				cs.setLong(1, User_Model.getCurrent().getUserID());
				cs.setLong(2, typeInt-1);
				cs.setLong(3, account1);
				cs.setLong(4, account2);
				cs.setDouble(5, amount);
			}
			
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
