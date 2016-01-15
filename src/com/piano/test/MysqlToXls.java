package com.piano.test;

import java.io.*;
import java.sql.*;
import org.apache.poi.hssf.usermodel.*;
import java.util.*;

public class MysqlToXls {

	private Connection connection = null;

	public MysqlToXls(String database, String user, String password)
			throws ClassNotFoundException, SQLException {

		// Create MySQL database connection
		Class.forName("com.mysql.jdbc.Driver");

		String url = "jdbc:mysql://10.1.40.41:3306/" + database +"?useUnicode=true&characterEncoding=utf8";
		connection = DriverManager.getConnection(url, user, password);
	}

	public void generateXls(String tablename, String filename)
			throws SQLException, FileNotFoundException, IOException {

		// Create new Excel workbook and sheet
		HSSFWorkbook xlsWorkbook = new HSSFWorkbook();
		HSSFSheet xlsSheet = xlsWorkbook.createSheet();
		short rowIndex = 0;

		// Execute SQL query
		PreparedStatement stmt = connection.prepareStatement("select * from "
				+ tablename);
		ResultSet rs = stmt.executeQuery();

		// Get the list of column names and store them as the first
		// row of the spreadsheet.
		ResultSetMetaData colInfo = rs.getMetaData();
		List<String> colNames = new ArrayList<String>();
		HSSFRow titleRow = xlsSheet.createRow(rowIndex++);
		
		int colCount = colInfo.getColumnCount();
		System.out.println("colCount:"+colCount);
		for (int i = 1; i <= colCount; i++) {
			System.out.println("colInfo.getColumnName("+i+"):"+colInfo.getColumnName(i));
			colNames.add(colInfo.getColumnName(i));
			titleRow.createCell((short) (i - 1)).setCellValue(
					new HSSFRichTextString(colInfo.getColumnName(i)));
			xlsSheet.setColumnWidth((short) (i - 1), (short) 4000);
		}

		// Save all the data from the database table rows
		while (rs.next()) {
			HSSFRow dataRow = xlsSheet.createRow(rowIndex++);
			short colIndex = 0;
			System.out.println("colNames length:"+colNames.size());
			for (String colName : colNames) {
				System.out.println("rs.getString("+colName+"):"+rs.getString(colName));
				dataRow.createCell(colIndex++).setCellValue(
						new HSSFRichTextString(rs.getString(colName)));
			}
		}

		// Write to disk
		xlsWorkbook.write(new FileOutputStream(filename));
	}

	// Close database connection
	public void close() throws SQLException {
		connection.close();
	}

	public static void main(String[] args) {
		try {
			MysqlToXls mysqlToXls = new MysqlToXls("hospital", "root", "root");
			mysqlToXls.generateXls("primer", "E:\\person.xls");
			mysqlToXls.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}