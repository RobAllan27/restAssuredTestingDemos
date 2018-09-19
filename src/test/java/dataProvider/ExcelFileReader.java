package dataProvider;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileNotFoundException;

import java.io.FileOutputStream;

import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;

import org.apache.poi.xssf.usermodel.XSSFRow;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileReader {

	private static XSSFSheet ExcelWSheet;

	private static XSSFWorkbook ExcelWBook;

	private static XSSFCell Cell;

	private static XSSFRow Row;

//This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method

	/*
public static void setExcelFileSheet(String Path,String SheetName) throws Exception {

	   try {

			// Open the Excel file

			FileInputStream ExcelFile = new FileInputStream(Path);

			// Access the required test data sheet

			ExcelWBook = new XSSFWorkbook(ExcelFile);

			ExcelWSheet = ExcelWBook.getSheet(SheetName);

			} catch (Exception e){

				throw (e);

			}
	}

*/

public static Object[][] getTableArray(String inFilePath, String SheetName)    throws Exception

{   

   String[][] tabArray = null;
   try{
	   File file = new File(inFilePath);
	   
	   FileInputStream ExcelFile = new FileInputStream(file);

	   // Access the required test data sheet

	   ExcelWBook = new XSSFWorkbook(ExcelFile);

	   ExcelWSheet = ExcelWBook.getSheet(SheetName);

	   //int startCol = 1;

	   int numberOfHeaderRow = 1;
	   
	   int totalRowsofData = getLastRowUsed() - numberOfHeaderRow + 1 ;

	   // rows counted 0 onwards hence add the extra 1 last rownum of 3 means there are 4 rows 
	   
	   int totalColsofData = getLastColUsed();

	   tabArray=new String[totalRowsofData][totalColsofData];

	   for (int rowj=numberOfHeaderRow; rowj<=totalRowsofData;rowj++)   
		   {
		   for (int colj=0;colj<totalColsofData;colj++)  
			   {
			   tabArray[rowj - numberOfHeaderRow][colj]=getCellData(rowj,colj);

			   // System.out.println(tabArray[rowj-numberOfHeaderRow][colj]);
			   }
		   }
	}

	catch (FileNotFoundException e)

	{

		System.out.println("Could not read the Excel sheet");

		e.printStackTrace();

	}

	catch (IOException e)

	{

		System.out.println("Could not read the Excel sheet");

		e.printStackTrace();

	}

	return(tabArray);

}

//This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num

private static String getCellData(int RowNum, int ColNum) throws Exception{

   try{

	  Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

	  DataFormatter df = new DataFormatter();
	  
	  String CellData  = df.formatCellValue(Cell);
	  
	  return CellData;
	  
	  }catch (Exception e){

		return"";

		}

	}
/*
public static String getTestCaseName(String sTestCase)throws Exception{

	String value = sTestCase;

	try{

		int posi = value.indexOf("@");

		value = value.substring(0, posi);

		posi = value.lastIndexOf(".");	

		value = value.substring(posi + 1);

		return value;

			}catch (Exception e){

		throw (e);

				}

	}
	*/

/*
public static int getRowContains(String sTestCaseName, int colNum) throws Exception{

	int i;

	try {

		int rowCount = ExcelUtils.getRowUsed();

		for ( i=0 ; i<rowCount; i++){

			if  (ExcelUtils.getCellData(i,colNum).equalsIgnoreCase(sTestCaseName)){

				break;

			}

		}

		return i;

			}catch (Exception e){

		throw(e);

		}

	}
*/

private static int getLastRowUsed() throws Exception {

		try{

			int RowCount = ExcelWSheet.getLastRowNum();

			return RowCount;

		}catch (Exception e){

			System.out.println(e.getMessage());

			throw (e);
		}

	}

private static int getLastColUsed() throws Exception {
// We will simply count off the first row here
	try{

		int ColCount = ExcelWSheet.getRow(0).getLastCellNum();

		return ColCount;

	}catch (Exception e){

		System.out.println(e.getMessage());

		throw (e);
	}

}

}
