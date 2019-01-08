package com.project0.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BankAccountService {
	
	private static BankAccountService bankAccountService = null;
	private static final Logger log = LogManager.getLogger(BankAccountService.class);

	private BankAccountService() {
		// TODO Auto-generated constructor stub
	}
	
	public static BankAccountService getService() {
		log.traceEntry();
		if (bankAccountService == null) {
			bankAccountService = new BankAccountService();
		}
		
		return log.traceExit(bankAccountService);	
	}

}
