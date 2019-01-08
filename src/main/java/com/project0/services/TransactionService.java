package com.project0.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TransactionService {
	
	private static TransactionService transactionService = null;
	private static final Logger log = LogManager.getLogger(BankAccountService.class);

	private TransactionService() {
		// TODO Auto-generated constructor stub
	}
	
	public static TransactionService getService() {
		log.traceEntry();
		if (transactionService == null) {
			transactionService = new TransactionService();
		}
		
		return log.traceExit(transactionService);
	}

}
