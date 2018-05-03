package com.Vermeg.services;

public class CalculationMethods {

	public CalculationMethods(){
		super();
	}
	
	public double EvouniqueInvestmentMain(double lastvalue,double strategyrate){
		return lastvalue*(1+strategyrate);
	}
	public double sum(double mainevoregularinvestment,double mainevouniqueinvestment) {
		
		return mainevoregularinvestment+mainevouniqueinvestment;
	}

	public double ReservePS(double totalReserve,double mainreserve) {
		
		return totalReserve-mainreserve;
	}
	
	public double ChargeRegularPremuim(double commission,double regularpremuim) {
		
		return -commission*regularpremuim;
	}

}
