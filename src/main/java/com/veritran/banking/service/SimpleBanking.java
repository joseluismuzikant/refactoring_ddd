package com.veritran.banking.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.veritran.banking.model.Client;
import com.veritran.banking.repository.ClientRepository;

@Component
public class SimpleBanking {

	@Autowired
	private ClientRepository clientRepository;

	@Transactional
	public int deposit(String clientId, int amount) {
		Assert.isTrue(amount >= 0, "amount must to be positive");
		Client client = clientRepository.getOne(clientId);
		Assert.notNull(client, "Client not exists in our bank");

		client.setBalance(client.getBalance() + amount);
		clientRepository.save(client);
		return client.getBalance();
	}

	@Transactional
	public int withdraw(String clientId, int amount) {
		Assert.isTrue(amount >= 0, "amount must to be positive");
		Client client = clientRepository.getOne(clientId);
		Assert.notNull(client, "Client not exists in our bank");
		Assert.isTrue(client.getBalance() - amount >= 0, "client must to have enough founds");
		client.setBalance(client.getBalance() - amount);
		;
		clientRepository.save(client);
		return client.getBalance();
	}

	@Transactional
	public void transfer(String clientFromId, String clientToId, int amount) {
		Assert.isTrue(amount > 0, "amount must to be positive");
		Client clientFrom = clientRepository.getOne(clientFromId);
		Assert.notNull(clientFrom, "Client not exists in our bank");
		Client clientTo = clientRepository.getOne(clientToId);
		Assert.notNull(clientTo, "Client not exists in our bank");
		Assert.isTrue(clientFrom.getBalance() - amount >= 0, "client1 must to have enough founds");

		clientFrom.setBalance(clientFrom.getBalance() - amount);
		clientTo.setBalance(clientTo.getBalance() + amount);
		clientRepository.save(clientFrom);
		clientRepository.save(clientTo);
	}

	public Client create(String id, int balance) {
		Client client = new Client(id, balance);
		return clientRepository.save(client);
	}
}
