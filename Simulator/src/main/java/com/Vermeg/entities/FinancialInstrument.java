package com.Vermeg.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FinancialInstrument implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name;
	private double minbaserate;
	private double avgbaserate;
	private double maxbaserate;
	private double minpsrate;
	private double avgpsrate;
	private double maxpsrate;
	
	
	public FinancialInstrument() {
		super();
	}
	public FinancialInstrument(String name, double minbaserate, double avgbaserate, double maxbaserate,
			double minpsrate, double avgpsrate, double maxpsrate) {
		super();
		
		this.name = name;
		this.minbaserate = minbaserate;
		this.avgbaserate = avgbaserate;
		this.maxbaserate = maxbaserate;
		this.minpsrate = minpsrate;
		this.avgpsrate = avgpsrate;
		this.maxpsrate = maxpsrate;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getMinbaserate() {
		return minbaserate;
	}
	public void setMinbaserate(double minbaserate) {
		this.minbaserate = minbaserate;
	}
	public double getAvgbaserate() {
		return avgbaserate;
	}
	public void setAvgbaserate(double avgbaserate) {
		this.avgbaserate = avgbaserate;
	}
	public double getMaxbaserate() {
		return maxbaserate;
	}
	public void setMaxbaserate(double maxbaserate) {
		this.maxbaserate = maxbaserate;
	}
	public double getMinpsrate() {
		return minpsrate;
	}
	public void setMinpsrate(double minpsrate) {
		this.minpsrate = minpsrate;
	}
	public double getAvgpsrate() {
		return avgpsrate;
	}
	public void setAvgpsrate(double avgpsrate) {
		this.avgpsrate = avgpsrate;
	}
	public double getMaxpsrate() {
		return maxpsrate;
	}
	public void setMaxpsrate(double maxpsrate) {
		this.maxpsrate = maxpsrate;
	}
	
	
	

}
