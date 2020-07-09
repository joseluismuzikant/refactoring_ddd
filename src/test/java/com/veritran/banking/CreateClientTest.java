package com.veritran.banking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.veritran.banking.domain.model.Account;
import com.veritran.banking.domain.model.Balance;
import com.veritran.banking.domain.model.Client;
import com.veritran.banking.domain.model.repository.Accounts;
import com.veritran.banking.domain.model.repository.Clients;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CreateClientTest {
	protected String clientName;
	protected Client client;
	protected Account account;

	@Mock
	protected Clients clientRepository;
	@Mock
	protected Accounts accountRepository;

	@Before
	public void setUp() throws Exception {
		clientName = "francisco";
		client = BankingTestUtils.getClient(clientName);
		account = BankingTestUtils.getAccount(client, 100);
		when(clientRepository.findByName(clientName)).thenReturn(client);
		when(accountRepository.findById(client.getAccountId())).thenReturn(Optional.of(account));
	}

	@Test
	public void createClient() {
		assertNotNull(client);
		assertEquals(client.getName(), clientName);
		Account clientAccount = accountRepository.findById(client.getAccountId()).get();
		assertNotNull(clientAccount);
		assertTrue(clientAccount.getBalance().equals(new Balance(100)));
	}

}
