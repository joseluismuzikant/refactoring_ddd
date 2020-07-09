package com.veritran.banking;

import com.veritran.banking.model.Client;

public class BankingTestUtils {
	public static Client getClient(String id, int balance) {
		return new Client(id, balance);
	}
}
