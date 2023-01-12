package com.sophos.jfrgBank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sophos.jfrgBank.entity.Accounts;
import com.sophos.jfrgBank.entity.Transactions;
import com.sophos.jfrgBank.repository.TransactionsRepository;

@Service
public class TransactionsServiceImpl implements TransactionsService {

	@Autowired
	TransactionsRepository transactionsRepository;
	
	@Autowired
	AccountsService accountsService;

	@Override
	public List<Transactions> getAllTransactionsByAccount(int accountId) {
		return transactionsRepository.findByAccountIdA(accountId);
	}

	@Override
	public Transactions createTransaction(Transactions transaction) {
		return transactionsRepository.save(transaction);
	}
	
	@Override
	public String movType(String transactionType) {
		String movement = "";
		
		if (transactionType.equals("Consignación")) {
			movement = "Crédito";
		}
		if (transactionType.equals("Retiro") || transactionType.equals("Transferencia")) {
			movement = "Débito";
		}
		return movement;
	}

	@Override
	public double balance(String accountStatus, String transactionType, int value, int clientId, int accountId) {
			double balance = 0;
			
			for (Accounts currentAccount:accountsService.getAllAccountsByClient(clientId)) {
				if (currentAccount.getIdA()==accountId) {
					
					if ((accountStatus.equals("Activa") || accountStatus.equals("Inactiva")) 
							&& transactionType.equals("Consignación")) {
						if (currentAccount.getGmf()) {
							balance = currentAccount.getAccB()+value;
						}else {
							balance = currentAccount.getAccB()+(value*0.996);
						}
					}
					
					if (accountStatus.equals("Activa") 
							&& (transactionType.equals("Retiro") || transactionType.equals("Transferencia"))) {
						if (currentAccount.getGmf()) {
							balance = currentAccount.getAccB()-value;
						}else {
							balance = currentAccount.getAccB()-(value*1.004);
						}
					} else {
						balance = currentAccount.getAccB();
					}
				}
			}
			return balance;
	}

	@Override
	public double avBalance(String accountStatus, String transactionType, int value, int clientId, int accountId) {
		double balance = 0;
		
		for (Accounts currentAccount:accountsService.getAllAccountsByClient(clientId)) {
			if (currentAccount.getIdA()==accountId) {
				
				if ((accountStatus.equals("Activa") || accountStatus.equals("Inactiva"))
						&& transactionType.equals("Consignación")) {
					if (currentAccount.getGmf()) {
						balance = currentAccount.getAccAvailableB()+value;
					}else {
						balance = currentAccount.getAccAvailableB()+(value*0.996);
					}
				}
				
				if (accountStatus.equals("Activa")
						&& (transactionType.equals("Retiro") || transactionType.equals("Transferencia"))) {
					if (currentAccount.getGmf()) {
						balance = currentAccount.getAccAvailableB()-value;
					}else {
						balance = currentAccount.getAccAvailableB()-(value*1.004);
					}
				} else {
					balance = currentAccount.getAccAvailableB();
				}
			}
		}
		return balance;
	}

}
