package com.veritran.banking;

import com.veritran.banking.domain.model.Account;
import com.veritran.banking.domain.model.Client;

public class BankingTestUtils {
	public static Client getClient(String name) {
		Client client =  new Client(name);
		return client ;
	}
	public static Account getAccount(Client client, int value) {
		return 	client.createAccount(value); 
	}
	
	public static Account getAccount(Client client) {
		return 	client.createAccount(); 
	}
}
