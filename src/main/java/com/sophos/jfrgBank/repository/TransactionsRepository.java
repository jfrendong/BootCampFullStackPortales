package com.sophos.jfrgBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import com.sophos.jfrgBank.entity.Transactions;

//@Repository it´s not required because JpasRepository already implements a class that have this annotation
public interface TransactionsRepository extends JpaRepository <Transactions,Integer> {

}
