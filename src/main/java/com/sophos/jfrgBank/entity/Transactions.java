package com.sophos.jfrgBank.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(name="transactions")
public class Transactions {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idT;
	@Column(name="Tipo_Transacción") @NotNull
	private String transactionType;
	@Column(name="Fecha_Movimiento") @NotNull
	@PastOrPresent
	private LocalDate transactionDate;
	@Column(name="Descripción")
	private String description;
	@Column(name="Valor") @NotNull
	private int value;
	@Column(name="Tipo_Movimiento") @NotNull
	private String movementType;
	@Column(name="Cuenta_Destino")
	private String accDes;
	@Column(name="Saldo")
	private double accB;
	@Column(name="Saldo_Disponible")
	private double accAvB;
	
	@ManyToOne
	private Accounts account;
	
	public Transactions () {
	}

	public int getIdT() {
		return idT;
	}

	public void setIdT(int idT) {
		this.idT = idT;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getMovementType() {
		return movementType;
	}

	public void setMovementType(String movementType) {
		this.movementType = movementType;
	}
	
	public String getAccDes() {
		return accDes;
	}

	public void setAccDes(String accDes) {
		this.accDes = accDes;
	}

	public double getAccB() {
		return accB;
	}

	public void setAccB(double accB) {
		this.accB = accB;
	}
	
	public double getAccAvB() {
		return accAvB;
	}

	public void setAccAvB(double accAvB) {
		this.accAvB = accAvB;
	}

	public Accounts getAccount() {
		return account;
	}

	public void setAccount(Accounts account) {
		this.account = account;
	}

	
	
	

}
