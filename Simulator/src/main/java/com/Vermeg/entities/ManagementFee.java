package com.Vermeg.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ManagementFee implements Serializable{

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Temporal(TemporalType.DATE)
	@Column(name="LAST_UPDATE_TIME")
	private Date date;
	private double feevalue;
	
	
	
	public ManagementFee() {
		super();
	}
	public ManagementFee(Date date, double feevalue) {
		super();
		
		this.date = date;
		this.feevalue = feevalue;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getFeevalue() {
		return feevalue;
	}
	public void setFeevalue(double feevalue) {
		this.feevalue = feevalue;
	}
	
	
}
