package com.Vermeg.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Vermeg.entities.Tax;

public interface TaxRepository extends JpaRepository<Tax, Long>{
	
	@Query("select sum(m.taxvalue) from Tax m")
	public Double findtax();
	@Transactional
	@Modifying
	@Query("UPDATE Tax SET taxname = :taxname, taxvalue = :taxvalue  WHERE id = :id")
	public void update(@Param("taxname") String taxname, @Param("taxvalue") String taxvalue, @Param("id") Long id);
	
	
}
