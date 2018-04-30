package com.Vermeg.entities;


import java.util.HashMap;
import java.util.Map;


public class ComputedDataConfig {

	
	private Map<String,Double> ResultTypeMap =new HashMap<String,Double>(); 
	private double Iterator;
	
	
	public ComputedDataConfig(Map<String, Double> resultTypeMap, double iterator) {
		super();
		ResultTypeMap = resultTypeMap;
		Iterator = iterator;
	}

	public ComputedDataConfig() {
		super();
	}
	
	public Map<String, Double> getResultTypeMap() {
		return ResultTypeMap;
	}
	public void setResultTypeMap(Map<String, Double> resultTypeMap) {
		ResultTypeMap = resultTypeMap;
	}
	public double getIterator() {
		return Iterator;
	}
	public void setIterator(double iterator) {
		Iterator = iterator;
	}
	
	
	
}
