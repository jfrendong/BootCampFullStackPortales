package com.sophos.jfrgBank.entity;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;



@Entity
@Table(name="clients")
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_Cliente")
	private int id;
	@Column(name="Tipo_Identificación") @NotNull
	private String idType;
	@Column(name="Nro_Identificación") @NotNull
	private int idNumber;
	@Column(name="Nombres") @Size(min=2, max=30)
	private String name;
	@Column(name="Apellidos") @Size(min=2, max=30)
	private String lastName;
	@Column(name="Correo_Electrónico") @NotNull @Email
	private String email;
	@Column(name="Fecha_Nacimiento") @Past
	private LocalDate birthDay;
	@Column(name="Fecha_Creación") @PastOrPresent
	private LocalDate creationDate;
	@Column(name="Usuario_Creación")
	private String creationUser;
	@Column(name="Fecha_Modificación") @PastOrPresent
	private LocalDate modDate;
	@Column(name="Usuario_Modificación")
	private String modUser;
	
	@OneToMany(mappedBy="client", cascade= CascadeType.ALL)
	@JsonIgnore
	private List <Accounts> accounts;
		
	public enum IdType {
		CC, CE, PAP
	}
	
	public Client ( ) {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public int getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(int idNumber) {
		this.idNumber = idNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(LocalDate birthDay) {
		this.birthDay = birthDay;
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
	
	public List<Accounts> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Accounts> accounts) {
		this.accounts = accounts;
	}
	
}
