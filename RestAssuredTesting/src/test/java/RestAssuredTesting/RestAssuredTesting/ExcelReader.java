package RestAssuredTesting.RestAssuredTesting;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;


import java.util.ArrayList;

//the code for this class is same as dataDrivenTest but it got converted to a method
//it only takes in name of a testCaseName and returns the myArray	
// it is more recent than dataDrivenMethod

//logic of rahul is below
//Identify Testcases coloum by scanning the entire 1st row
//once coloumn is identified then scan entire testcase coloum to identify purcjhase testcase row
//after you grab purchase testcase row = pull all the data of that row and feed into test
public class ExcelReader {

	//public  ArrayList<String> getData(String testCaseName) throws IOException {
	@Test
	public ArrayList<String> excelReader() throws IOException {
		
		//create an array that stores the final cells
		ArrayList<String> myArray = new ArrayList<String>();
				
		//define file path
		System.out.println(System.getProperty("user.dir"));
		
		
		//import file - change file names with \ to /
		FileInputStream myFile = new FileInputStream("C:/Selenium/RestAssuredTesting/TestFile.xlsx");

		//change imported file to excel type
		XSSFWorkbook myExcelFile = new XSSFWorkbook("myFile");
		
		//grab number of sheets to get desired one
		int sheets = myExcelFile.getNumberOfSheets();
		
		//create for loop that checks matching sheet names
		for (int i=0; i<sheets; i++)
		{
			String sheetNames = myExcelFile.getSheetName(i);
			
			//if name matches, store it as a worksheet
			if (sheetNames.equalsIgnoreCase("TestSheet"))
			{
				XSSFSheet mySheet = myExcelFile.getSheetAt(i);
				
				//after sheet is grabbed, iterator through rows to find column 
				java.util.Iterator<Row> myRows = mySheet.iterator();
	
				//get first row to establish column
				Row firstRow = myRows.next();
				
				//iterator through first row cells to define a desired column
				java.util.Iterator<Cell> myCells = firstRow.cellIterator();
				
				//logic and variables to find column name
				int k = 0;
				int columnNumber= 0;
				while(myCells.hasNext())
				{
					//start iterating the cells
					Cell eachCell = myCells.next();
					if(eachCell.getStringCellValue().equalsIgnoreCase("Desired Column"))
							{
							
							//assign a column number
							k = columnNumber;
												
							}
						k++;
				} 
								
				//just to test if columns are selected as exp.
				System.out.println(columnNumber);
				
				//more logic to get all cells of a row containing the column name
				//iterate through rows 
				while(myRows.hasNext())
				{
					Row rowList = myRows.next();
					
					//below is to collect data of a row based on a given column name(col number is fixed)
					//key point of this whole code is to get data using this exact cell below
					if ( rowList.getCell(columnNumber).getStringCellValue().equalsIgnoreCase("testCaseName"))
					{
						
					//iterate through cells to get all the values at that row
					java.util.Iterator<Cell> myCells2 =	rowList.cellIterator();
					while(myCells2.hasNext())
					{
						
						//store the cell values into the above array
						//sometimes input can be numeric instead. so convert to string
						Cell cellVal = myCells2.next();
						
						//if cellVal is string, simply add to array. else convert to string first
						if(cellVal.getCellTypeEnum() == CellType.STRING)
						{
							
							myArray.add(myCells2.next().getStringCellValue())

						}
						else
						{
							//convert to string and add to array 
							myArray.add(NumberToTextConverter.toText(cellVal.getNumericCellValue()));
						}
												
						//System.out.println(myCells2.next().getStringCellValue());
						
					}
						
					}
						
				}
				

		}

	}
		//add return statement after while loop is done
		return myArray;
		
		
}
}


