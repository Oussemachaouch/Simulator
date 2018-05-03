package com.Vermeg.enumeration;

public enum ResultType {
	
	Evo_regular_inv_Main("Evo regular inv Main"), 
	Evo_unique_inv_Main("Evo unique inv Main"),
	Pure_regular_inv("Pure regular inv"),
	Reserve_Main("Reserve Main"),
	Evo_regular_inv_TOT("Evo regular inv TOT"),
	Tax_R_P("Tax R P"),
	Tax_U_P("Tax U P"),
	Evo_unique_inv_TOT("Evo unique inv TOT"),
	Reserve_TOT("Reserve TOT"),
	Reserve_PS("Reserve PS"),
	Charge_Regul_prem("Charge Regul prem"),
	
	Regular_premium("Regular premium");
	
	

	private final String description;
	
	private ResultType(String description) {
		this.description = description;
	}

	public String getDesc() {
		return description;
	}
	
	
}
