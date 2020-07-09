package com.veritran.banking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.veritran.banking.domain.model.Account;
import com.veritran.banking.domain.model.Balance;
import com.veritran.banking.domain.model.Client;
import com.veritran.banking.domain.model.exception.BalanceNegativeException;
import com.veritran.banking.domain.services.GetBalanceFromAccount;
import com.veritran.banking.domain.services.TransferFromOneAccountToOther;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TransferFromOneAccountToOtherTest extends CreateClientTest {
	
	private String clientTargetName;
	private Client clientTarget;
	private Account accountTarget;

	@InjectMocks
	private TransferFromOneAccountToOther transferFromOneAccountToOther;
	
	@InjectMocks
	private GetBalanceFromAccount getBalanceFromAccount;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		clientTargetName = "pedro";
		clientTarget = BankingTestUtils.getClient(clientTargetName);
		accountTarget = BankingTestUtils.getAccount(clientTarget, 50);
		when(clientRepository.findByName(clientTargetName)).thenReturn(clientTarget);
		when(accountRepository.findById(clientTarget.getAccountId())).thenReturn(Optional.of(accountTarget));
	}

	@Test
	public void createClientTarget() {
		assertNotNull(clientTarget);
		assertEquals(clientTarget.getName(), clientTargetName);
		Account clientAccountTarget = accountRepository.findById(clientTarget.getAccountId()).get();
		assertNotNull(clientAccountTarget);
		assertTrue(clientAccountTarget.getBalance().equals(new Balance(50)));
	}
	
	@Test
	public void transferMoney() {
		transferFromOneAccountToOther.transfer(clientName,clientTargetName, 10);
		assertEquals(getBalanceFromAccount.getBalanceFrom(clientName), 90);
		assertEquals(getBalanceFromAccount.getBalanceFrom(clientTargetName), 60);
	}

	@Test(expected = BalanceNegativeException.class)
	public void transferMoneyWithoutEnoughFunds() {
		transferFromOneAccountToOther.transfer(clientName,clientTargetName, 110);
	}

	@Test(expected = IllegalArgumentException.class)
	public void transferNegativeAmount() {
		transferFromOneAccountToOther.transfer(clientName,clientTargetName, -10);
	}

	@Test(expected = IllegalArgumentException.class)
	public void transferCeroAmount() {
		transferFromOneAccountToOther.transfer(clientName,clientTargetName, 0);
	}
}
