package com.graacc.mirifici.artefato.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "auctions")
public class AuctionEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idauction")
	private Long idAuction;
	
	@Column(name="minincrementvalue")
	private Double minValueIncrease;
	
	@Temporal(TemporalType.DATE)
	@Column(name="initauctiondate")
	private Date initAuctionDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "finalauctiondate")
	private Date finalAuctionDate;
	
	@Column(name = "idproduct")
	private Long idProduto;

	public Long getIdAuction() {
		return idAuction;
	}

	public void setIdAuction(Long idAuction) {
		this.idAuction = idAuction;
	}

	public Double getMinValueIncrease() {
		return minValueIncrease;
	}

	public void setMinValueIncrease(Double minValueIncrease) {
		this.minValueIncrease = minValueIncrease;
	}

	public Date getInitAuctionDate() {
		return initAuctionDate;
	}

	public void setInitAuctionDate(Date initAuctionDate) {
		this.initAuctionDate = initAuctionDate;
	}

	public Date getFinalAuctionDate() {
		return finalAuctionDate;
	}

	public void setFinalAuctionDate(Date finalAuctionDate) {
		this.finalAuctionDate = finalAuctionDate;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}	
}