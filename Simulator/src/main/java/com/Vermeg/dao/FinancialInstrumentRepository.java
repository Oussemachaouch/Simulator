package com.Vermeg.dao;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Vermeg.entities.FinancialInstrument;
import com.Vermeg.entities.SimulationParameters;


public interface FinancialInstrumentRepository extends JpaRepository<FinancialInstrument, Long>{
	@Modifying
	 @Transactional
	@Query("update FinancialInstrument SET name=:name where id=:id")
	public void update(@Param("id") Long id,@Param("name") String name);
	
	@Query("select m.name from FinancialInstrument m")
	public List<FinancialInstrument> findname();
}
