package com.sophos.jfrgBank.service;

import java.util.List;

import com.sophos.jfrgBank.entity.Transactions;

public interface TransactionsService {
	
	public String movType (String transactionType);
	public double balance (String accountStatus, String transactionType, int value, int clientId,int accountId);
	public double avBalance (String accountStatus, String transactionType, int value, int clientId,int accountId);
	public List <Transactions> getAllTransactionsByAccount (int accountId);
	public Transactions createTransaction (Transactions transaction);
	
}
