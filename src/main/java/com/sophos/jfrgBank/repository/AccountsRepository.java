package com.sophos.jfrgBank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.sophos.jfrgBank.entity.Accounts;

//@Repository it´s not required because JpasRepository already implements a class that have this annotation
public interface AccountsRepository extends JpaRepository <Accounts,Integer> {
	List<Accounts> findByClientId(int clientId);
	boolean existsByIdA (int accountId);
	boolean existsByaccNumber (String accNumber);
	public Optional<Accounts> findByIdA (int idA);
	Accounts findAccountByIdA (int idA);
	
}
