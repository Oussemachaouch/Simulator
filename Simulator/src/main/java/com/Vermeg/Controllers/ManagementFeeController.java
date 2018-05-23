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


import com.Vermeg.dao.ManagementFeeRepository;
import com.Vermeg.entities.FinancialInstrument;
import com.Vermeg.entities.ManagementFee;
import com.Vermeg.entities.Tax;

@RestController
@CrossOrigin("*")
public class ManagementFeeController {

	@Autowired
	private ManagementFeeRepository managementfeerepository;
	
	@RequestMapping(value="/managementfee",method=RequestMethod.GET)
	public List<ManagementFee> getFees(){
		
		return managementfeerepository.findAll();
	}
	@RequestMapping(value="/last",method=RequestMethod.GET)
	public List<ManagementFee> getlastfeevalue(){
		System.out.println(managementfeerepository.find().get(managementfeerepository.find().size()-1));
		return managementfeerepository.find();
	}
	
	@RequestMapping(value="/managementfee",method=RequestMethod.POST)
	public ManagementFee Save(@RequestBody ManagementFee f){
		
		return managementfeerepository.save(f);
	}
	@RequestMapping(value="/managementfee/{id}",method=RequestMethod.DELETE)
	public boolean delete(@PathVariable Long id){
		
		managementfeerepository.deleteById(id);
		return true;
	}
	@RequestMapping(value="/managementfee/{id}",method=RequestMethod.GET)
	public Optional<ManagementFee> GetOne(@PathVariable Long id){
		
		return managementfeerepository.findById(id);
	}
	@RequestMapping(value="/managementfee/{id}",method=RequestMethod.PUT) 
	public ManagementFee update(@PathVariable Long id,  @RequestBody ManagementFee c){
	    c.setId(id);
	    return 	managementfeerepository.save(c);
	}
}
