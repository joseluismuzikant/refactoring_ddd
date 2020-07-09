package com.veritran.banking.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.veritran.banking.domain.model.Account;
import com.veritran.banking.domain.model.Client;
import com.veritran.banking.domain.model.repository.Accounts;
import com.veritran.banking.domain.model.repository.Clients;

@Component
public class GetBalanceFromAccount {
	
	@Autowired
	private Clients clientRepository;
	
	@Autowired
	private Accounts accountsRepository;

	public int getBalanceFrom(String clientName) {
		Client client = clientRepository.findByName(clientName);
		Assert.notNull(client, "Client not exists in our bank");
		Account account = accountsRepository.findById(client.getAccountId()).get();
		return account.getBalance().getValue();
	}

}
