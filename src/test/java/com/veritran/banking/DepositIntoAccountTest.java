package com.veritran.banking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.veritran.banking.domain.model.Account;
import com.veritran.banking.domain.model.Balance;
import com.veritran.banking.domain.model.exception.AmountNegativeException;
import com.veritran.banking.domain.services.DepositIntoAccount;
import com.veritran.banking.domain.services.GetBalanceFromAccount;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class DepositIntoAccountTest extends CreateClientTest {
	@InjectMocks
	private DepositIntoAccount depositIntoAccount;
	
	@InjectMocks
	private GetBalanceFromAccount getBalanceFromAccount;

	@Test
	public void depositMoneyIntoAnAccount() {
		depositIntoAccount.deposit(clientName, 10);
		assertEquals(account.getBalance(), new Balance(110));
		verify(accountRepository).save(account);
	}

	@Test(expected = AmountNegativeException.class)
	public void depositNegativeAmountIntoAnAccount() {
		depositIntoAccount.deposit(clientName, -10);
	}

}
