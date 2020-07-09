package com.veritran.banking.domain.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.veritran.banking.domain.model.Account;
import com.veritran.banking.domain.model.Client;
import com.veritran.banking.domain.model.Statement;
import com.veritran.banking.domain.model.repository.Accounts;
import com.veritran.banking.domain.model.repository.Clients;

@Component
public class DepositIntoAccount {
	
	@Autowired
	private Clients clientRepository;
	
	@Autowired
	private Accounts accountsRepository;
	
	
	@Transactional
	public void deposit(String clientName, int amount) {
		Client client = clientRepository.findByName(clientName);
		Assert.notNull(client, "Client not exists in our bank");
		Account account = accountsRepository.findById(client.getAccountId()).get();
		account.deposit(amount);
		account.addStatement(Statement.create("deposit", amount,account.getBalance().getValue()));
		accountsRepository.save(account);
	}

}
