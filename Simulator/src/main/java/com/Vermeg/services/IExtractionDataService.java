package com.Vermeg.services;

import java.util.Date;
import java.util.Map;
import com.Vermeg.entities.ComputedDataConfig;
import com.Vermeg.entities.SimulationParameters;

public interface IExtractionDataService {

	
	public double getNumericData(int row ,int column);
	public double getInputNumericData(int row ,int column);
	public double getIteration();
	public Map<String,Double> getData();
	public ComputedDataConfig initialComputedDataConfig();
	//public SimulationParameters getInput();
	public Date getInputDateData(int row ,int column);
	public String getInputStringData(int row ,int column);
}
