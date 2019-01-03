package com.plano.accounting.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "client")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "client_id")
	public int clientId;

	@Column(name = "client_name")
	private String clientName;

	@Enumerated(EnumType.STRING)
	@Column(name = "client_type", length = 10)
	private ClientType clientType;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	public Client() {

	}

	public Client(String clientName, ClientType clientType, String email, String phone) {
		this.clientName = clientName;
		this.clientType = clientType;
		this.email = email;
		this.phone = phone;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public ClientType getClientType() {
		return clientType;
	}

	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", clientName=" + clientName + ", clientType=" + clientType + ", email="
				+ email + ", phone=" + phone + "]";
	}

}
