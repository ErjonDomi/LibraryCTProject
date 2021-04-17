package com.LibraryCT.utilities;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {
    public static String path;
    public static FileInputStream fis = null;
    private static XSSFWorkbook workbook = null;
    private static XSSFSheet sheet = null;

    public static void getPath(String pathToExcelFile , String sheetName) {
        path = pathToExcelFile;
        try {
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Returns the row count in a given sheet name
    public static int getRowCount(String sheetName) {
        int index = workbook.getSheetIndex(sheetName);
        if (index == -1)
            return 0;
        else {
            sheet = workbook.getSheetAt(index);
            int number = sheet.getLastRowNum();
            return number;
        }
    }

    //Reading data from excel to Map
    //Returns a map for a single row where column name is the key and row is the value
    public static Map<String , String> getMap(String path , int rowNum , String sheetName) {
        getPath(path , sheetName);
        XSSFRow xssfRow = sheet.getRow(rowNum);
        Map<String , String> map = new LinkedHashMap<>();

        int cellCount = xssfRow.getLastCellNum();
        for (int i = 0; i < cellCount; i++) {
            XSSFCell cellKey = sheet.getRow(0).getCell(i);
            cellKey.setCellType(CellType.STRING);
            String key = cellKey.getStringCellValue();

            XSSFCell cellValue = sheet.getRow(rowNum).getCell(i);
            cellValue.setCellType(CellType.STRING);
            String value = cellValue.getStringCellValue();

            map.put(key , value);
        }
        return map;
    }

     //Reading data from excel to a List Of Map
    //Return a list of map  for all the rows that we will read from excel file
    public static List<Map<String, String>> listOfMaps(String path , String sheetName) {
        getPath(path , sheetName);
        List<Map<String , String>> listOfMap = new ArrayList<>();
        int rowCount = getRowCount(sheetName);
        for (int rowNum = 1; rowNum < rowCount; rowNum++) {
            listOfMap.add(getMap(path , rowNum , sheetName));
        }
        return listOfMap;
    }

}