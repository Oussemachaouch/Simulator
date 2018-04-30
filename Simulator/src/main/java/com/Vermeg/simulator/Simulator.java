package com.Vermeg.simulator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Service;
import com.Vermeg.entities.ComputedDataConfig;
import com.Vermeg.entities.SimulationParams;
import com.Vermeg.enumeration.ResultType;
import com.Vermeg.services.CalculationMethods;

@Service
public class Simulator {
	
	CalculationMethods calculationmethod = new CalculationMethods();
	
	public Simulator() {
		super();
	}


	public boolean CheckIfProvidedRulesExistInResultType(ComputedDataConfig computedDataConfig)
	{
			
		int iterateResultTypeenum = 0;
	    
		Set enumresultType = new HashSet();
	    	
		while(iterateResultTypeenum <= ResultType.values().length-1)
	    	{
				enumresultType.add(ResultType.values()[iterateResultTypeenum].getDesc());
				iterateResultTypeenum++ ;
	    	}
 
	
	    
	    if (computedDataConfig.getResultTypeMap().keySet().containsAll(enumresultType))
	    	{
	    		System.out.println("All Rules Exist");
	    		return true; 
	    	}	
	    else {
	    		System.out.println("Invalid Rules");
	    		return false;
	    	}
	}
	

	public int findpositionbyName(String name)
	{
		Set enumresultType = new HashSet();
		
		int iterate = 0;
		
		
		while(iterate <= ResultType.values().length-1)
    	{
		if (ResultType.values()[iterate].getDesc().equals(name))
			{
				return iterate;
			}
		
		iterate++ ;
    	}
		
		return 0;
		
	}
	
	public List<Map<String,Double>> simulator(ComputedDataConfig computedDataConfig,SimulationParams simulationparameters)
	{
		List<Map<String,Double>> resultsofSimulation = new ArrayList<Map<String,Double>>();
	
		
		if (CheckIfProvidedRulesExistInResultType(computedDataConfig))
		{		
			double iteration=computedDataConfig.getIterator();
				
			
			Map<String,Double> map = new HashMap<>(computedDataConfig.getResultTypeMap());
			
		
			while ( iteration <= simulationparameters.getDuration())
		 	{
	    	
				int positionEvoregularinvMain=findpositionbyName("Evo regular inv Main");
				int positionEvouniqueinvMain=findpositionbyName("Evo unique inv Main");
				int positionReserveMain=findpositionbyName("Reserve Main");
				int positionEvoregularinvTOT=findpositionbyName("Evo regular inv TOT");
				int positionEvouniqueinvTOT=findpositionbyName("Evo unique inv TOT");
				int positionReserveTOT=findpositionbyName("Reserve TOT");
				int positionPureregularinv=findpositionbyName("Pure regular inv");
				int positionChargeRegulprem=findpositionbyName("Charge Regul prem");
				int positionRegularpremium=findpositionbyName("Regular premium");
				int positionReservePS=findpositionbyName("Reserve PS");
			
				String EvoregularinvMainkey=ResultType.values()[positionEvoregularinvMain].getDesc();
				String EvouniqueinvMainkey=ResultType.values()[positionEvouniqueinvMain].getDesc();
				String ReserveMainkey=ResultType.values()[positionReserveMain].getDesc();
				String EvoregularinvTOTkey=ResultType.values()[positionEvoregularinvTOT].getDesc();
				String EvouniqueinvTOTkey=ResultType.values()[positionEvouniqueinvTOT].getDesc();
				String ReserveTOTkey=ResultType.values()[positionReserveTOT].getDesc();
				String Pureregularinvkey=ResultType.values()[positionPureregularinv].getDesc();
				String ChargeRegulpremkey=ResultType.values()[positionChargeRegulprem].getDesc();
				String Regularpremiumkey=ResultType.values()[positionRegularpremium].getDesc();
				String ReservePSkey=ResultType.values()[positionReservePS].getDesc();
			
			
				double ruleEvo_regular_inv_Main =(map.get(EvoregularinvMainkey)+map.get(Pureregularinvkey))*(1+simulationparameters.getStrategyrate());
			
				map.put(EvoregularinvMainkey,ruleEvo_regular_inv_Main);
				
				map.put(EvouniqueinvMainkey,calculationmethod.EvouniqueInvestmentMain(map.get(EvouniqueinvMainkey), simulationparameters.getStrategyrate()));
	    	
				map.put(ReserveMainkey,calculationmethod.sommation(map.get(EvoregularinvMainkey),map.get(EvouniqueinvMainkey)));
				
				map.put(EvoregularinvTOTkey,(map.get(EvoregularinvTOTkey)+map.get(Pureregularinvkey))*(1+simulationparameters.getStrategyrate()+simulationparameters.getPsrate()));
	    	
				map.put(EvouniqueinvTOTkey,map.get(EvouniqueinvTOTkey)*(1+simulationparameters.getStrategyrate()+simulationparameters.getPsrate()));
	    	
				map.put(ReserveTOTkey,calculationmethod.sommation(map.get(EvoregularinvTOTkey),map.get(EvouniqueinvTOTkey)));
	    	
				map.put(Pureregularinvkey, calculationmethod.sommation(map.get(ChargeRegulpremkey),map.get(Regularpremiumkey)));
	    	
				map.put(ChargeRegulpremkey,calculationmethod.ChargeRegularPremuim(simulationparameters.getCommission(), map.get(Regularpremiumkey)));
				
				map.put(ReservePSkey,calculationmethod.ReservePS(map.get(ReserveTOTkey), map.get(ReserveMainkey)));
	    	
				Map<String,Double> newresulttypemap = new HashMap<>(map);
				
				resultsofSimulation.add(newresulttypemap);
	    	
				//System.out.println(map);
				//System.out.println();
				iteration++;
	    	
		 	}
		
	    System.out.println(resultsofSimulation);
		}
		
		return resultsofSimulation;
		
	}


}
