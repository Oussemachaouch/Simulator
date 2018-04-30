package com.Vermeg.services;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ConfigPropertyExtractor{
	
	
	
	public String getPathValue() {
				
		String result ="";
		Properties prop = new Properties();
		String propFileName="excel.properties";
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		
		if (inputStream != null)
		{
			try {
				prop.load(inputStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			result = prop.getProperty("Path");
			
		} else {
			System.out.println("Path not found");
		}
	
		return result;
	
	}
	
	
	public int getSheetNumberValue() {
			
			int port=0;
			Properties prop = new Properties();
			String propFileName="Excel.properties";
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			
			if (inputStream != null)
			{
				try {
					prop.load(inputStream);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String result = prop.getProperty("SheetNumber");
				port = Integer.parseInt(prop.getProperty("SheetNumber"));
			} else {
				System.out.println("SheetNumber not found");
			}
		
	return port;
	} 


}
