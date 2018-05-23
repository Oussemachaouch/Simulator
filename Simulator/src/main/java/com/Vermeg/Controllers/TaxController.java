package com.Vermeg.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.Vermeg.dao.TaxRepository;
import com.Vermeg.entities.SimulationParams;
import com.Vermeg.entities.Tax;


@RestController
@CrossOrigin("*")
public class TaxController {
	
	@Autowired
	private TaxRepository taxrepository;
	

	
	@RequestMapping(value="/tax",method=RequestMethod.GET)
	public List<Tax> getTax(){
		
		return taxrepository.findAll();
	}
	@RequestMapping(value="/tax/{id}",method=RequestMethod.GET)
	public Optional<Tax> GetOne(@PathVariable Long id){
		
		return taxrepository.findById(id);
	}
	
	@RequestMapping(value="/taxvalue",method=RequestMethod.GET)
	public Double gettaxvalues(){
		
		return taxrepository.findtax();
	}
	
	@RequestMapping(value="/tax",method=RequestMethod.POST)
	public Tax Save(@RequestBody Tax t){
		
		return taxrepository.save(t);
	}
	
	@RequestMapping(value="/taxe/{id}",method=RequestMethod.PUT) 
	public Tax update(@PathVariable Long id,  @RequestBody Tax c){
	    c.setId(id);
	    return 	taxrepository.save(c);
	}
	
	@RequestMapping(value="/tax/{id}",method=RequestMethod.DELETE)
	public boolean delete(@PathVariable Long id){
		
		taxrepository.deleteById(id);
		return true;
	}
	


}
