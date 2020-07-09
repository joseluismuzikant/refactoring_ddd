package com.veritran.banking.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {
	@Id
	private UUID accountId;
	
	private UUID clientId;
	
	@Embedded
	private Balance balance;
	
	@Embedded
	private List<Statement> statements;

	private Account() {
		
	}
	
	public Account(Client client) {
		this.accountId = UUID.randomUUID();
		this.balance = new Balance(0);
		this.clientId = client.getClientId();
		this.statements = new ArrayList<Statement>();
	}

	public UUID getAccountId() {
		return accountId;
	}

	public Balance getBalance() {
		return balance;
	}

	public UUID getClientId() {
		return clientId;
	}

	public void deposit(int amount) {
		this.balance = this.balance.sum(amount);
	}

	public void withdraw(int amount) {
		this.balance = this.balance.minus(amount);
	}
	
	public void addStatement(Statement statement) {
		statements.add(statement);
	}
	public List<Statement> getStatements() {
		return statements;
	}
}