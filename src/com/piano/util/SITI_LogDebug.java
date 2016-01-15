package com.piano.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



public class SITI_LogDebug {
	
	public static String dirName = "D:\\/logs/";
	//public static String dirName = "/home/siti/logs/";
	
	public static void LogText (String str){
		write(getFileName(), str+"\n");		
	}
	
	public static void LogText (String str,String userName){
		write(getFileName(userName), str+"\n");		
	}
	
	public static void LogException(Exception e){
		
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		
		String str = getDateString() + " " + e.getMessage() + "::" + errors.toString();
		System.out.println(str);
		LogText(str);
	}
	
	public static void LogDebug(Object obj,String tip,int r){
		String str = getDateString() + " " + getObjName(obj) + "::" + tip + "::" + r;
		System.out.println(str);
		LogText(str);
	}
	
	public static void LogDebug(Object obj,String tip,float r){
		String str = getDateString() + " " + getObjName(obj) + "::" + tip + "::" + r;
		System.out.println(str);
		LogText(str);
	}
	
	public static String getObjName(Object obj) {
		return (obj==null)?"null":obj.getClass().getName();
	}
	public static void LogDebug(Object obj,String tip,String r){
		String str = getDateString() + " " + getObjName(obj) + "::" + tip + "::" + r;
		System.out.println(str);
		LogText(str);
	}
	
	public static String getFileName(){
		String fileName = "";
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fileName = sdf.format(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dirName + fileName + ".log";
	}
	
	public static String getFileName(String userName){
		String fileName = "";
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fileName = sdf.format(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dirName + userName + "_" + fileName + ".log";
	}
	
	public static String getTime(){
		String time = "";
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			time = sdf.format(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return time;
	}
	
	private static String getDateString(){
		String fileName = "";
		DateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		try {
			fileName = sdf.format(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}
	private static void write(String fileName, String text){
		
		BufferedWriter out = null ;  
        try  {  
            out = new  BufferedWriter( new  OutputStreamWriter(  
                    new  FileOutputStream(new File(fileName).getAbsolutePath(),  true )));  
            out.write(text + "\n"); 
            System.out.println(text);
            //System.out.println(new Date());
        } catch  (Exception e) {  
            e.printStackTrace();  
        } finally  {  
            try  {  
                out.close();  
            } catch  (IOException e) {  
                e.printStackTrace();  
            }
        }
	}
	
	
	/*Logger logger = Logger.getLogger(getClass().getName());
	
	public void LogDebug(String name){
		try {
			logger.debug(name);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		
	}
	*/
	
	public static void getTrace(final Exception e){
		System.out.println();
		//LogText("######BEGIN######");
		try {
			new PrintWriter(new BufferedWriter(new FileWriter(
					getFileName(), true)), true).println(new Object() {
				public String toString() {
					StringWriter stringWriter = new StringWriter();
					PrintWriter writer = new PrintWriter(stringWriter);
					e.printStackTrace(writer);
					StringBuffer buffer = stringWriter.getBuffer();
					return getDateString() + " " + buffer.toString();
				}
			});
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println(e1.getMessage());
		}
		//LogText("######END######");
	}
	
	public static void main(String[] args) {
		
			/*int i = 1 / 0;
			System.out.println("i=" + i);
			FileOutputStream fileOutputStream = null;
			try {
				fileOutputStream = new FileOutputStream(getFileName());
				PrintStream printStream = new PrintStream(fileOutputStream);
				System.setOut(printStream);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}*/
		try {
			int i = 1 / 0;
			System.out.println("i=" + i);
		} catch (Exception e2) {
			SITI_LogDebug.LogException(e2);
		}
		
	}
		
}
