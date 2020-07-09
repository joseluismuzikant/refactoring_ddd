package com.veritran.banking.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Entity
public class Client {
	@Id
	private String id;
	private int balance;

	public Client() {
		super();
	}

	public Client(String id, int balance) {
		Assert.isTrue(!StringUtils.isEmpty(id),"id must to be non empty");
		Assert.isTrue(balance>=0,"balance must to be positive");
		this.id = id;
		this.balance = balance;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getId() {
		return id;
	}
}
