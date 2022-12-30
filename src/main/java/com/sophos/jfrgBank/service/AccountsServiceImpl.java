package com.sophos.jfrgBank.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sophos.jfrgBank.entity.Accounts;
import com.sophos.jfrgBank.repository.AccountsRepository;

@Service
public class AccountsServiceImpl implements AccountsService {

	@Autowired
	AccountsRepository accountsRepository;

	@Override
	public Accounts createAccount(Accounts account) {
		return accountsRepository.save(account);
	}

	@Override
	public List<Accounts> getAllAccountsByClient(int clientId) {
		return accountsRepository.findByClientId(clientId);
	}

	@Override
	public Accounts updateAccount(Accounts account, int clientId) {
		boolean resourceFound = false;
		for (Accounts currentAccount:getAllAccountsByClient(clientId)) {
			if (currentAccount.getIdA()==account.getIdA()) {
				resourceFound = true;
				currentAccount.setStatus(account.getStatus());					
				}
			}
		if (!resourceFound) getAllAccountsByClient(clientId).add(account); 
		return accountsRepository.save(account);
	}

	@Override
	public String accNum(String accType) {
		String num = "";
		char [] chars = "0123456789".toCharArray();
		Random random = new Random();
		StringBuffer buffer = new StringBuffer();
		
		for (int i=0;i<8;i++){
		   buffer.append(chars[random.nextInt(chars.length)]);
		}
			
		if (accType.equals("Ahorros")) {
			num = "46" + buffer.toString();
		} else if (accType.equals("Corriente")) {
			num = "23" + buffer.toString();
		} else {
			num = "";
		}
		
		return num;
	}

	@Override
	public int accBalance(String accType, int accB) {
		int balance = 0;
		
		if (accType.equals("Ahorros")) {
			balance = accB;
		} else if (accType.equals("Corriente")) {
			balance = accB + 3000000;
		} else {
			balance = 0;
		}
		
		return balance;
	}

	@Override
	public String accStatus(String accType) {
		String sta = "";
		
		if (accType.equals("Ahorros")) {
			sta = "Activa";
		} else if (accType.equals("Corriente")) {
			sta = "Inactiva";
		} else {
			sta = "";
		}
		
		return sta;
	}

}
