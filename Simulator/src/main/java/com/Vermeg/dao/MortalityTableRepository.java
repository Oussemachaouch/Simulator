package com.Vermeg.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Vermeg.entities.MortalityTable;





public interface MortalityTableRepository extends JpaRepository<MortalityTable, Long>{

	@Query("select m from MortalityTable m where m.mortalitytableage.name=:x")
	public List<MortalityTable> findbyId(@Param("x")String mc);
	
	@Query("select m.mortalitytableage.name from MortalityTable m")
	public Set<MortalityTable> find();
}
