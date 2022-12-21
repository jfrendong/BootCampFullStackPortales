package com.sophos.jfrgBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.sophos.jfrgBank.entity.Accounts;

//@Repository it´s not required because JpasRepository already implements a class that have this annotation
public interface AccountsRepository extends JpaRepository <Accounts,Integer> {

}
