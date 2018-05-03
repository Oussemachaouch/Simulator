package com.Vermeg.Controllers;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Vermeg.dao.SimulationParametersRepository;

import com.Vermeg.services.ExtractionDataService;
import com.Vermeg.simulator.Simulator;

@RestController
@CrossOrigin("*")
public class SimulationController {

	@Autowired
	private Simulator simulator;
	@Autowired
	private SimulationParametersRepository simulationParametersRepository;
	
	
	@RequestMapping(value="/simulation",method=RequestMethod.GET)
	public List<Map<String,Double>> Simulation(@RequestParam(name="mc")long mc){
		ExtractionDataService extraction = new ExtractionDataService();
		return simulator.simulator(extraction.initialComputedDataConfig(),simulationParametersRepository.findbyId(mc));
	}

}
