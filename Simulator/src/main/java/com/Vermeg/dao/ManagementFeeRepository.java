package com.Vermeg.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.Vermeg.entities.ManagementFee;


public interface ManagementFeeRepository extends JpaRepository<ManagementFee, Long>{
	
	@Query("select m.feevalue from ManagementFee m where date <= CURRENT_DATE ORDER BY date DESC")
	public List<ManagementFee> find();
}
