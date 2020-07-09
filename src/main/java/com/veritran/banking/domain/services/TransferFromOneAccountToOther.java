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
public class TransferFromOneAccountToOther {
	
	@Autowired
	private Clients clientRepository;
	
	@Autowired
	private Accounts accountsRepository;
	
	
	@Transactional
	public void transfer(String clientNameSource, String clientNameTarget,int amount) {
		Client clientSource = clientRepository.findByName(clientNameSource);
		Assert.notNull(clientSource, "Client not exists in our bank");
		Client clientTarget = clientRepository.findByName(clientNameTarget);
		Assert.notNull(clientTarget, "Client not exists in our bank");
		Assert.isTrue(amount>0,"amount must be possitive");
		Account accountSource = accountsRepository.findById(clientSource.getAccountId()).get();
		Account accountTarget = accountsRepository.findById(clientTarget.getAccountId()).get();
		accountSource.withdraw(amount);
		accountSource.addStatement(Statement.create("transfer to "+clientNameTarget, amount,accountSource.getBalance().getValue()));
		accountTarget.deposit(amount);
		accountsRepository.save(accountSource);
		accountsRepository.save(accountTarget);
	}

}
