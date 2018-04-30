package com.Vermeg.services;

import java.util.Map;

import com.Vermeg.entities.ComputedDataConfig;

public interface IExtractionDataService {

	
	public double GetNumericData(int row ,int column);
	public double GetIteration();
	public Map<String,Double> GetData();
	public ComputedDataConfig initialComputedDataConfig();
}
