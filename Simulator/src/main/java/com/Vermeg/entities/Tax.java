package com.Vermeg.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tax implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String taxname;
	private double taxvalue;
	
	
	
	public Tax() {
		super();
	}
	public Tax(String taxname, double taxvalue) {
		super();
		
		this.taxname = taxname;
		this.taxvalue = taxvalue;
	}
	public String getTaxname() {
		return taxname;
	}
	public void setTaxname(String taxname) {
		this.taxname = taxname;
	}
	public double getTaxvalue() {
		return taxvalue;
	}
	public void setTaxvalue(double taxvalue) {
		this.taxvalue = taxvalue;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	

}
