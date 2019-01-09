package com.project0.dao;

import java.util.List;
import java.util.Optional;

import com.project0.models.Transaction_Model;

public interface TransactionDao {
	Optional<List<Transaction_Model>> getAllTransactions();
	Optional<List<Transaction_Model>> getAllTransactionsOfUser(long userID);
	

	void createTransaction(Transaction_Model.Action type, long account1, double amount);
	void createTransaction(Transaction_Model.Action type, long account1, long account2, double amount);
}
