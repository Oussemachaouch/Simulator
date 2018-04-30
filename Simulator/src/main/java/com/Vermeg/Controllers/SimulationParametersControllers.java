package com.Vermeg.Controllers;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Vermeg.dao.SimulationParametersRepository;
import com.Vermeg.entities.SimulationParams;

@RestController
@CrossOrigin("*")
public class SimulationParametersControllers {
	
	@Autowired
	private SimulationParametersRepository SimulationParametersRepository;
	
	@RequestMapping(value="/parameters",method=RequestMethod.GET)
	public List<SimulationParams> GetConfig(){
		
		return SimulationParametersRepository.findAll();
	}
	@RequestMapping(value="/parameters/{id}",method=RequestMethod.GET)
	public Optional<SimulationParams> GetOne(@PathVariable Long id){
		
		return SimulationParametersRepository.findById(id);
	}
	
	@RequestMapping(value="/parameters",method=RequestMethod.POST)
	public SimulationParams Save(@RequestBody SimulationParams s){
		
		return SimulationParametersRepository.save(s);
	}
}
