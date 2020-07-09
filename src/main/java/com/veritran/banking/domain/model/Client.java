package com.veritran.banking.domain.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.util.Assert;

import io.micrometer.core.instrument.util.StringUtils;

@Entity
public class Client {

	@Id
	private UUID clientId;
	private String name;
	private UUID accountId;

	private Client() {
		
	}
	
	public Client(String name) {
		Assert.isTrue(StringUtils.isNotEmpty(name),"Client name cannot be empty");
		this.clientId = UUID.randomUUID();
		this.name = name;
	}
	
	public Account createAccount() {
		Account account = new Account(this);
		this.accountId = account.getAccountId();
		return account;
	}
	
	public Account createAccount(int value) {
		Account account = new Account(this);
		this.accountId = account.getAccountId();
		account.deposit(value);
		return account;
	}

	public String getName() {
		return name;
	}

	public UUID getClientId() {
		return clientId;
	}

	public UUID getAccountId() {
		return accountId;
	}
}
