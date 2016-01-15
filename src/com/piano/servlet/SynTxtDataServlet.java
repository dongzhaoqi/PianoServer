package com.piano.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.piano.util.SITI_LogDebug;

/**
 * 根据移动端传过来的用户名，判断服务器是否有该用户产生的日志文件
 * @Date 2015-12-09
 * @author Dong
 *
 */

public class SynTxtDataServlet extends HttpServlet {

	@SuppressWarnings("deprecation")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String parameter = request.getQueryString();	//获取url中?以后的内容

		String userName = parameter.split("=")[1];

		userName = URLDecoder.decode(userName);

		System.out.println("userName:" + userName);

		File dir = new File(SITI_LogDebug.dirName);

		File[] targetFiles = getFilesByPathPrefix(dir, userName);

		System.out.println("length:" + targetFiles.length);
		
		if(targetFiles.length == 0){
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			// out.print("POST method");
			out.print("该用户还没有做题记录");
			out.flush();
			out.close();
			return;
		}
		
		for (int i = 0; i < targetFiles.length; i++) {

			File file = new File(SITI_LogDebug.dirName + targetFiles[i].getName());

			InputStream inputStream = new FileInputStream(file);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					inputStream, "UTF-8"));
			// BufferedReader in = new BufferedReader(new
			// InputStreamReader(request.getInputStream(), "UTF-8"));

			// 数据
			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = in.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
			in.close();

			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			// out.print("POST method");
			out.print(sb);
			out.flush();
			out.close();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);

	}

	
	/**
	 * 获取目录下以指定前缀开头的所有文件
	 * @param path
	 * @param prefixStr
	 * @return
	 */
	public static File[] getFilesByPathPrefix(File path, final String prefixStr) {
		File[] fileArr = path.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {

				if (dir.isDirectory() && name.startsWith(prefixStr)) {
					return true;
				} else {
					return false;
				}
			}
		});
		return fileArr;

	}

}
