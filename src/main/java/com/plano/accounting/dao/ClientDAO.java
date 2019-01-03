package com.plano.accounting.dao;

import java.util.List;

import com.plano.accounting.entity.Client;
import com.plano.accounting.entity.ClientType;

public interface ClientDAO {

	public List<Client> findAll();

	public Client getClientById(int id);

	public List<Client> getClientByType(ClientType type);

	public void saveClient(Client client);

}
