package com.Vermeg.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Vermeg.entities.SimulationParams;

public interface SimulationParametersRepository extends JpaRepository<SimulationParams, Long>{

	@Query("select c from SimulationParams c where c.id=:x")
	public SimulationParams findbyId(@Param("x")long mc);
	
}
