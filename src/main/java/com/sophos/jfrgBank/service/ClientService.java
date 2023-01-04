package com.sophos.jfrgBank.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.sophos.jfrgBank.entity.Client;

public interface ClientService {
	
	boolean calculateAge (LocalDate birthDay, LocalDate currentDate);
	public Client createClient (Client client);
	public List <Client> getAllClients ();
	public Optional<Client> getClientById(int id);
	public Client updateClient (Client client);
	public boolean checkAccounts (int clientId);
	public boolean checkAccountStatus (int clientId);
	public boolean deleteClientById (int id);

	

}
