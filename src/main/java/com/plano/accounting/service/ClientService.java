package com.plano.accounting.service;

import java.util.List;

import com.plano.accounting.entity.Client;
import com.plano.accounting.entity.ClientType;

public interface ClientService {
	
	public List<Client> findAll();
	
	public Client getClientById(int id);
	
	public List<Client> getClientByType(ClientType type);
	
	public Client saveClient(Client client);

}
