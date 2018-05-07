package com.Vermeg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Vermeg.entities.FinancialInstrument;


public interface FinancialInstrumentRepository extends JpaRepository<FinancialInstrument, Long>{

}
