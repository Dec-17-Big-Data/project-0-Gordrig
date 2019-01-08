package com.project0.models;

import java.sql.Timestamp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Transaction_Model {

	private static final Logger log = LogManager.getLogger(Transaction_Model.class);
	
	private long transactionID, user, account1, account2, amount;
	private Timestamp timeOfTransaction;
	public enum Action {DEPOSIT , WITHDRAWAL , TRANSFER} 
	private Action action;
	
	public Transaction_Model() {
		super();
		log.traceEntry();
		// TODO Auto-generated constructor stub
		
		log.traceExit();
	}

	

	public Transaction_Model(long transactionID, long user, long account1, long account2, long amount,
			Timestamp timeOfTransaction, Action action) {
		super();
		log.traceEntry();
		this.transactionID = transactionID;
		this.user = user;
		this.account1 = account1;
		this.account2 = account2;
		this.amount = amount;
		this.timeOfTransaction = timeOfTransaction;
		this.action = action;
		log.traceExit();
	}



	public long getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(long transactionID) {
		this.transactionID = transactionID;
	}

	public long getUser() {
		return user;
	}

	public void setUser(long user) {
		this.user = user;
	}

	public long getAccount1() {
		return account1;
	}

	public void setAccount1(long account1) {
		this.account1 = account1;
	}

	public long getAccount2() {
		return account2;
	}

	public void setAccount2(long account2) {
		this.account2 = account2;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public Timestamp getTimeOfTransaction() {
		return timeOfTransaction;
	}

	public void setTimeOfTransaction(Timestamp timeOfTransaction) {
		this.timeOfTransaction = timeOfTransaction;
	}

	public static Logger getLog() {
		return log;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	@Override
	public int hashCode() {
		log.traceEntry();
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (account1 ^ (account1 >>> 32));
		result = prime * result + (int) (account2 ^ (account2 >>> 32));
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + (int) (amount ^ (amount >>> 32));
		result = prime * result + ((timeOfTransaction == null) ? 0 : timeOfTransaction.hashCode());
		result = prime * result + (int) (transactionID ^ (transactionID >>> 32));
		result = prime * result + (int) (user ^ (user >>> 32));
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
		Transaction_Model other = (Transaction_Model) obj;
		if (account1 != other.account1)
			return log.traceExit(false);
		if (account2 != other.account2)
			return log.traceExit(false);
		if (action != other.action)
			return log.traceExit(false);
		if (amount != other.amount)
			return log.traceExit(false);
		if (timeOfTransaction == null) {
			if (other.timeOfTransaction != null)
				return log.traceExit(false);
		} else if (!timeOfTransaction.equals(other.timeOfTransaction))
			return log.traceExit(false);
		if (transactionID != other.transactionID)
			return log.traceExit(false);
		if (user != other.user)
			return log.traceExit(false);
		return log.traceExit(true);
	}

	@Override
	public String toString() {
		log.traceEntry();
		return log.traceExit("Transaction_Model [transactionID=" + transactionID + ", user=" + user + ", account1=" + account1
				+ ", account2=" + account2 + ", amount=" + amount + ", timeOfTransaction=" + timeOfTransaction
				+ ", action=" + action + "]");
	}
	
	

}
