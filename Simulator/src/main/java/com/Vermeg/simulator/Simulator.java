package com.Vermeg.simulator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Service;
import com.Vermeg.entities.ComputedDataConfig;
import com.Vermeg.entities.FinancialInstrument;
import com.Vermeg.entities.SimulationParameters;
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
	    	
				//int positionUniquepremium=findpositionbyName("Unique premium");
				
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
				//String Uniquepremiumkey=ResultType.values()[positionUniquepremium].getDesc();
				
			
				double ruleEvo_regular_inv_Main =(map.get(EvoregularinvMainkey)+map.get(Pureregularinvkey))*(1+simulationparameters.getStrategyrate());
			
				map.put(EvoregularinvMainkey,ruleEvo_regular_inv_Main);
				
				map.put(EvouniqueinvMainkey,calculationmethod.EvouniqueInvestmentMain(map.get(EvouniqueinvMainkey), simulationparameters.getStrategyrate()));
	    	
				map.put(ReserveMainkey,calculationmethod.sum(map.get(EvoregularinvMainkey),map.get(EvouniqueinvMainkey)));
				
				map.put(EvoregularinvTOTkey,(map.get(EvoregularinvTOTkey)+map.get(Pureregularinvkey))*(1+simulationparameters.getStrategyrate()+simulationparameters.getPsrate()));
	    	
				map.put(EvouniqueinvTOTkey,map.get(EvouniqueinvTOTkey)*(1+simulationparameters.getStrategyrate()+simulationparameters.getPsrate()));
	    	
				map.put(ReserveTOTkey,calculationmethod.sum(map.get(EvoregularinvTOTkey),map.get(EvouniqueinvTOTkey)));
	    	
				map.put(Pureregularinvkey, calculationmethod.sum(map.get(ChargeRegulpremkey),map.get(Regularpremiumkey)));
	    	
				map.put(ChargeRegulpremkey,calculationmethod.ChargeRegularPremuim(simulationparameters.getCommission(), map.get(Regularpremiumkey)));
				
				map.put(ReservePSkey,calculationmethod.ReservePS(map.get(ReserveTOTkey), map.get(ReserveMainkey)));
	    	
				Map<String,Double> newresulttypemap = new HashMap<>(map);
				
				resultsofSimulation.add(newresulttypemap);
				
				
				iteration++;
	    	
		 	}
		
	    System.out.println(resultsofSimulation);
		}
		
		return resultsofSimulation;
		
	}

	public List<Map<String,Double>> simulatorMinResults(ComputedDataConfig computedDataConfig,SimulationParameters financialinstrument)
	{
		List<Map<String,Double>> resultsofSimulation = new ArrayList<Map<String,Double>>();
	
		
		if (CheckIfProvidedRulesExistInResultType(computedDataConfig))
		{		
			double iteration=computedDataConfig.getIterator();
			
			Map<String,Double> map = new HashMap<>(computedDataConfig.getResultTypeMap());
			
			while ( iteration <= (financialinstrument.getDuration()*(12/financialinstrument.getPeriodicity())))
		 	{
	    	
				//int positionUniquepremium=findpositionbyName("Unique premium");
				
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
				//String Uniquepremiumkey=ResultType.values()[positionUniquepremium].getDesc();
				
			
				double ruleEvo_regular_inv_Main =(map.get(EvoregularinvMainkey)+map.get(Pureregularinvkey))*(1+financialinstrument.getFinancialinstrument().getMinbaserate());
			
				map.put(EvoregularinvMainkey,ruleEvo_regular_inv_Main);
				
				map.put(EvouniqueinvMainkey,calculationmethod.EvouniqueInvestmentMain(map.get(EvouniqueinvMainkey), financialinstrument.getFinancialinstrument().getMinbaserate()));
	    	
				map.put(ReserveMainkey,calculationmethod.sum(map.get(EvoregularinvMainkey),map.get(EvouniqueinvMainkey)));
				
				map.put(EvoregularinvTOTkey,(map.get(EvoregularinvTOTkey)+map.get(Pureregularinvkey))*(1+financialinstrument.getFinancialinstrument().getMinpsrate()+financialinstrument.getFinancialinstrument().getMinpsrate()));
	    	
				map.put(EvouniqueinvTOTkey,map.get(EvouniqueinvTOTkey)*(1+financialinstrument.getFinancialinstrument().getMinbaserate()+financialinstrument.getFinancialinstrument().getMinpsrate()));
	    	
				map.put(ReserveTOTkey,calculationmethod.sum(map.get(EvoregularinvTOTkey),map.get(EvouniqueinvTOTkey)));
	    	
				map.put(Pureregularinvkey, calculationmethod.sum(map.get(ChargeRegulpremkey),map.get(Regularpremiumkey)));
	    	
				//map.put(ChargeRegulpremkey,calculationmethod.ChargeRegularPremuim(simulationparameters.getCommission(), map.get(Regularpremiumkey)));
				
				map.put(ReservePSkey,calculationmethod.ReservePS(map.get(ReserveTOTkey), map.get(ReserveMainkey)));
	    	
				Map<String,Double> newresulttypemap = new HashMap<>(map);
				
				resultsofSimulation.add(newresulttypemap);
				
				
				iteration++;
	    	
		 	}
		
	    System.out.println(resultsofSimulation);
		}
		
		return resultsofSimulation;
		
	}
	public List<Map<String,Double>> simulatorAvgResults(ComputedDataConfig computedDataConfig,SimulationParameters financialinstrument)
	{
		List<Map<String,Double>> resultsofSimulation = new ArrayList<Map<String,Double>>();
	
		
		if (CheckIfProvidedRulesExistInResultType(computedDataConfig))
		{		
			double iteration=computedDataConfig.getIterator();
			
			Map<String,Double> map = new HashMap<>(computedDataConfig.getResultTypeMap());
			
			while ( iteration <= (financialinstrument.getDuration()*(12/financialinstrument.getPeriodicity())))
		 	{
	    	
				//int positionUniquepremium=findpositionbyName("Unique premium");
				
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
				//String Uniquepremiumkey=ResultType.values()[positionUniquepremium].getDesc();
				
			
				double ruleEvo_regular_inv_Main =(map.get(EvoregularinvMainkey)+map.get(Pureregularinvkey))*(1+financialinstrument.getFinancialinstrument().getAvgbaserate());
			
				map.put(EvoregularinvMainkey,ruleEvo_regular_inv_Main);
				
				map.put(EvouniqueinvMainkey,calculationmethod.EvouniqueInvestmentMain(map.get(EvouniqueinvMainkey), financialinstrument.getFinancialinstrument().getAvgbaserate()));
	    	
				map.put(ReserveMainkey,calculationmethod.sum(map.get(EvoregularinvMainkey),map.get(EvouniqueinvMainkey)));
				
				map.put(EvoregularinvTOTkey,(map.get(EvoregularinvTOTkey)+map.get(Pureregularinvkey))*(1+financialinstrument.getFinancialinstrument().getAvgpsrate()+financialinstrument.getFinancialinstrument().getAvgpsrate()));
	    	
				map.put(EvouniqueinvTOTkey,map.get(EvouniqueinvTOTkey)*(1+financialinstrument.getFinancialinstrument().getAvgbaserate()+financialinstrument.getFinancialinstrument().getAvgpsrate()));
	    	
				map.put(ReserveTOTkey,calculationmethod.sum(map.get(EvoregularinvTOTkey),map.get(EvouniqueinvTOTkey)));
	    	
				map.put(Pureregularinvkey, calculationmethod.sum(map.get(ChargeRegulpremkey),map.get(Regularpremiumkey)));
	    	
				//map.put(ChargeRegulpremkey,calculationmethod.ChargeRegularPremuim(simulationparameters.getCommission(), map.get(Regularpremiumkey)));
				
				map.put(ReservePSkey,calculationmethod.ReservePS(map.get(ReserveTOTkey), map.get(ReserveMainkey)));
	    	
				Map<String,Double> newresulttypemap = new HashMap<>(map);
				
				resultsofSimulation.add(newresulttypemap);
				
				
				iteration++;
	    	
		 	}
		
	    System.out.println(resultsofSimulation);
		}
		
		return resultsofSimulation;
		
	}

	public List<Map<String,Double>> simulatorMaxResults(ComputedDataConfig computedDataConfig,SimulationParameters financialinstrument)
	{
		List<Map<String,Double>> resultsofSimulation = new ArrayList<Map<String,Double>>();
	
		
		if (CheckIfProvidedRulesExistInResultType(computedDataConfig))
		{		
			double iteration=computedDataConfig.getIterator();
			
			Map<String,Double> map = new HashMap<>(computedDataConfig.getResultTypeMap());
			
			while ( iteration <= (financialinstrument.getDuration()*(12/financialinstrument.getPeriodicity())))
		 	{
	    	
				//int positionUniquepremium=findpositionbyName("Unique premium");
				
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
				//String Uniquepremiumkey=ResultType.values()[positionUniquepremium].getDesc();
				
			
				double ruleEvo_regular_inv_Main =(map.get(EvoregularinvMainkey)+map.get(Pureregularinvkey))*(1+financialinstrument.getFinancialinstrument().getMaxbaserate());
			
				map.put(EvoregularinvMainkey,ruleEvo_regular_inv_Main);
				
				map.put(EvouniqueinvMainkey,calculationmethod.EvouniqueInvestmentMain(map.get(EvouniqueinvMainkey), financialinstrument.getFinancialinstrument().getMaxbaserate()));
	    	
				map.put(ReserveMainkey,calculationmethod.sum(map.get(EvoregularinvMainkey),map.get(EvouniqueinvMainkey)));
				
				map.put(EvoregularinvTOTkey,(map.get(EvoregularinvTOTkey)+map.get(Pureregularinvkey))*(1+financialinstrument.getFinancialinstrument().getMaxpsrate()+financialinstrument.getFinancialinstrument().getMaxpsrate()));
	    	
				map.put(EvouniqueinvTOTkey,map.get(EvouniqueinvTOTkey)*(1+financialinstrument.getFinancialinstrument().getMaxbaserate()+financialinstrument.getFinancialinstrument().getMaxpsrate()));
	    	
				map.put(ReserveTOTkey,calculationmethod.sum(map.get(EvoregularinvTOTkey),map.get(EvouniqueinvTOTkey)));
	    	
				map.put(Pureregularinvkey, calculationmethod.sum(map.get(ChargeRegulpremkey),map.get(Regularpremiumkey)));
	    	
				//map.put(ChargeRegulpremkey,calculationmethod.ChargeRegularPremuim(simulationparameters.getCommission(), map.get(Regularpremiumkey)));
				
				map.put(ReservePSkey,calculationmethod.ReservePS(map.get(ReserveTOTkey), map.get(ReserveMainkey)));
	    	
				Map<String,Double> newresulttypemap = new HashMap<>(map);
				
				resultsofSimulation.add(newresulttypemap);
				
				
				iteration++;
	    	
		 	}
		
	    System.out.println(resultsofSimulation);
		}
		
		return resultsofSimulation;
		
	}
	
	public List<Map<String,Double>> comparator(List<Map<String,Double>> l1 ,List<Map<String,Double>> l2){
		
		Map<String,Double> ll =new HashMap<>();
		List<Map<String,Double>> comparationresults = new ArrayList<Map<String,Double>>();
		
		List<String> keys = new ArrayList<String>(l1.get(0).keySet());
		
		
		
		for(int i = 0; i < l1.size(); i++)
		{	
			List<Double> values1 = new ArrayList<Double>(l1.get(i).values());
			List<Double> values2 = new ArrayList<Double>(l2.get(i).values());
			System.out.println(values1);
			System.out.println(values2);
			for(int j = 0; j < l2.get(0).values().size(); j++)
			{
				ll.put(keys.get(j),values2.get(j)-values1.get(j));
				
			}
			Map<String,Double> lll =new HashMap<String,Double>(ll);
			comparationresults.add(lll);
		}
		
		/*int i=0;
		while(i<l1.size())
		{
			List<Double> v1 = new ArrayList<Double>(l1.get(i).values());
			List<Double> v2 = new ArrayList<Double>(l2.get(i).values());
			
			Map<String,Double> map = new HashMap<>();
			List<String> key = new ArrayList<String>(l1.get(0).keySet());
			System.out.println(i);
		for(int j = 0; j < v2.size(); j++)
		{
			
		
			System.out.println(v1);
			System.out.println(v2);
		
		
		System.out.println(j);
		
			map.put(key.get(j),v1.get(j)-v2.get(j));
	
		comparationresults.add(map);
		
		}
		i++;
			}*/
		return comparationresults;
		
		
		
	}

}
