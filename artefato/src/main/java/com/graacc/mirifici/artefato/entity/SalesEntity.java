package com.graacc.mirifici.artefato.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sales")
public class SalesEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idsales")
	private Long idSales;
	@Column(name = "status")
	private String status;
	@Column(name = "value")
	private Double value;
	@Column(name = "payoutdate")
	private Date payoutDate;
	@Column(name = "idproduct")
	private Long idProduct;
	@Column(name = "idcustomer")
	private Long idCustomer;
	public Long getIdSales() {
		return idSales;
	}
	public void setIdSales(Long idSales) {
		this.idSales = idSales;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public Date getPayoutDate() {
		return payoutDate;
	}
	public void setPayoutDate(Date payoutDate) {
		this.payoutDate = payoutDate;
	}
	public Long getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}
	public Long getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(Long idCustomer) {
		this.idCustomer = idCustomer;
	}

}
