package com.graacc.mirifici.artefato.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contacts")
public class ContactEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcontact")
	private Long idContato;
	@Column(name = "idcustomer")
	private Long idCustomer;
	@Column(name = "ddd")
	private Integer ddd;
	@Column(name = "number")
	private Integer number;
	@Column(name = "mobile")
	private boolean mobile;
	public Long getIdContato() {
		return idContato;
	}
	public void setIdContact(Long idContact) {
		this.idContato = idContact;
	}
	public Long getIdCustomerr() {
		return idCustomer;
	}
	public void setIdCustomer(Long idCustomer) {
		this.idCustomer = idCustomer;
	}
	public Integer getDdd() {
		return ddd;
	}
	public void setDdd(Integer ddd) {
		this.ddd = ddd;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public boolean isMobile() {
		return mobile;
	}
	public void setMobile(boolean mobile) {
		this.mobile = mobile;
	}

}