package com.veritran.banking.domain.services;

import java.util.List;

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
public class GetStatementFromAccount {

	
	@Autowired
	private Clients clientRepository;
	
	@Autowired
	private Accounts accountsRepository;
	

	public List<Statement> getStatements(String clientName) {
		Client clientSource = clientRepository.findByName(clientName);
		Assert.notNull(clientSource, "Client not exists in our bank");
		Account accountSource = accountsRepository.findById(clientSource.getAccountId()).get();
	
		return accountSource.getStatements();
	}

}
