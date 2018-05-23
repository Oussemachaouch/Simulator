package com.Vermeg;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.Vermeg.dao.SimulationParamRepository;
import com.Vermeg.entities.FinancialInstrument;
import com.Vermeg.entities.SimulationParameters;
import com.Vermeg.entities.SimulationParams;
import com.Vermeg.services.ExtractionDataService;
import com.Vermeg.services.StorageService;
import com.Vermeg.simulator.Simulator;

@SpringBootApplication
@ComponentScan
public class SimulatorApplication implements CommandLineRunner{
	@Autowired
	private SimulationParamRepository SimulationParametersRepository;
	
	@Resource
	StorageService storageService;
	
	@Autowired
	private Simulator simulator;
	
	public static void main(String[] args) throws IOException{
		SpringApplication.run(SimulatorApplication.class, args);
		
		//SimulationParams simulationparameters = new SimulationParams(9,0.005,0.015,0.06,0);
		
	//	ExtractionDataService extraction = new ExtractionDataService();
		//System.out.println(extraction.getInput().getCommission());
		//System.out.println(extraction.getInput().getDuration());
		//System.out.println(extraction.getInput().getDatedenaissance());
		//System.out.println(extraction.getInput().getGender());
		//	Simulator simulator = new Simulator();
	
	//		simulator.simulator(extraction.initialComputedDataConfig(),simulationparameters);
	//	test test=new test();
	//	test.tester();
	}
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		//ExtractionDataService extraction = new ExtractionDataService();
		//FinancialInstrument fin=new FinancialInstrument("ouss",1.0,1.0,1.0,1.0,1.0,1.0);
		//Date dt=new Date();
		//simulator.simulator1(extraction.initialComputedDataConfig(),new SimulationParameters(9,015,dt,1.2,fin));
	//	simulator.simulator(extraction.initialComputedDataConfig(),new SimulationParams(9,0.005,0.015,0.06,0));
		//SimulationParametersRepository.save(new SimulationParams(9,0.005,0.015,0.06,0));
		
		storageService.deleteAll();
		storageService.init();
	}
}
