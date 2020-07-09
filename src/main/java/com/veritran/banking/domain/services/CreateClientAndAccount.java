package com.veritran.banking.domain.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.veritran.banking.domain.model.Account;
import com.veritran.banking.domain.model.Client;
import com.veritran.banking.domain.model.repository.Accounts;
import com.veritran.banking.domain.model.repository.Clients;

@Component
public class CreateClientAndAccount {
	
	@Autowired
	private Clients clientRepository;
	
	@Autowired
	private Accounts accountsRepository;
	
	@Transactional
	public Client create(String clientName, int amount) {
		Client client =  new Client(clientName);
		Account account = client.createAccount(amount); 
		clientRepository.save(client);
		accountsRepository.save(account);
		return client;
	}
}
