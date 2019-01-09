package com.project0.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Bank_Account_Model {

	private static final Logger log = LogManager.getLogger(Bank_Account_Model.class);
	
	private long bankAccountID, owner;
	private Double balance;
	private Boolean activated;
	
	public Bank_Account_Model() {
		super();
		log.traceEntry();
		// TODO Auto-generated constructor stub
		log.traceExit();
	}

	

	public Bank_Account_Model(long bankAccountID, long owner, double balance, Boolean activated) {
		super();
		log.traceEntry();
		this.bankAccountID = bankAccountID;
		this.owner = owner;
		this.balance = balance;
		this.activated = activated;
		log.traceExit();
	}



	public long getBankAccountID() {
		return bankAccountID;
	}

	public void setBankAccountID(long bankAccountID) {
		this.bankAccountID = bankAccountID;
	}

	public long getOwner() {
		return owner;
	}

	public void setOwner(long owner) {
		this.owner = owner;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
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
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		result = prime * result + (int) (bankAccountID ^ (bankAccountID >>> 32));
		result = prime * result + (int) (owner ^ (owner >>> 32));
		log.traceExit(result);
		return result;
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
		Bank_Account_Model other = (Bank_Account_Model) obj;
		if (activated == null) {
			if (other.activated != null)
				return log.traceExit(false);
		} else if (!activated.equals(other.activated))
			return log.traceExit(false);
		if (balance == null) {
			if (other.balance != null)
				return log.traceExit(false);
		} else if (!balance.equals(other.balance))
			return log.traceExit(false);
		if (bankAccountID != other.bankAccountID)
			return log.traceExit(false);
		if (owner != other.owner)
			return log.traceExit(false);
		return log.traceExit(true);
	}

	@Override
	public String toString() {
		log.traceEntry();
		return log.traceExit("Bank_Account_Model [bankAccountID=" + bankAccountID + ", owner=" + owner + ", balance=" + balance
				+ ", activated=" + activated + "]");
	}
	
	

}
