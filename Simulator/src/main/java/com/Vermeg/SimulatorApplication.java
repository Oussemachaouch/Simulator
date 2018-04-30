package com.Vermeg;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.Vermeg.dao.SimulationParametersRepository;
import com.Vermeg.simulator.Simulator;

@SpringBootApplication
@ComponentScan
public class SimulatorApplication implements CommandLineRunner{
	@Autowired
	private SimulationParametersRepository SimulationParametersRepository;
	
	@Autowired
	private Simulator simulator;
	
	public static void main(String[] args) throws IOException{
		SpringApplication.run(SimulatorApplication.class, args);
		
	//	SimulationParams simulationparameters = new SimulationParams(9,0.005,0.015,0.06,0);
		
	//	ExtractionDataService extraction = new ExtractionDataService();
			
	//		Simulator simulator = new Simulator();
			
	//		simulator.simulator(extraction.initialComputedDataConfig(),simulationparameters);
		
	}
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		//ExtractionDataService extraction = new ExtractionDataService();
		//simulator.simulator(extraction.initialComputedDataConfig(),new SimulationParams(9,0.005,0.015,0.06,0));
		//SimulationParametersRepository.save(new SimulationParams(9,0.005,0.015,0.06,0));
	}
}
