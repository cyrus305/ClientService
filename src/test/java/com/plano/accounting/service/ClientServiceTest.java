package com.plano.accounting.service;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.plano.accounting.dao.ClientDAO;
import com.plano.accounting.entity.Client;
import com.plano.accounting.entity.ClientType;

@RunWith(SpringRunner.class)
public class ClientServiceTest {

	@TestConfiguration
	static class ClientServiceImplTestContextConfiguration {

		@Bean
		public ClientService clientService() {
			return new ClientServiceImpl();
		}
	}

	@Autowired
	private ClientService clientService;

	@MockBean
	private ClientDAO clientDAO;

	Client mockClient;

	@Before
	public void setUp() {
		mockClient = new Client("Rob Nashford", ClientType.INDIVIDUAL, "bob@test.com", "1234567890");
		mockClient.setClientId(1);
	}

	@Test
	public void whenValidClientTypethenClientShouldBeFound() {
		Mockito.when(clientDAO.getClientByType(mockClient.getClientType())).thenReturn(Arrays.asList(mockClient));
		List<Client> found = clientService.getClientByType(mockClient.getClientType());

		Assert.assertEquals(found.get(0).getClientName(), mockClient.getClientName());
	}

}
