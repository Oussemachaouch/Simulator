package com.Vermeg.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Vermeg.entities.SimulationParameters;
import com.Vermeg.enumeration.ResultType;

@Service
public class ProjectionDataService {

public void getInput(InputStream f){
	
	//List<Map<String,Double>> comparationresults = new ArrayList<Map<String,Double>>();
	
	try {
		BufferedReader reader = new BufferedReader(new InputStreamReader(f));
		StringBuilder out = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
		    out.append(line);
		}
		System.out.println(out.toString());   //Prints the string content read from input stream
		reader.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	
		
		
		//return comparationresults;
	}

public double getNumericData(int row ,int column,MultipartFile file) throws IOException{

	
	XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
	XSSFSheet worksheet = workbook.getSheetAt(0);
	double data =worksheet.getRow(row).getCell(column).getNumericCellValue();
	return data;
}


public List<Map<String,Double>> getData(MultipartFile file,SimulationParameters financialinstrument)
{
	Map<String,Double> ResultTypeMap =new HashMap<String,Double>();
	List<Map<String,Double>> listResultTypeMap = new ArrayList<Map<String,Double>>();
	
	try {
		XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
		XSSFSheet worksheet = workbook.getSheetAt(0);
		
		
	
		int j=1;
		while (j<=(financialinstrument.getDuration()*(12/financialinstrument.getPeriodicity())))
		{
			XSSFRow row; 
			XSSFCell cell;
			Iterator rows = worksheet.rowIterator();
			
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
						
						
					
						ResultTypeMap.put(cell.getStringCellValue(),getNumericData( cell.getRowIndex()+j, cell.getColumnIndex(),file) );
						
						
						
						}
				
				}
					
			}

		 }
		
		Map<String,Double> ResultTypeMap1 =new HashMap<String,Double>(ResultTypeMap);
		listResultTypeMap.add(ResultTypeMap1);
		System.out.println(listResultTypeMap);
		j++;
		System.out.println(j);
		
		
	}
		
	}catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return listResultTypeMap;
}
}
