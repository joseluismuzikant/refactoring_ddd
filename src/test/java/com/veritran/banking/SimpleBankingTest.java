package com.veritran.banking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.veritran.banking.model.Client;
import com.veritran.banking.repository.ClientRepository;
import com.veritran.banking.service.SimpleBanking;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SimpleBankingTest {
	private String clientId;
	private Client client;

	@Mock
	private ClientRepository clientRepository;

	@InjectMocks
	private SimpleBanking simpleBanking;

	@Before
	public void setUp() throws Exception {
		clientId = "francisco";
		client = BankingTestUtils.getClient(clientId, 100);
		when(clientRepository.getOne(clientId)).thenReturn(client);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void createSimpleBanking() {
		assertNotNull(simpleBanking);
	}

	@Test
	public void createClient() {
		assertNotNull(client);
		assertEquals(client.getId(), clientId);
		assertEquals(client.getBalance(), 100);
	}

	@Test
	public void depositMoneyIntoAnAccount() {
		simpleBanking.deposit(clientId, 10);
		assertEquals(client.getBalance(), 110);
	}

	@Test(expected = IllegalArgumentException.class)
	public void depositNegativeAmountIntoAnAccount() {
		simpleBanking.deposit(clientId, -10);
	}

	@Test
	public void withdrawMoneyFromAnAccount() {
		simpleBanking.withdraw(clientId, 10);
		assertEquals(client.getBalance(), 90);
	}

	@Test(expected = IllegalArgumentException.class)
	public void withdrawMoneyFromAnAccountWithOverdraft() {
		simpleBanking.withdraw(clientId, 110);
	}

	@Test(expected = IllegalArgumentException.class)
	public void withdrawMoneyFromAnAccountWithNegativeAmount() {
		simpleBanking.withdraw(clientId, -10);
	}
}
