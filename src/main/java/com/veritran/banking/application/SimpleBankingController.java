package com.veritran.banking.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veritran.banking.domain.model.Client;
import com.veritran.banking.domain.services.CreateClientAndAccount;
import com.veritran.banking.domain.services.DepositIntoAccount;
import com.veritran.banking.domain.services.TransferFromOneAccountToOther;
import com.veritran.banking.domain.services.WithdrawFromAccount;

import io.swagger.annotations.Api;

/**
 * The type SimpleBanking controller.
 */
@Api
@RestController()
public class SimpleBankingController {

	@Autowired
	private CreateClientAndAccount createClientAndAccount;
	
	@Autowired
	private DepositIntoAccount depositIntoAccount;
	
	@Autowired
	private WithdrawFromAccount withdrawFromAccount;
	
	@Autowired
	private TransferFromOneAccountToOther transferFromOneAccountToOther;


	/**
	 * Create a client.
	 */
	@PostMapping("/client/{id}/{balance}")
	public Client client(@PathVariable(value = "id") String id, @PathVariable(value = "balance") int balance) {
		 return createClientAndAccount.create(id, balance);
	}

	/**
	 * Do a deposit.
	 */
	@PostMapping("/deposit/{clientId}/{amount}")
	public void deposit(@PathVariable(value = "clientId") String clientId, @PathVariable(value = "amount") int amount) {
		 depositIntoAccount.deposit(clientId, amount);
	}

	/**
	 * Do a withdraw.
	 */
	@PostMapping("/withdraw/{clientId}/{amount}")
	public void withdraw(@PathVariable(value = "clientId") String clientId, @PathVariable(value = "amount") int amount) {
		withdrawFromAccount.withdraw(clientId, amount);
	}

	/**
	 * Do a transfer.
	 */
	@PostMapping("/transfer/{clientFromId}/{clientToId}/{amount}")
	public void transfer(@PathVariable(value = "clientFromId") String clientFromId,
			@PathVariable(value = "clientToId") String clientToId, @PathVariable(value = "amount") int amount) {
		transferFromOneAccountToOther.transfer(clientFromId, clientToId, amount);
	}
}
