package com.project0.services;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.project0.dao.BankAccountDao;
import com.project0.models.Bank_Account_Model;
import com.project0.oracle.BankAccountOracle;

public class BankAccountService {
	
	private static BankAccountDao dao = null;
	private static BankAccountService bankAccountService = null;
	private static final Logger log = LogManager.getLogger(BankAccountService.class);

	private BankAccountService() {
		// TODO Auto-generated constructor stub
		dao = BankAccountOracle.getDao();
	}
	
	public static BankAccountService getService() {
		log.traceEntry();
		if (bankAccountService == null) {
			bankAccountService = new BankAccountService();
		}
		
		return log.traceExit(bankAccountService);	
	}
	
	public Optional<List<Bank_Account_Model>> getAccounts() {
		log.traceEntry();
		Optional<List<Bank_Account_Model>> op = dao.getAllAccounts();
		log.traceExit(op);
		return op;
	}
	
	public void createAccount() {
		log.traceEntry();
		dao.createAccount();
		log.traceExit();
		return;
	}

}
