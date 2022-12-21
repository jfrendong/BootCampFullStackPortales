package com.sophos.jfrgBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;


import com.sophos.jfrgBank.entity.Client;


//@Repository it´s not required because JpasRepository already implements a class that have this annotation
public interface ClientRepository extends JpaRepository <Client,Integer> {
	boolean existsByIdNumber(int idNumber);
}
