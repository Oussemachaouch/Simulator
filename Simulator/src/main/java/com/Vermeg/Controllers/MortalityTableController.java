package com.Vermeg.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.Vermeg.dao.MortalityTableRepository;

import com.Vermeg.entities.MortalityTable;


@RestController
@CrossOrigin("*")
public class MortalityTableController {

	@Autowired
	private MortalityTableRepository mortalitytableRepository;
	
	@RequestMapping(value="/mortalitytable/{name}",method=RequestMethod.GET)
	public List<MortalityTable> GetOne(@PathVariable String name){
		
		 
		
		 return mortalitytableRepository.findbyId(name);
	}
	@RequestMapping(value="/mortalitytable",method=RequestMethod.GET)
	public List<MortalityTable> getFees(){
		
		return mortalitytableRepository.findAll();
	}
	@RequestMapping(value="/name",method=RequestMethod.GET)
	public Set<MortalityTable> get(){
		
		return mortalitytableRepository.find();
	}
}
