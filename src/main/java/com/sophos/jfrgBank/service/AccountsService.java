package com.sophos.jfrgBank.service;

import java.util.List;
import java.util.Optional;

import com.sophos.jfrgBank.entity.Accounts;



public interface AccountsService {
	
	public String accNum (String accType);
	public String accStatus (String accType);
	public double accBalance (String accType, boolean gmf,double accB);
	public Accounts createAccount (Accounts accounts);
	public List <Accounts> getAllAccountsByClient (int clientId);
	public Accounts updateAccount (Accounts accounts, int clientId);
	public void updateAccBalance (int clientId, int accId, double balance, double avBalance);
	public void updateDesAccBalance (int clientId, int accId, String transactionType, String accDes, int value);
	public String Status (int clientId, int accId);
	public boolean balaanceCheck (int clientId, int accId, String transactionType, int value);
	public boolean gmfCheck (int clientId);
	public Optional<Accounts> getAccountByIdA(int idA);
	
}
