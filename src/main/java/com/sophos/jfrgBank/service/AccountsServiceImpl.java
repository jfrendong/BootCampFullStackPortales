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
				if (account.getStatus().equals("Cancelada")&&(currentAccount.getAccB()<=1)
						&&(currentAccount.getAccAvailableB()==3000000)) {
					currentAccount.setStatus(account.getStatus());
				} else {currentAccount.setStatus(account.getStatus());}
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
	public double accBalance(String accType, boolean gmf, double accB) {
		double balance = 0;
		
		if (gmf) {
			if (accType.equals("Ahorros")) {balance = accB;} 
			if (accType.equals("Corriente")) {balance = accB + 3000000;}			
		} else {
			if (accType.equals("Ahorros")) {balance = accB*0.996;} 
			if (accType.equals("Corriente")) {balance = (accB + 3000000)*0.996;}
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

	@Override
	public void updateAccBalance(int clientId, int accId, double balance, double avBalance) {
		for (Accounts currentAccount:getAllAccountsByClient(clientId)) {
			if (currentAccount.getIdA()==accId) {
				currentAccount.setAccBalance(balance);
				currentAccount.setAccAvailableB(avBalance);
				}
			}
	}

	@Override
	public void updateDesAccBalance(int clientId, int accId, String transactionType, String accDes, int value) {
		for (Accounts currentAccount:getAllAccountsByClient(clientId)) {
			if (accountsRepository.existsByaccNumber(accDes)) {
				if (transactionType.equals("Transferencia") && currentAccount.getAccNumber().equals(accDes)) {
					if (currentAccount.getGmf()) {
						currentAccount.setAccBalance((currentAccount.getAccB())+value);
						currentAccount.setAccAvailableB((currentAccount.getAccAvailableB())+value);
					}else {
						currentAccount.setAccBalance((currentAccount.getAccB())+(value*0.996));
						currentAccount.setAccAvailableB((currentAccount.getAccAvailableB())+(value*0.996));
					}
				}
			}	
		}
	}

	@Override
	public String Status(int clientId, int accId) {
		String accountStatus="";
		for (Accounts currentAccount:getAllAccountsByClient(clientId)) {
			if (currentAccount.getIdA()==accId) {
				accountStatus=currentAccount.getStatus();
				}
			}
		return accountStatus;
	}

	@Override
	public boolean balaanceCheck(int clientId, int accId, String transactionType, int value) {
		boolean resourceCheck = false;
		for (Accounts currentAccount:getAllAccountsByClient(clientId)) {
			if (currentAccount.getIdA()==accId) {
				if ((transactionType.equals("Retiro") || transactionType.equals("Transferencia"))
						&& currentAccount.getAccAvailableB()>=value) {
					resourceCheck = true;
				} else {resourceCheck = true;}
			}
		}
		return resourceCheck;
	}

	@Override
	public boolean gmfCheck(int clientId) {
		boolean resourceCheck = false;
		for (Accounts currentAccount:getAllAccountsByClient(clientId)) {
			if (currentAccount.getGmf()) {
				resourceCheck=true;
				}
			}		
		return resourceCheck;
	}
	
	
	
}

	


