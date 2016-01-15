package com.piano.test;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.junit.Test;

import net.sf.json.JSONObject;

import com.piano.http.util.HttpUtil;
import com.piano.util.MD5Util;
import com.piano.util.SITI_LogDebug;

public class ServiceTest {

	public static void main(String args[]) throws IOException, Exception {
		/*
		 * String api="api/user/register";
		 * 
		 * JSONObject jsonObject = new JSONObject(); jsonObject.put("userName",
		 * "董兆琦5"); jsonObject.put("password", "zhaoqidong5");
		 * jsonObject.put("birthday", "1991年11月04日"); jsonObject.put("gender",
		 * "男"); String result = HttpUtil.post(api, jsonObject);
		 * System.out.println("result:"+result);
		 */

	}

	/**
	 * 注册
	 * @throws IOException
	 * @throws Exception
	 */
	
	@Test
	public void testRegister() throws IOException, Exception {
		String api = "api/user/register";

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userName", "Wan wan");
		jsonObject.put("password", "111111");
		jsonObject.put("birthday", "1961年11月04日");
		jsonObject.put("gender", "女");
		String result = HttpUtil.post(api, jsonObject);
		
		System.out.println("result:"+result);
		
		JSONObject jsonResult = JSONObject.fromObject(result);
		/*if("0".equals(jsonResult.getString("errorCode"))){
			String time = SITI_LogDebug.getTime();
			String text = "=====START=====\r\n" + "用户:"+jsonObject.getString("userName")+"于"+ time +"注册了!\r\n"+"=====END=====\r\n\r\n";
			SITI_LogDebug.LogText(text);
			System.out.println(text);
		}*/
		
	}

	/**
	 * 登录
	 * @throws IOException
	 * @throws Exception
	 */
	@Test
	public void testLogin() throws IOException, Exception {

		String api = "api/user/login";
		String userName = "Jack Ma";
		String password = "111111";
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userName", userName);
		jsonObject.put("password", password);

		/*String time = SITI_LogDebug.getTime();
		String text = "=====START=====\r\n" + "用户:"+userName+"于"+ time +"登陆了!\r\n"+"=====END=====\r\n\r\n";
		SITI_LogDebug.LogText(text);
		System.out.println(text);*/
		String result = HttpUtil.post(api, jsonObject);
		
	}
	
	/**
	 * 统计注册人数
	 * @throws IOException
	 * @throws Exception
	 */
	@Test
	public void testUserCount() throws IOException, Exception{
		String api = "api/user/count";
		JSONObject jsonObject = new JSONObject();
		String result = HttpUtil.get(api);
		System.out.println("result:"+result);
	}
	
	/**
	 * 获取所有注册用户
	 * @throws IOException
	 * @throws Exception
	 */
	@Test
	public void testGetAllUser() throws IOException, Exception{
		String api = "api/user/get";
		JSONObject jsonObject = new JSONObject();
		String result = HttpUtil.post(api, jsonObject);
		System.out.println("result:"+result);
		
		JSONObject jsonObject2 = JSONObject.fromObject(result);
		
		//System.out.println(jsonObject2.get("result"));
	}
	
	
	/**
	 * 用户授权
	 * @throws IOException
	 * @throws Exception
	 */
	@Test
	public void testAuthorize() throws IOException, Exception{
		
		String api = "api/user/authorize";
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userId", 37);
		jsonObject.put("isVerified", 1);
		String result = HttpUtil.post(api, jsonObject);
		System.out.println("result:"+result);
		
	}

}
