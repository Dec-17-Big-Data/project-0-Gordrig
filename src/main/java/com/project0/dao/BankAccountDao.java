package com.project0.dao;

import java.util.List;
import java.util.Optional;

import com.project0.models.Bank_Account_Model;

public interface BankAccountDao {
	Optional<List<Bank_Account_Model>> getAllAccounts();
	Optional<List<Bank_Account_Model>> getAccountsOfUser(long userID);
}
