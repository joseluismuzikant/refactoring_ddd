package com.veritran.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veritran.banking.model.Client;
import com.veritran.banking.service.SimpleBanking;

import io.swagger.annotations.Api;

/**
 * The type SimpleBanking controller.
 */
@Api
@RestController()
public class SimpleBankingController {

	@Autowired
	private SimpleBanking simpleBanking;

	/**
	 * Create a client.
	 */
	@PostMapping("/client/{id}/{balance}")
	public Client client(@PathVariable(value = "id") String id, @PathVariable(value = "balance") int balance) {
		return simpleBanking.create(id, balance);
	}

	/**
	 * Do a deposit.
	 */
	@PostMapping("/deposit/{clientId}/{amount}")
	public int deposit(@PathVariable(value = "clientId") String clientId, @PathVariable(value = "amount") int amount) {
		return simpleBanking.deposit(clientId, amount);
	}

	/**
	 * Do a withdraw.
	 */
	@PostMapping("/withdraw/{clientId}/{amount}")
	public int withdraw(@PathVariable(value = "clientId") String clientId, @PathVariable(value = "amount") int amount) {
		return simpleBanking.withdraw(clientId, amount);
	}

	/**
	 * Do a transfer.
	 */
	@PostMapping("/transfer/{clientFromId}/{clientToId}/{amount}")
	public void transfer(@PathVariable(value = "clientFromId") String clientFromId,
			@PathVariable(value = "clientToId") String clientToId, @PathVariable(value = "amount") int amount) {
		simpleBanking.transfer(clientFromId, clientToId, amount);
	}
}
