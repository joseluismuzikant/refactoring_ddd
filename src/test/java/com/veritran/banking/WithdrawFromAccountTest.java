package com.veritran.banking;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.veritran.banking.domain.model.Balance;
import com.veritran.banking.domain.model.exception.AmountNegativeException;
import com.veritran.banking.domain.services.WithdrawFromAccount;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class WithdrawFromAccountTest  extends CreateClientTest{
	
	@InjectMocks
	private WithdrawFromAccount withdrawFromAccount;
	

	@Test
	public void depositMoneyIntoAnAccount() {
		withdrawFromAccount.withdraw(clientName, 10);
		assertEquals(account.getBalance(), new Balance(90));
		verify(accountRepository).save(account);
	}

	@Test(expected = AmountNegativeException.class)
	public void depositNegativeAmountIntoAnAccount() {
		withdrawFromAccount.withdraw(clientName, -10);
	}
}
