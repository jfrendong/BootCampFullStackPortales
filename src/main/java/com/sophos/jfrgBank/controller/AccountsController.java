package com.sophos.jfrgBank.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sophos.jfrgBank.entity.Accounts;
import com.sophos.jfrgBank.repository.AccountsRepository;
import com.sophos.jfrgBank.repository.ClientRepository;
import com.sophos.jfrgBank.service.AccountsService;
import com.sophos.jfrgBank.service.ClientService;
//import com.sophos.jfrgBank.service.ClientService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/clients/{id}/accounts")

public class AccountsController {
	
	@Autowired
	AccountsService accountsService;
	
	@Autowired
	ClientService clientService;
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	AccountsRepository accountsRepository;
	
	@GetMapping
	public ResponseEntity<List<Accounts>> getAllAccountsByClient(@PathVariable(value = "id") int clientId){
		if (!clientRepository.existsById(clientId)) {
			return new ResponseEntity <> (HttpStatus.BAD_REQUEST);
		    }

		    return new ResponseEntity<>(accountsRepository.findByClientId(clientId), HttpStatus.OK);
	}
	
	@GetMapping("/{idA}")
	public ResponseEntity<Accounts> getAccountByIdA (@PathVariable(value = "idA") int accId) {
		return accountsService.getAccountByIdA(accId)
                .map(accounts -> new ResponseEntity<>(accounts, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
	
	@PostMapping
	public ResponseEntity<Accounts> createAccount (@PathVariable(value = "id") int clientId,
			@RequestBody Accounts account) {
		
		if (clientRepository.existsById(clientId) && account.getAccB()>=0) {
			account.setAccNumber(accountsService.accNum(account.getAccType()));
			if(accountsService.gmfCheck(clientId)) {
				account.setGmf(false);
			}
			account.setAccAvailableB(accountsService.accBalance(account.getAccType(), account.getGmf() ,account.getAccB()));
			account.setStatus(accountsService.accStatus(account.getAccType()));
			account.setCreationDate(LocalDate.now());
			account.setCreationUser("Admin");
			account.setClient(clientRepository.findClientById(clientId));
						
			return new ResponseEntity<>(accountsService.createAccount(account), HttpStatus.CREATED);
		} else {
			return new ResponseEntity <> (HttpStatus.BAD_REQUEST);
		}
	  }
	
	@PutMapping("/{idA}")
	public ResponseEntity<Accounts> updateAccount(@PathVariable(value = "id") int clientId,
			@RequestBody Accounts account) {
			account.setModDate(LocalDate.now());
			account.setModUser("Admin");
			return new ResponseEntity<>(accountsService.updateAccount(account, clientId), HttpStatus.OK);
	}
			
}



