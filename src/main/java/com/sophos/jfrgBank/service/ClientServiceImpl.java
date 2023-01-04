package com.sophos.jfrgBank.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sophos.jfrgBank.entity.Accounts;
import com.sophos.jfrgBank.entity.Client;
import com.sophos.jfrgBank.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	AccountsService accountsService;
	
	@Override
	public Client createClient(Client client) {
		return clientRepository.save(client);
	}
//
	@Override
	public List<Client> getAllClients() {
		return clientRepository.findAll();
	}
//	
	@Override
	public Client updateClient(Client client) {
		boolean resourceFound = false;
		for (Client currentClient:getAllClients()) {
			if (currentClient.getId()==client.getId()) {
				resourceFound = true;
				currentClient.setIdType(client.getIdType());
				currentClient.setIdNumber(client.getIdNumber());
				currentClient.setName(client.getName());
				currentClient.setLastName(client.getLastName());
				currentClient.setEmail(client.getEmail());
				currentClient.setBirthDay(client.getBirthDay());
			}
		}
		if (!resourceFound) getAllClients().add(client);
		return clientRepository.save(client);
	}
//
	@Override
	public Optional<Client> getClientById(int id) {
        return clientRepository.findById(id);
    }
//
    @Override
    public boolean deleteClientById(int id) {
        return getClientById(id).map(client -> {
            clientRepository.deleteById(id);
            return true;
        }).orElse(false);
    }
	
	@Override
	public boolean calculateAge(LocalDate birthDay, LocalDate currentDate) {
		int Age = Period.between(birthDay, currentDate).getYears();
		if (Age < 18) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public boolean checkAccountStatus(int clientId) {
		boolean resourceFound = false;
		for (Accounts currentAccount:accountsService.getAllAccountsByClient(clientId)) {
			if (currentAccount.getStatus().equals("Cancelada")) {
				resourceFound = true;
				}else {resourceFound = false;}
		}
		return resourceFound;
	}
	@Override
	public boolean checkAccounts(int clientId) {
		boolean resourceFound = false;
		if (accountsService.getAllAccountsByClient(clientId).isEmpty()) {
			resourceFound = true;
		}
		return resourceFound;
	}
	
	

	
}
