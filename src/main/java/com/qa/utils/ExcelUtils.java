package com.qa.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	private static XSSFSheet ExcelWSheet;

	private static XSSFWorkbook ExcelWBook;

	private static XSSFCell Cell;

	private static XSSFRow Row;

	public XSSFSheet setExcel(String filePath, String sheetName) throws IOException {

		try {
			FileInputStream file = new FileInputStream(filePath);

			ExcelWBook = new XSSFWorkbook(file);
			ExcelWSheet = ExcelWBook.getSheet(sheetName);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ExcelWSheet;
	}

	public String readExcel(int row, int col) {
		try {
			String cellData;
			Cell = ExcelWSheet.getRow(row).getCell(col);
			if (Cell.getCellType() == CellType.STRING) {
				cellData = Cell.getStringCellValue();
			} else {
				int cellDataInt = (int) Cell.getNumericCellValue();
				cellData = String.valueOf(cellDataInt);
			}
			return cellData;
		} catch (NullPointerException e) {
			return "";
		}
	}

	public void writeExcel(int row, int col, String result) {
		try {
			Row = ExcelWSheet.getRow(row);
			Cell = Row.getCell(col, MissingCellPolicy.RETURN_BLANK_AS_NULL);
			if (Cell == null) {

				Cell = Row.createCell(col);
				Cell.setCellValue(result);

			} else {
				Cell.setCellValue(result);
			}
			FileOutputStream fileOut = new FileOutputStream(Constants.FILE_PATH + Constants.FILE_NAME);
			ExcelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	public HashMap<String, String> getdata(String testcaseName, String sheetname) throws IOException {
		String username = null;
		String password = null;
		String testcase;
		XSSFSheet excelWSheet = setExcel(Constants.FILE_PATH + Constants.FILE_NAME, sheetname);
		int rowCount = excelWSheet.getLastRowNum() - excelWSheet.getFirstRowNum();
		HashMap<String, String> credential = new HashMap<String, String>();
		for (int i = 1; i <= rowCount; i++) {
			testcase = readExcel(i, 0);

			if (testcaseName.equalsIgnoreCase(testcase)) {
				if (readExcel(i, 1)== null||readExcel(i, 2)==null) {
					credential.put("", "");	
				}
				credential.put(readExcel(i, 1), readExcel(i, 2));
				return credential;
			}


		}
		return credential;

	}

}
