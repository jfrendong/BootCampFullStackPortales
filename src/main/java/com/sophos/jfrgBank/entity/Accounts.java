package com.sophos.jfrgBank.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(name="accounts")
public class Accounts {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idA;
	@Column(name="Tipo_Cuenta") @NotNull
	private String accType; 
	@Column(name="Nro_Cuenta") @NotNull
	private String accNumber;
	@Column(name="Estado") @NotNull
	private String status;
	@Column(name="Saldo") @NotNull
	private int accB;
	@Column(name="Saldo_Disponible") @NotNull
	private int accAvailableB;
	@Column(name="GMF") @NotNull
	private Boolean gmf;
	@Column(name="Fecha_Creación") @NotNull
	@PastOrPresent
	private LocalDate creationDate;
	@Column(name="Usuario_Creación") @NotNull
	private String creationUser;
	@Column(name="Fecha_Modificación")
	@PastOrPresent
	private LocalDate modDate;
	@Column(name="Usuario_Modificación")
	private String modUser;
	
	@ManyToOne
	@JoinColumn(name="ID_Cliente", referencedColumnName="Client_ID")
	private Client client;
	
	@OneToMany(mappedBy="account", cascade= CascadeType.ALL)
	private List <Transactions> transactions = new ArrayList<>();
	
	public enum AccType {
		Ahorros, Corriente
	}
	
	public enum AccStatus {
		Activa, Inactiva, Cancelada
	}
	
	public Accounts () {
	}

	public int getIdA() {
		return idA;
	}

	public void setIdA(int idA) {
		this.idA = idA;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public String getAccNumber() {
		return accNumber;
	}

	public void setAccNumber(String accNumber) {
		this.accNumber = accNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getAccB() {
		return accB;
	}

	public void setAccBalance(int accB) {
		this.accB = accB;
	}

	public int getAccAvailableB() {
		return accAvailableB;
	}

	public void setAccAvailableB(int accAvailableB) {
		this.accAvailableB = accAvailableB;
	}

	public Boolean getGmf() {
		return gmf;
	}

	public void setGmf(Boolean gmf) {
		this.gmf = gmf;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreationUser() {
		return creationUser;
	}

	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
	}

	public LocalDate getModDate() {
		return modDate;
	}

	public void setModDate(LocalDate modDate) {
		this.modDate = modDate;
	}

	public String getModUser() {
		return modUser;
	}

	public void setModUser(String modUser) {
		this.modUser = modUser;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Transactions> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transactions> transactions) {
		this.transactions = transactions;
	}
	
}