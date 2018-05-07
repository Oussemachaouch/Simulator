package com.Vermeg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Vermeg.entities.SimulationParams;
import com.Vermeg.entities.Tax;

public interface TaxRepository extends JpaRepository<Tax, Long>{

}
