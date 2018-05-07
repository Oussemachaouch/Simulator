package com.Vermeg.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.Vermeg.dao.ManagementFeeRepository;
import com.Vermeg.entities.ManagementFee;

@RestController
@CrossOrigin("*")
public class ManagementFeeController {

	@Autowired
	private ManagementFeeRepository managementfeerepository;
	
	@RequestMapping(value="/managementfee",method=RequestMethod.GET)
	public List<ManagementFee> getFees(){
		
		return managementfeerepository.findAll();
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
}
