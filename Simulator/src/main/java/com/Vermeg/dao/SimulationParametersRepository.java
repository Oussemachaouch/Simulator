package com.Vermeg.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Vermeg.entities.MortalityTable;
import com.Vermeg.entities.SimulationParameters;
import com.Vermeg.entities.SimulationParams;


public interface SimulationParametersRepository extends JpaRepository<SimulationParameters, Long> {

	@Query("select c from SimulationParameters c where c.id=:x")
	public SimulationParameters findbyId(@Param("x")long mc);
}
