package com.plano.accounting.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.plano.accounting.entity.Client;
import com.plano.accounting.entity.ClientType;
import com.plano.accounting.service.ClientService;

@RunWith(SpringRunner.class)
@WebMvcTest(ClientController.class)
public class ClientControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ClientService clientService;

	Client mockClient;

	@Before
	public void setUp() {
		mockClient = new Client("Rob Nashford", ClientType.INDIVIDUAL, "bob@test.com", "1234567890");
		mockClient.setClientId(1);
	}

	@Test
	public void findAllReturnsClientList() throws Exception {

		List<Client> clientList = Arrays.asList(mockClient);

		given(this.clientService.findAll()).willReturn(clientList);

		mvc.perform(get("/api/clients").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].clientName", is(mockClient.getClientName())));

	}

	@Test
	public void testGetClientByIdReturnsClient() throws Exception {
		given(this.clientService.getClientById(Mockito.anyInt())).willReturn(mockClient);

		MvcResult result = mvc.perform(get("/api/clients/100").contentType(MediaType.APPLICATION_JSON)).andReturn();
		String expected = "{\"clientId\":1,\"clientName\":\"Rob Nashford\",\"clientType\":\"INDIVIDUAL\",\"email\":\"bob@test.com\",\"phone\":\"1234567890\"}";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

	}

	@Test
	public void testCreateClient() throws Exception {
		// clientService.saveClient to respond back with mockClient
		given(clientService.saveClient(Mockito.any(Client.class))).willReturn(mockClient);
		String jsonPayload = "{\"clientId\":1,\"clientName\":\"Rob Nashford\",\"clientType\":\"INDIVIDUAL\",\"email\":\"bob@test.com\",\"phone\":\"1234567890\"}";
		// Send client as body to save new client
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/clients").accept(MediaType.APPLICATION_JSON)
				.content(jsonPayload).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatus());

	}

}
