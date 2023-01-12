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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sophos.jfrgBank.entity.Transactions;
import com.sophos.jfrgBank.repository.AccountsRepository;
import com.sophos.jfrgBank.repository.ClientRepository;
import com.sophos.jfrgBank.repository.TransactionsRepository;
import com.sophos.jfrgBank.service.AccountsService;
import com.sophos.jfrgBank.service.TransactionsService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/clients/{id}/accounts/{idA}/transactions")
public class TransactionsController {
	
	@Autowired
	TransactionsService transactionsService;
	
	@Autowired
	AccountsService accountsService;
	
	@Autowired
	TransactionsRepository transactionsRepository;
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	AccountsRepository accountsRepository;
	
	@GetMapping
	public ResponseEntity<List<Transactions>> getAllTransactionsByAccount(@PathVariable(value = "id") int clientId,
			@PathVariable(value = "idA") int accountId){
		if (!accountsRepository.existsByIdA(accountId)) {
			return new ResponseEntity <> (HttpStatus.BAD_REQUEST);
		    }

		    return new ResponseEntity<>(transactionsRepository.findByAccountIdA(accountId), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Transactions> createTransaction (@PathVariable(value = "id") int clientId,
			@PathVariable(value = "idA") int accountId, @RequestBody Transactions transaction) {
		
		if (accountsRepository.existsById(accountId) 
				&& accountsService.balaanceCheck(clientId, accountId, transaction.getTransactionType(),
						transaction.getValue())) {
			
			transaction.setTransactionDate(LocalDate.now());
			
			transaction.setMovementType(transactionsService.movType(transaction.getTransactionType()));
			
			transaction.setAccB(transactionsService.balance(accountsService.Status(clientId, accountId),
					transaction.getTransactionType(), transaction.getValue(), clientId, accountId));
			
			transaction.setAccAvB(transactionsService.avBalance(accountsService.Status(clientId, accountId),
					transaction.getTransactionType(), transaction.getValue(), clientId, accountId));
			
			accountsService.updateAccBalance(clientId, accountId, transaction.getAccB(), transaction.getAccAvB());
			
			accountsService.updateDesAccBalance(clientId, accountId, transaction.getTransactionType(),
					transaction.getAccDes(), transaction.getValue());
			
			transaction.setAccount(accountsRepository.findAccountByIdA(accountId));
			
			return new ResponseEntity<>(transactionsService.createTransaction(transaction), HttpStatus.CREATED);
		} else {
			return new ResponseEntity <> (HttpStatus.BAD_REQUEST);
		}
	}	
}
