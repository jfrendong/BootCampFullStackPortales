package com.sophos.jfrgBank.service;

import java.util.List;


import com.sophos.jfrgBank.entity.Accounts;

public interface AccountsService {
	
	public String accNum (String accType);
	public String accStatus (String accType);
	public int accBalance (String accType, int accB);
	public Accounts createAccount (Accounts accounts);
	public List <Accounts> getAllAccountsByClient (int clientId);
	public Accounts updateAccount (Accounts accounts, int clientId);
}
