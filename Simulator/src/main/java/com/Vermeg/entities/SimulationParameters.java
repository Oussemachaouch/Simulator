package com.Vermeg.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;

@Entity
public class SimulationParameters implements Serializable {
	

	@Id
	@GeneratedValue
	private long id;
	@NotNull
	private int duration;
	private double taxes;
	@DecimalMax("100")
	private double rates;
	public long getId() {
		return id;
	}
	
	
	
	public SimulationParameters() {
		super();
	}


	public SimulationParameters(@NotNull int duration, double taxes, @DecimalMax("100") double rates) {
		super();
		this.duration = duration;
		this.taxes = taxes;
		this.rates = rates;
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
	public double getTaxes() {
		return taxes;
	}
	public void setTaxes(double taxes) {
		this.taxes = taxes;
	}
	public double getRates() {
		return rates;
	}
	public void setRates(double rates) {
		this.rates = rates;
	}
	

	
}
