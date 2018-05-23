package com.Vermeg.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class SimulationParameters implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@NotNull
	private int duration;
	
	private int periodicity;
	
	@Temporal(TemporalType.DATE)
	@Column(name="datedenaissance")
	private Date date;
	
	private double annualpremuim;
	
	@OneToOne
	private FinancialInstrument financialinstrument;
	
	

	public SimulationParameters() {
		super();
	}



	public long getId() {
		return id;
	}



	public int getDuration() {
		return duration;
	}



	public int getPeriodicity() {
		return periodicity;
	}



	public Date getDatedenaissance() {
		return date;
	}



	public double getAnnualpremuim() {
		return annualpremuim;
	}


	
	public FinancialInstrument getFinancialinstrument() {
		return financialinstrument;
	}



	public void setId(long id) {
		this.id = id;
	}



	public void setDuration(int duration) {
		this.duration = duration;
	}



	public void setPeriodicity(int periodicity) {
		this.periodicity = periodicity;
	}



	public void setDatedenaissance(Date datedenaissance) {
		this.date = datedenaissance;
	}



	public void setAnnualpremuim(double annualpremuim) {
		this.annualpremuim = annualpremuim;
	}



	public void setFinancialinstrument(FinancialInstrument financialinstrument) {
		this.financialinstrument = financialinstrument;
	}



	public SimulationParameters(@NotNull int duration, int periodicity, Date date, double annualpremuim,
			FinancialInstrument financialinstrument) {
		super();
		this.duration = duration;
		this.periodicity = periodicity;
		this.date = date;
		this.annualpremuim = annualpremuim;
		this.financialinstrument = financialinstrument;
	}
	
	
	

}
