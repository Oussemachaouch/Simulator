package com.Vermeg.Controllers;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Vermeg.dao.FinancialInstrumentRepository;
import com.Vermeg.dao.SimulationParametersRepository;
import com.Vermeg.entities.FinancialInstrument;
import com.Vermeg.entities.MortalityTable;
import com.Vermeg.entities.SimulationParameters;


@RestController
@CrossOrigin("*")
public class SimulationParametersController {
	@Autowired
	private SimulationParametersRepository SimulationParametersRepository;
	@Autowired
	private FinancialInstrumentRepository financialinstrumentRepository ;
	
	
	@RequestMapping(value="/param",method=RequestMethod.POST)
	public SimulationParameters Save(@RequestBody SimulationParameters s){
		
		return SimulationParametersRepository.save(s);
	}
	@RequestMapping(value="/param",method=RequestMethod.GET)
	public List<SimulationParameters> get(){
		
		return SimulationParametersRepository.findAll();
	}
	@RequestMapping(value="/parame/{id}", method=RequestMethod.POST)
	public SimulationParameters signUp(@RequestBody SimulationParameters c, @PathVariable("id") long financialinstrument_id )
	{
		
			
			Optional<FinancialInstrument> o = financialinstrumentRepository.findById(financialinstrument_id);
			
			System.out.println(o.get());
			FinancialInstrument oo=o.get();
			c.setFinancialinstrument(oo);
			
			 SimulationParametersRepository.saveAndFlush(c);
			 return c;
}
}