package com.graacc.mirifici.artefato.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "auctionDetails")
public class AuctionDetailEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idauctiondetail")
	private Long idAuctionDetail;
	@Column(name = "throw")
	private Double throwField;
	@Column(name = "throwdate")
	private Date throwDate;
	@Column(name = "idauction")
	private Long idAuction;
	@Column(name = "idcustomer")
	private Long idCustomer;
	public Long getIdAuctionDetail() {
		return idAuctionDetail;
	}
	public void setIdAuctionDetail(Long idAuctionDetail) {
		this.idAuctionDetail = idAuctionDetail;
	}
	public Double getThrowField() {
		return throwField;
	}
	public void setThrowField(Double throwField) {
		this.throwField = throwField;
	}
	public Date getThrowDate() {
		return throwDate;
	}
	public void setThrowDate(Date throwDate) {
		this.throwDate = throwDate;
	}
	public Long getIdAuction() {
		return idAuction;
	}
	public void setIdAuction(Long idAuction) {
		this.idAuction = idAuction;
	}
	public Long getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(Long idCustomer) {
		this.idCustomer = idCustomer;
	}
}
