package com.veritran.banking;

import static org.junit.Assert.assertEquals;
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
public class BankingTransferTest {
	private String clientId1 = "francisco";
	private String clientId2 =  "pedro";
	Client client1 ;
	Client client2 ;

	@Mock
	private ClientRepository clientRepository;

	@InjectMocks
	private SimpleBanking simpleBanking;

	@Before
	public void setUp() throws Exception {
		client1 = BankingTestUtils.getClient("francisco", 100);
		client2 = BankingTestUtils.getClient("pedro", 50);
		when(clientRepository.getOne("francisco")).thenReturn(client1);
		when(clientRepository.getOne("pedro")).thenReturn(client2);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void transferMoney() {
		simpleBanking.transfer(clientId1,clientId2, 10);
		assertEquals(client1.getBalance(), 90);
		assertEquals(client2.getBalance(), 60);
	}

	@Test(expected = IllegalArgumentException.class)
	public void transferMoneyWithoutEnoughFunds() {
		simpleBanking.transfer(clientId1, clientId2, 110);
	}

	@Test(expected = IllegalArgumentException.class)
	public void transferNegativeAmount() {
		simpleBanking.transfer(clientId1, clientId2, -10);
	}

	@Test(expected = IllegalArgumentException.class)
	public void transferCeroAmount() {
		simpleBanking.transfer(clientId1, clientId2, 0);
	}
}
