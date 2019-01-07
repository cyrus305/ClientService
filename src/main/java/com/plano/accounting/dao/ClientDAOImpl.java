package com.plano.accounting.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.plano.accounting.entity.Client;
import com.plano.accounting.entity.ClientType;

@Repository
public class ClientDAOImpl implements ClientDAO {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Client> findAll() {

		Session currentSession = entityManager.unwrap(Session.class);
		Query<Client> query = currentSession.createQuery("from Client", Client.class);
		List<Client> client = query.getResultList();

		return client;
	}

	@Override
	public Client getClientById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		return currentSession.get(Client.class, id);
	}

	@Override
	public List<Client> getClientByType(ClientType type) {
		String hql = "FROM Client WHERE client_type = " + "'" + type + "'";

		Session currentSession = entityManager.unwrap(Session.class);
		Query<Client> query = currentSession.createQuery(hql, Client.class);

		List<Client> client = query.getResultList();

		return client;
	}

	@Override
	public Client saveClient(Client client) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(client);
		return client;
	}

}
