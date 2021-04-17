package com.LibraryCT.utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DB_Utility{

    private static Connection conn ;
    private static Statement stmnt ;
    private static ResultSet rs ;

    /*
     * a static method to create connection
     * with valid url and username password
     * */
    public static void createConnection(){

        String connectionStr = "jdbc:mysql://18.233.97.71:3306/library1";
        String username = "library1_client" ;
        String password = "WVF4NdGXCKHeE6VQ" ;

        try {
            conn = DriverManager.getConnection(connectionStr, username, password) ;
            System.out.println("CONNECTION SUCCESSFUL ");
        } catch (SQLException e) {
            System.out.println("Connection has failed "+ e.getMessage() );
        }

    }

    /**
     * a static method to get the ResultSet object
     * with valid connection by executing query
     * @param query
     * @return ResultSet object that contain the result just in cases needed outside the class
     */
    public static ResultSet runQuery(String query) {

        try {
            stmnt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmnt.executeQuery(query) ;
        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING RESULTSET " + e.getMessage() );
        }
        return rs ;
    }

    /**
     * cleaning up the resources
     */
    public static void destroy(){


            try {
                if(rs!=null) {
                    rs.close();
                }
                if(stmnt!=null) {
                    stmnt.close();
                }
                if(conn!=null) {
                    conn.close();
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

    }
    /**
     * Get the row count of the resultSet
     * @return the row number of the resultset given
     */
    public static int getRowCount(){

        int rowCount = 0 ;

        try {
            rs.last();
            rowCount = rs.getRow() ;
            rs.beforeFirst();

        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING ROW COUNT " + e.getMessage() );
        }

        return rowCount ;

    }

    /**
     * a method to get the column count of the current ResultSet
     * @return column count
     * */
    public static int getColumnCNT() {

        int colCount = 0 ;

        try {
            ResultSetMetaData rsmd = rs.getMetaData() ;
            colCount = rsmd.getColumnCount();
        } catch (SQLException e) {
            System.out.println("ERROR WHILE COUNTING THE COLUMNS" + e.getMessage());
        }
        return colCount;

    }

    /**
     * Create a method that return all column names as list
     * @param
     * @return List<String>
     */
    public static List<String> getColumnNames(){

        List<String> colNamesList = new ArrayList<>();

        try {
            ResultSetMetaData rsmd = rs.getMetaData() ;
            for (int colNum = 1; colNum <= rsmd.getColumnCount() ; colNum++) {

                String colName = rsmd.getColumnLabel(colNum);
                colNamesList.add( colName ) ;

            }
        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING COLUMN NAMES "+ e.getMessage());
        }
        return colNamesList ;
    }


    // getting the entire row as List<String>

    /**
     *
     * @param rowNum the row number you want the list from
     * @return List of String that contains the row data
     */
    public static List<String> getRowDataAsList(int rowNum){

        List<String> rowDataList = new ArrayList<>();

        try {
            rs.absolute(rowNum) ;

            for (int colNum = 1; colNum <= getColumnCNT() ; colNum++) {

                String cellValue = rs.getString(colNum);
                rowDataList.add(cellValue) ;

            }
            rs.beforeFirst();

        } catch (SQLException e) {
            System.out.println("ERROR WHILE getRowDataAsList " + e.getMessage());
        }
        return rowDataList ;

    }

    /**
     * Getting single column cell value at certain row
     * @param rowNum    row number we want to get data from
     * @param columnIndex  column index we want to get the data from
     * @return the data in String
     */
    public static String getColumnDataAtRow (int rowNum , int columnIndex){

        String result = "";

        try {
            rs.absolute(rowNum) ;
            result = rs.getString(columnIndex) ;
            rs.beforeFirst();

        } catch (SQLException e) {
            System.out.println("ERROR WHILE getColumnDataAtRow " + e.getMessage() );
        }

        return result ;

    }

    /**
     *
     * @param rowNum
     * @param columnName
     * @return the data at that row with that column name
     */
    public static String getColumnDataAtRow (int rowNum , String columnName){

        String result = "";
        try {
            rs.absolute(rowNum) ;
            result = rs.getString(columnName) ;

            rs.beforeFirst();

        } catch (SQLException throwables) {
            System.out.println("ERROR WHILE getColumnDataAtRow ");
        }
        return result ;

    }

    /**
     *
     * @param columnIndex the column you want to get a list out of
     * @return List of String that contains entire column data from 1st row to last row
     */
    public static List<String> getColumnDataAsList(int columnIndex){

        List<String> columnDataLst = new ArrayList<>();
        try{

            rs.beforeFirst();

            while(rs.next() ) {

                String cellValue = rs.getString(columnIndex);
                columnDataLst.add(cellValue);

            }
            rs.beforeFirst();
        }catch(SQLException e){
            System.out.println("ERROR WHILE getColumnDataAsList "+ e.getMessage() );
        }
        return columnDataLst ;


    }
    /**
     *
     * @param columnName the column you want to get a list out of
     * @return List of String that contains entire column data from the column name specified
     */
    public static List<String> getColumnDataAsList(String columnName){
        List<String> columnDataLst = new ArrayList<>();
        try{

            rs.beforeFirst();

            while(rs.next() ) {

                String cellValue = rs.getString(columnName);
                columnDataLst.add(cellValue);

            }
            rs.beforeFirst();

        }catch(SQLException e){
            System.out.println("ERROR WHILE getColumnDataAsList "+ e.getMessage() );
        }
        return columnDataLst ;


    }

    /*
     * a method to display all the data in the result set
     *
     * */
    public static void displayAllData(){

        try {
            rs.beforeFirst();

            while (rs.next() ){

                for (int colNum = 1; colNum <= getColumnCNT() ; colNum++) {

                    System.out.print(  rs.getString(colNum) + "\t" );

                }
                System.out.println();

            }

        } catch (SQLException e) {
            System.out.println("ERROR WHILE DISPLAYING ALL DATA " + e.getMessage() );
        }


    }

    /**
     * We want to store certain row data as a Map<String,String>
     * * For example :
     *  give me number 3rd row  --->> Map<String,String>   {region_id : 3 , region_name : Asia}
     * @param rowNum
     * @return Map object that contains column names as a key and cell as value
     */
    public static  Map<String,String> getRowMap (int rowNum) {

        Map<String,String> rowMap = new LinkedHashMap<>();

        try {
            rs.absolute(rowNum);
            ResultSetMetaData rsmd = rs.getMetaData();

            for (int colNum = 1; colNum <= getColumnCNT() ; colNum++) {

                String colName      = rsmd.getColumnLabel(colNum) ;
                String cellValue    = rs.getString(colNum) ;
                rowMap.put(colName, cellValue) ;

            }
            rs.beforeFirst();
        } catch (SQLException e) {
                System.out.println("ERROR AT ROW MAP FUNCTION " + e.getMessage() );
        }
        return rowMap ;

    }

    /**
     * Getting entire resultset data as List of Map object
     *
     * @return List< Map<String,String> > that represent all row data
     */
    public static List< Map<String,String> > getAllDataAsListOfMap(){

        List< Map<String,String> > rowMapList = new ArrayList<>();

        for (int rowNum = 1; rowNum <= getRowCount() ; rowNum++) {

            rowMapList.add(   getRowMap(rowNum)   ) ;

        }

        return rowMapList ;


    }

    /**
     *
     * @return Getting the first row first column data
     *
     */
    public static String getFirstData(){

        return  getColumnDataAtRow(1,1) ;

    }



}
