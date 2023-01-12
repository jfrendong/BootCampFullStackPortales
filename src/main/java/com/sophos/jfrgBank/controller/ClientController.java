package com.sophos.jfrgBank.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sophos.jfrgBank.entity.Client;
import com.sophos.jfrgBank.service.ClientService;
import com.sophos.jfrgBank.repository.ClientRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/clients")
public class ClientController {
	
	@Autowired
	ClientService clientService;
	
	@Autowired
	ClientRepository clientRepository;
	
	//handler method to handle clients list and return mode and view
	@GetMapping
	public ResponseEntity<List<Client>> getClients () {
		return new ResponseEntity<> (clientService.getAllClients(), HttpStatus.OK);		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Client> getClientById(@PathVariable("id") int id){
        return clientService.getClientById(id)
                .map(client -> new ResponseEntity<>(client, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
//		
	@PostMapping
	public ResponseEntity<Client> createClient (@RequestBody Client client) {
		if ((clientRepository.existsByIdNumber(client.getIdNumber()))
				||clientService.calculateAge(client.getBirthDay(), LocalDate.now())) {
			return new ResponseEntity <> (HttpStatus.BAD_REQUEST);
		} else {client.setCreationDate(LocalDate.now());
				client.setCreationUser("Admin");
				client.setAccounts(client.getAccounts());
			return new ResponseEntity<>(clientService.createClient(client), HttpStatus.CREATED);
		}
	}
//		
	@PutMapping("/{id}")
	public ResponseEntity<Client> updateClient (@RequestBody Client client) {
		if (clientService.calculateAge(client.getBirthDay(), LocalDate.now())) {
			return new ResponseEntity <> (HttpStatus.BAD_REQUEST);
		} else {client.setModDate(LocalDate.now());
				client.setModUser("Admin");
			return new ResponseEntity<>(clientService.updateClient(client), HttpStatus.OK);
		}
	}
//	
		@DeleteMapping("/{id}")
	    public ResponseEntity deleteClientById(@PathVariable("id") int id){
	        if (clientService.checkAccounts(id) || clientService.checkAccountStatus(id)) {
	        	if (clientService.deleteClientById(id)){
		            return new ResponseEntity<>(HttpStatus.OK);
		        }else {
		            return new ResponseEntity(HttpStatus.NOT_FOUND);
		        }	
	        } else {
	        	return new ResponseEntity(HttpStatus.FORBIDDEN);
	        }
			
	    }
	
}
