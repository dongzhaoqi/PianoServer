package com.piano.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;

public class XlsToMySql {

	public static void insert(String fileName) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection) DriverManager
					.getConnection(
							"jdbc:mysql://localhost:3306/hospital?useUnicode=true&characterEncoding=utf8",
							"root", "root");
			con.setAutoCommit(false);
			PreparedStatement pstm = null;
			FileInputStream input = new FileInputStream(fileName);
			POIFSFileSystem fs = new POIFSFileSystem(input);
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			Row row;
			System.out
					.println("sheet.getLastRowNum():" + sheet.getLastRowNum());
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				row = sheet.getRow(i);
				String primerid = row.getCell(0).getStringCellValue();
				String location = row.getCell(1).getStringCellValue();
				
				String feature = row.getCell(2).getStringCellValue();
				String left_primer = row.getCell(3).getStringCellValue();
				String right_primer = row.getCell(4).getStringCellValue();
				String primertype = row.getCell(5).getStringCellValue();
				String bach = row.getCell(6).getStringCellValue();
				String testnumber = row.getCell(7).getStringCellValue();

				String sql = "INSERT INTO primer VALUES('" + primerid + "','" + location
						+ "','" + feature + "','" + left_primer + "','" + right_primer + "','" + primertype + "','" + bach + "','" + testnumber + "')";
				pstm = (PreparedStatement) con.prepareStatement(sql);
				pstm.execute();
				System.out.println("Import rows " + i);
			}
			con.commit();
			pstm.close();
			con.close();
			
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException ex) {
			System.out.println(ex);
		} catch (IOException ioe) {
			System.out.println(ioe);
		}

	}

}
