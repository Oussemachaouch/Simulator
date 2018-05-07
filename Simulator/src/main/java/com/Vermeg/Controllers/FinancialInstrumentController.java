package com.Vermeg.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Vermeg.dao.FinancialInstrumentRepository;
import com.Vermeg.entities.FinancialInstrument;


@RestController
@CrossOrigin("*")
public class FinancialInstrumentController {
	
	@Autowired
	private FinancialInstrumentRepository financialinstrumentrepository;
	
	@RequestMapping(value="/financialinstrument",method=RequestMethod.GET)
	public List<FinancialInstrument> GetConfig(){
		
		return financialinstrumentrepository.findAll();
	}
	
	@RequestMapping(value="/financialinstrument",method=RequestMethod.POST)
	public FinancialInstrument Save(@RequestBody FinancialInstrument f){
		
		return financialinstrumentrepository.save(f);
	}
	
	@RequestMapping(value="/financialinstrument/{id}",method=RequestMethod.DELETE)
	public boolean delete(@PathVariable Long id){
		
		financialinstrumentrepository.deleteById(id);
		return true;
	}
}
