package com.project0.services;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.project0.dao.TransactionDao;
import com.project0.models.Transaction_Model;
import com.project0.oracle.TransactionOracle;

public class TransactionService {
	
	private static TransactionDao dao = null;
	private static TransactionService transactionService = null;
	private static final Logger log = LogManager.getLogger(BankAccountService.class);

	private TransactionService() {
		// TODO Auto-generated constructor stub
		dao = TransactionOracle.getDao();
	}
	
	public static TransactionService getService() {
		log.traceEntry();
		if (transactionService == null) {
			transactionService = new TransactionService();
		}
		
		return log.traceExit(transactionService);
	}
	
	public Optional<List<Transaction_Model>> getAllTransactions() {
		log.traceEntry();
		Optional<List<Transaction_Model>> op = dao.getAllTransactions();
		log.traceExit(op);
		return op;
	}
	
	
	public void createTransaction(Transaction_Model.Action action, double amount, long account1) {
		log.traceEntry();
		dao.createTransaction(action, account1, amount);
		log.traceExit();
		return;
	}
	
	public void createTransaction(Transaction_Model.Action action, double amount, long account1, long account2) {
		log.traceEntry();
		dao.createTransaction(action, account1, account2, amount);
		log.traceExit();
		return;
	}

}
