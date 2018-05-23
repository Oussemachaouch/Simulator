package com.Vermeg.services;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import com.Vermeg.entities.ComputedDataConfig;
import com.Vermeg.entities.SimulationParameters;
import com.Vermeg.enumeration.ResultType;


@Service
public class ExtractionDataService implements IExtractionDataService{

	
	
	XSSFWorkbook wb;
	XSSFSheet sheet1;
	
	ConfigPropertyExtractor property = new ConfigPropertyExtractor();
	
	
 
	public  ExtractionDataService(){
    	try {
    		
    		
    		File src = new File(property.getPathValue());
    		FileInputStream fis=new FileInputStream(src);
    		wb = new XSSFWorkbook(fis);
    		
    		
    	}
    	 catch (Exception e) {
    		// TODO Auto-generated catch block
    		e.getMessage();
		
		}
	
}
	
	@Override
	public double getNumericData(int row ,int column){
	
		
		sheet1= wb.getSheetAt(property.getSheetNumberValue());
		double data =sheet1.getRow(row).getCell(column).getNumericCellValue();
		return data;
	}
	
	@Override
	public double getInputNumericData(int row ,int column){
	
		
		sheet1= wb.getSheetAt(property.getInputSheetNumberValue());
		double data =sheet1.getRow(row).getCell(column).getNumericCellValue();
		return data;
	}
	
	@Override
	public Date getInputDateData(int row ,int column){
	
		sheet1= wb.getSheetAt(property.getInputSheetNumberValue());
		Date data =sheet1.getRow(row).getCell(column).getDateCellValue();
		
		return data;
	}
	
	@Override
	public String getInputStringData(int row ,int column){
	
		sheet1= wb.getSheetAt(property.getInputSheetNumberValue());
		String data =sheet1.getRow(row).getCell(column).getStringCellValue();
		
		return data;
	}
	
	@Override
	public double getIteration()
	{
		
	
		sheet1= wb.getSheetAt(property.getSheetNumberValue());
		XSSFRow row; 
		XSSFCell cell;
		Iterator rows = sheet1.rowIterator();

		while (rows.hasNext())
		{
			row=(XSSFRow) rows.next();
			Iterator cells = row.cellIterator();
			
			while (cells.hasNext())
			{ 
				cell=(XSSFCell) cells.next();
		
				if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING && cell.getStringCellValue().equals("ITERATOR"))
				{
					int rowposition=cell.getRowIndex()+1;
					int colomnposition=cell.getColumnIndex();
					
					return getNumericData(rowposition,colomnposition);
				}
		
			}
			
		}
	
		return 0.0;
	}
	
	/*@Override
	public SimulationParameters getInput()
	{
		SimulationParameters simulationparameters = new SimulationParameters();
	
		sheet1= wb.getSheetAt(property.getInputSheetNumberValue());
		XSSFRow row; 
		XSSFCell cell;
		Iterator rows = sheet1.rowIterator();

		while (rows.hasNext())
		{
			row=(XSSFRow) rows.next();
			Iterator cells = row.cellIterator();
			
			while (cells.hasNext())
			{ 
				cell=(XSSFCell) cells.next();
		
				if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING && cell.getStringCellValue().equals("Commission"))
				{
					int rowposition=cell.getRowIndex();
					int colomnposition=cell.getColumnIndex()+1;
					
					
					 simulationparameters.setCommission(getInputNumericData(rowposition,colomnposition));
				}
				if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING && cell.getStringCellValue().equals("Duration"))
				{
					int rowposition=cell.getRowIndex();
					int colomnposition=cell.getColumnIndex()+1;
					
					
					 simulationparameters.setDuration(getInputNumericData(rowposition,colomnposition));
				}
				if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING && cell.getStringCellValue().equals("Date de naissance"))
				{
					int rowposition=cell.getRowIndex();
					int colomnposition=cell.getColumnIndex()+1;
					
					simulationparameters.setDatedenaissance(getInputDateData(rowposition, colomnposition));
				}
				
				if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING && cell.getStringCellValue().equals("Gender"))
				{
					int rowposition=cell.getRowIndex();
					int colomnposition=cell.getColumnIndex()+1;
					
					simulationparameters.setGender(getInputStringData(rowposition, colomnposition));
				}
				

			}
			
		}
	
		return simulationparameters;
	}
	*/
	
	@Override
	public Map<String,Double> getData()
	{
		Map<String,Double> ResultTypeMap =new HashMap<String,Double>();
		
		sheet1= wb.getSheetAt(property.getSheetNumberValue());
		
		XSSFRow row; 
		XSSFCell cell;
		Iterator rows = sheet1.rowIterator();
	    
		while (rows.hasNext())
		{
			row=(XSSFRow) rows.next();
			Iterator cells = row.cellIterator();
			
			while (cells.hasNext())
			{ 
				cell=(XSSFCell) cells.next();
				for (int i=0 ; i<=ResultType.values().length-1 ; i++)
				{		
					if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING && cell.getStringCellValue().equals(ResultType.values()[i].getDesc()))
						{
						
						ResultTypeMap.put(cell.getStringCellValue(),getNumericData( cell.getRowIndex(), cell.getColumnIndex()+1) );
					
						}
				}
				
			}

		 }

		return ResultTypeMap;
	}
	
	@Override
	public ComputedDataConfig initialComputedDataConfig() {
	
		ComputedDataConfig computeddataconfig = new ComputedDataConfig();
		computeddataconfig.setIterator(getIteration());
		computeddataconfig.setResultTypeMap(getData());
		return computeddataconfig;
	}
	}
       
			
		
		
		


