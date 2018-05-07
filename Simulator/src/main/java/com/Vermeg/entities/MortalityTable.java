package com.Vermeg.entities;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class MortalityTable {

	
	@Id
    private MortalityTableAges mortalitytableage;
	private double mortalityquotient;
	
	
	public MortalityTable() {
		super();
	}
	
	public MortalityTable( double mortalityquotient) {
		super();
		
		this.mortalityquotient = mortalityquotient;
	}


	public MortalityTable(MortalityTableAges mortalitytableage, double mortalityquotient) {
		super();
		this.mortalitytableage = mortalitytableage;
		this.mortalityquotient = mortalityquotient;
	}

	public double getMortalityquotient() {
		return mortalityquotient;
	}
	public void setMortalityquotient(double mortalityquotient) {
		this.mortalityquotient = mortalityquotient;
	}

	public MortalityTableAges getMortalitytableage() {
		return mortalitytableage;
	}

	public void setMortalitytableage(MortalityTableAges mortalitytableage) {
		this.mortalitytableage = mortalitytableage;
	}

	
	
}
