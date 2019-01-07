package com.plano.accounting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plano.accounting.dao.ClientDAO;
import com.plano.accounting.entity.Client;
import com.plano.accounting.entity.ClientType;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientDAO clientDAO;

	@Override
	@Transactional
	public List<Client> findAll() {
		return clientDAO.findAll();
	}

	@Override
	@Transactional
	public Client getClientById(int id) {
		return clientDAO.getClientById(id);
	}

	@Override
	@Transactional
	public List<Client> getClientByType(ClientType type) {
		return clientDAO.getClientByType(type);
	}

	@Override
	@Transactional
	public Client saveClient(Client client) {
		return clientDAO.saveClient(client);
	}

}
