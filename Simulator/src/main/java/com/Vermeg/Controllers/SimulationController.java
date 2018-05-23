package com.Vermeg.Controllers;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.Vermeg.dao.SimulationParamRepository;
import com.Vermeg.dao.SimulationParametersRepository;
import com.Vermeg.dao.TaxRepository;
import com.Vermeg.services.ExtractionDataService;
import com.Vermeg.simulator.Simulator;

@RestController
@CrossOrigin("*")
public class SimulationController {

	@Autowired
	private Simulator simulator;
	@Autowired
	private SimulationParamRepository simulationParametersRepository;
	@Autowired
	private SimulationParametersRepository simulationParameterssRepository;
	@Autowired
	private TaxRepository taxRepository;
	
	@RequestMapping(value="/simulation",method=RequestMethod.GET)
	public List<Map<String,Double>> Simulation(@RequestParam(name="mc")long mc){
		ExtractionDataService extraction = new ExtractionDataService();
		
		return simulator.simulator(extraction.initialComputedDataConfig(),simulationParametersRepository.findbyId(mc));
	}
	@RequestMapping(value="/simulation1",method=RequestMethod.GET)
	public List<Map<String,Double>> Simulation1(@RequestParam(name="mc")long mc){
		ExtractionDataService extraction = new ExtractionDataService();
		System.out.println(simulationParameterssRepository.findbyId(mc));
		return simulator.simulatorMinResults(extraction.initialComputedDataConfig(),simulationParameterssRepository.findbyId(mc));
	}
	@RequestMapping(value="/simulation2",method=RequestMethod.GET)
	public List<Map<String,Double>> Simulation2(@RequestParam(name="mc")long mc){
		ExtractionDataService extraction = new ExtractionDataService();
		System.out.println(simulationParameterssRepository.findbyId(mc));
		return simulator.simulatorAvgResults(extraction.initialComputedDataConfig(),simulationParameterssRepository.findbyId(mc));
	}
	@RequestMapping(value="/simulation3",method=RequestMethod.GET)
	public List<Map<String,Double>> Simulation3(@RequestParam(name="mc")long mc){
		ExtractionDataService extraction = new ExtractionDataService();
		System.out.println(simulationParameterssRepository.findbyId(mc));
		return simulator.simulatorMaxResults(extraction.initialComputedDataConfig(),simulationParameterssRepository.findbyId(mc));
	}
	@RequestMapping(value="/simulation4",method=RequestMethod.GET)
	public List<Map<String,Double>> Simulation4(@RequestParam(name="mc")long mc){
		ExtractionDataService extraction = new ExtractionDataService();
		System.out.println(simulationParameterssRepository);
		return simulator.comparator(simulator.simulatorMaxResults(extraction.initialComputedDataConfig(),simulationParameterssRepository.findbyId(mc)),simulator.simulatorMinResults(extraction.initialComputedDataConfig(),simulationParameterssRepository.findbyId(mc)));
	}

}
