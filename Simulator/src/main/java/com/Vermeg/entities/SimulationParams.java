package com.Vermeg.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;



@Entity
public class SimulationParams implements Serializable {
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@NotNull
	private int duration;
	
	private double psrate;
	
	@DecimalMax("100")
	private double strategyrate;
	
	private double commission;
	
	@DecimalMax("100")
	private double taxerate;
	
	public long getId() {
		return id;
	}
	
	
	
	public SimulationParams() {
		super();
	}



	public SimulationParams(@NotNull int duration, double psrate, @DecimalMax("100") double strategyrate,
			double commission, @DecimalMax("100") double taxerate) {
		super();
		this.duration = duration;
		this.psrate = psrate;
		this.strategyrate = strategyrate;
		this.commission = commission;
		this.taxerate = taxerate;
	}



	public void setId(long id) {
		this.id = id;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}



	public double getPsrate() {
		return psrate;
	}



	public void setPsrate(double psrate) {
		this.psrate = psrate;
	}



	public double getStrategyrate() {
		return strategyrate;
	}



	public void setStrategyrate(double strategyrate) {
		this.strategyrate = strategyrate;
	}



	public double getCommission() {
		return commission;
	}



	public void setCommission(double commission) {
		this.commission = commission;
	}



	public double getTaxerate() {
		return taxerate;
	}



	public void setTaxerate(double taxerate) {
		this.taxerate = taxerate;
	}
	
	

	
}
