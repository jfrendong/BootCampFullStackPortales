package com.sophos.jfrgBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sophos.jfrgBank.entity.Accounts;
import com.sophos.jfrgBank.entity.Client;
import com.sophos.jfrgBank.entity.Transactions;

@Repository
public interface jfrgBankRepository extends JpaRepository <Client,Integer> {

}
