package com.plano.accounting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.plano.accounting.entity.Client;
import com.plano.accounting.entity.ClientType;
import com.plano.accounting.exception.ClientNotFoundException;
import com.plano.accounting.service.ClientService;

@RestController
@RequestMapping("/api")
public class ClientController {

	@Autowired
	private ClientService clientService;

	@GetMapping("/clients")
	public List<Client> findAll() {
		return clientService.findAll();
	}

	@GetMapping("/clients/{clientId}")
	public ResponseEntity<Client> getClientById(@PathVariable int clientId) {
		Client client = clientService.getClientById(clientId);

		if (client == null) {
			throw new ClientNotFoundException("client with given id not found");
		}
		return new ResponseEntity<Client>(client, HttpStatus.OK);
	}

	@GetMapping("clients/search")
	public List<Client> getClientsByType(@RequestParam("client-type") ClientType clientType) {
		List<Client> client = clientService.getClientByType(clientType);

		if (client == null) {
			throw new ClientNotFoundException("client with given client-type not found");
		}
		return client;
	}

	@PostMapping("/clients")
	public ResponseEntity<Client> createClient(@RequestBody Client client) {
		clientService.saveClient(client);
		return new ResponseEntity<Client>(client, HttpStatus.CREATED);
	}

}
