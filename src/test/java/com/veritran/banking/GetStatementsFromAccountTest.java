package com.veritran.banking;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.veritran.banking.domain.model.Account;
import com.veritran.banking.domain.model.Client;
import com.veritran.banking.domain.model.repository.Accounts;
import com.veritran.banking.domain.model.repository.Clients;
import com.veritran.banking.domain.services.DepositIntoAccount;
import com.veritran.banking.domain.services.GetBalanceFromAccount;
import com.veritran.banking.domain.services.GetStatementFromAccount;
import com.veritran.banking.domain.services.TransferFromOneAccountToOther;
import com.veritran.banking.domain.services.WithdrawFromAccount;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class GetStatementsFromAccountTest {
	
	private String clientName;
	private Client client;
	private Account account;
	private String clientTargetName;
	private Client clientTarget;
	private Account accountTarget;

	@Mock
	private Clients clientRepository;
	@Mock
	private Accounts accountRepository;
	
	@InjectMocks
	private DepositIntoAccount depositIntoAccount;
	
	@InjectMocks
	private WithdrawFromAccount withdrawFromAccount;
	
	@InjectMocks
	private TransferFromOneAccountToOther transferFromOneAccountToOther;
	
	@InjectMocks
	private GetStatementFromAccount getStatementFromAccount;
	
	@InjectMocks
	private GetBalanceFromAccount getBalanceFromAccount;
	
	@Before
	public void setUp() throws Exception {
		clientName = "francisco";
		client = BankingTestUtils.getClient(clientName);
		account = BankingTestUtils.getAccount(client);
		when(clientRepository.findByName(clientName)).thenReturn(client);
		when(accountRepository.findById(client.getAccountId())).thenReturn(Optional.of(account));
		clientTargetName = "pedro";
		clientTarget = BankingTestUtils.getClient(clientTargetName);
		accountTarget = BankingTestUtils.getAccount(clientTarget, 50);
		when(clientRepository.findByName(clientTargetName)).thenReturn(clientTarget);
		when(accountRepository.findById(clientTarget.getAccountId())).thenReturn(Optional.of(accountTarget));
	}

	
	//1. deposit, 100 USD, balance: 100 USD
	//2. withdrawal, 20 USD, balance: 80 USD
	//3. transfer to pedro, 50 USD, balance: 30 USD
	//4. deposit, 10 USD, balance: 40 USD
	@Test
	public void depositMoneyIntoAnAccountAndGetStatement() {
		depositIntoAccount.deposit(clientName, 100);
		withdrawFromAccount.withdraw(clientName, 20);
		transferFromOneAccountToOther.transfer(clientName,clientTargetName, 50);
		depositIntoAccount.deposit(clientName, 10);
		assertEquals(getStatementFromAccount.getStatements(clientName).get(0).getText(), "deposit, 100 USD, balance: 100 USD");											
		assertEquals(getStatementFromAccount.getStatements(clientName).get(1).getText(), "withdrawal, 20 USD, balance: 80 USD");
		assertEquals(getStatementFromAccount.getStatements(clientName).get(2).getText(), "transfer to pedro, 50 USD, balance: 30 USD");
		assertEquals(getStatementFromAccount.getStatements(clientName).get(3).getText(), "deposit, 10 USD, balance: 40 USD");
	}
}
