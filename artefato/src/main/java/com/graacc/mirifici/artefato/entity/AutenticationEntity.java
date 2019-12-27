package com.graacc.mirifici.artefato.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "autentication")
public class AutenticationEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idautentication")
	private Long idAutentication;
	@Column(name = "email")
	private String email;
	@Column(name = "login")
	private String login;
	@Column(name = "password")
	private String password;
	@Column(name = "salt")
	private String salt;
	@Column(name = "idcustomer")
	private Long idCustomer;
	public Long getIdAutentication() {
		return idAutentication;
	}
	public void setIdAutentication(Long idAutentication) {
		this.idAutentication = idAutentication;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public Long getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(Long idCustomer) {
		this.idCustomer = idCustomer;
	}
}