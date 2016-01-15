package com.piano.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.mysql.jdbc.Statement;

public class PoiExcel {

	public static String outputFile = "E:/dong.xls";
	static String createTableSql = "";
	static String colType = "";
	static String key = "id";
	static String charSet = "utf8";
	static String tableName = "excelTest";
	static String colName = "col";
	static Connection conn;

	static void getConnection() {
		String driver_class = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhsot:3306/user?useUnicode=true&characterEncoding=gb2312";
		String user_name = "root";
		String db_password = "root";

		try {
			Class.forName(driver_class);
			conn = DriverManager.getConnection(url, user_name, db_password);
		} catch (Exception e) {
		}

	}

	public static void CreateExcel() {
		try {
			// 创建新的Excel 工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 在Excel工作簿中建一工作表，其名为缺省值
			// 如要新建一名为"效益指标"的工作表，其语句为：
			// HSSFSheet sheet = workbook.createSheet("效益指标");
			HSSFSheet sheet = workbook.createSheet();
			// 在索引0的位置创建行（最顶端的行）
			HSSFRow row = sheet.createRow((short) 0);
			// 在索引0的位置创建单元格（左上端）
			HSSFCell cell = row.createCell((short) 0);
			// 定义单元格为字符串类型
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			// 在单元格中输入一些内容
			cell.setCellValue("sweater");
			// 新建一输出文件流
			FileOutputStream fOut = new FileOutputStream(outputFile);
			// 把相应的Excel 工作簿存盘
			workbook.write(fOut);
			fOut.flush();
			// 操作结束，关闭文件
			fOut.close();
			System.out.println("文件生成...");
		} catch (Exception e) {
			System.out.println("已运行 xlCreate() : " + e);
		}
	}
	
	/** 
	* 
	* 读取excel，遍历各个小格获取其中信息，并判断其是否是手机号码，并对正确的手机号码进行显示 
	* 注意： 1.sheet， 以0开始，以workbook.getNumberOfSheets()-1结束 2.row， 
	* 以0开始(getFirstRowNum)，以getLastRowNum结束 3.cell， 
	* 以0开始(getFirstCellNum)，以getLastCellNum结束, 结束的数目不知什么原因与显示的长度不同，可能会偏长 
	* 
	*/
	public static void readExcel(String fileToBeRead) { 
	      
	//将被表示成1.3922433397E10的手机号转化为13922433397,不一定是最好的转换方法
	      
	    DecimalFormat df = new DecimalFormat("#"); 
	      
	    try { 
	       // 创建对Excel工作簿文件的引用 
	       HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(fileToBeRead)); 
	         
	       for (int numSheets = 0; numSheets < workbook.getNumberOfSheets(); numSheets++) { 
	             
	           if (null != workbook.getSheetAt(numSheets)) { 
	          
	             HSSFSheet aSheet = workbook.getSheetAt(numSheets);//获得一个sheet 
	              
	             for (int rowNumOfSheet = 0; rowNumOfSheet <= aSheet.getLastRowNum(); rowNumOfSheet++) { 
	                   
	                 System.out.print("行"+rowNumOfSheet);//其它格式的数据
	                  if (null != aSheet.getRow(rowNumOfSheet)) { 
	                        
	                       HSSFRow aRow = aSheet.getRow(rowNumOfSheet); 
	                       ArrayList recode=new ArrayList();
	                         
	                         
	                       for (short cellNumOfRow = 0; cellNumOfRow <= aRow.getLastCellNum(); cellNumOfRow++) { 
	                             
	                            if (null != aRow.getCell(cellNumOfRow)) { 
	                                  
	                                HSSFCell aCell = aRow.getCell(cellNumOfRow); 
	                                  
	                                int cellType = aCell.getCellType(); 
	                              
	                                     switch (cellType) { 
	                                             case 0://Numeric 
	                                                      String strCell = df.format(aCell .getNumericCellValue()); 
	                                                       System.out.print(strCell);
	                                                       recode.add(strCell);
	                                      
	                                                       break; 
	                                             case 1://String 
	                                                      strCell = aCell.getStringCellValue(); 
	                                                      System.out.print(strCell); 
	                                                      recode.add(strCell);
	                                                      break; 
	                                             default: 
	                                                      System.out.println("格式不对不读");//其它格式的数据 
	                                                       
	                                     } 
	                                   
	                                      
	                            } 
	                      } //end  of  遍历所有数据项结束
	                         
	                           
	                          getConnection();
	                         
	                           
	                                                
	                          String sql="insert into tab_student_info (member_no,member_name,sex," +
                                                                    "contact_mobile,province_no,city_no,county_no," +
                                                                    "village_no,address,status,oper_date," +
                                                                    "oper_code,oper_type,domain_no,att1,"+
                                                                 
                                      
                                    "values("+0+",'"+recode.get(0)+"',"+0+",'"+ recode.get(1)  +"',"+ 1 +"," + 1 +","+ 1 +","+ 1 +",'"+ recode.get(2) +"'," +  
                                    1+","+"now()"+")";
	                            
	                          System.out.println(sql);
	                          Statement statement = (Statement) conn.createStatement();
	                          statement.executeUpdate(sql);  
	                         
	                          //conn.commit();
	                         
	                  } //end  of  if (null != aSheet.getRow(rowNumOfSheet))
	                  System.out.println("");
	                    
	  
	                    
	             } //end  of  for 遍历所有行结束
	           } 
	       }  //end  of  for  遍历所有sheet结束
	         
	    } catch (Exception e) { 
	       System.out.println("ReadExcelError" + e); 
	    } 
	} 
}
