package com.piano.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.springframework.context.ApplicationContext;

import com.piano.bean.VerifiedUser;
import com.piano.orm.User;
import com.piano.orm.UserDao;
import com.piano.session.GetConfig;
import com.piano.util.MD5Util;
import com.piano.util.ResultInfo;
import com.piano.util.SITI_LogDebug;

@Path("user")
public class UserService {

	private ArrayList<VerifiedUser> list;
	ApplicationContext ctx = GetConfig.getConfig();
	UserDao userDao = UserDao.getFromApplicationContext(ctx);

	/**
	 * 注册
	 * @throws IOException
	 * @throws Exception
	 */
	
	@POST
	@Path("register")
	@Produces(value = { "application/json" })
	@Consumes(value = { "application/json" })
	public ResultInfo<User> register(String data) throws JSONException {

		ResultInfo<User> resultInfo = new ResultInfo<User>();
		JSONObject jsonObject = JSONObject.fromObject(data);

		String userName = jsonObject.getString("userName");
		String password = jsonObject.getString("password");
		String school = jsonObject.getString("school");
		String birthday = jsonObject.getString("birthday");
		String gender = jsonObject.getString("gender");

		User user = userDao.findByUname(userName);
		if (user != null) {
			resultInfo.setErrorCode(1);
			resultInfo.setErrorMsg("用户名已被注册");
			return resultInfo;
		}

		User UserRegister = new User();
		UserRegister.setUserName(userName);
		UserRegister.setPassword(MD5Util.md5(password));
		UserRegister.setSchool(school);
		UserRegister.setBirthday(birthday);
		UserRegister.setGender(gender);
		UserRegister.setIsVerified(0);

		userDao.attachDirty(UserRegister);

		resultInfo.setErrorCode(0);
		resultInfo.setErrorMsg("注册成功");
		resultInfo.setResult(UserRegister);

		String time = SITI_LogDebug.getTime();
		String text = "=====START=====\r\n" + "用户:"
				+ jsonObject.getString("userName") + "于" + time + "注册了!\r\n"
				+ "=====END=====\r\n\r\n";
		SITI_LogDebug.LogText(text);
		System.out.println(text);

		return resultInfo;
	}

	/**
	 * 登录
	 * @throws IOException
	 * @throws Exception
	 */
	@POST
	@Path("login")
	@Produces(value = { "application/json" })
	@Consumes(value = { "application/json" })
	public ResultInfo<JSONObject> login(String data) {

		ResultInfo<JSONObject> resultInfo = new ResultInfo<JSONObject>();

		JSONObject resultJSONObject = new JSONObject();

		JSONObject jsonObject = JSONObject.fromObject(data);

		String userName = jsonObject.getString("userName");
		String password = jsonObject.getString("password");
		password = MD5Util.md5(password);

		System.out.println("userName:" + userName + " password:" + password);

		User user = userDao.findByUname(userName);

		if (user == null) {
			resultInfo.setErrorCode(1);
			resultInfo.setErrorMsg("用户不存在");
			return resultInfo;
		}

		if (user.getPassword().equals(password)) {
			resultInfo.setErrorCode(0);
			resultInfo.setErrorMsg("登录成功");
			resultJSONObject.put("userName", user.getUserName());
			resultJSONObject.put("school", user.getSchool());
			resultJSONObject.put("birthdate", user.getBirthday());
			resultJSONObject.put("gender", user.getGender());
			resultInfo.setResult(resultJSONObject);

			String time = SITI_LogDebug.getTime();

			user.setLastLoginTime(time);
			userDao.update(userName, time);
			
			String text = "=====START=====\r\n" + "用户:" + userName + "于" + time
					+ "登录了!\r\n" + "=====END=====\r\n\r\n";
			System.out.println("result:"+resultInfo);
			SITI_LogDebug.LogText(text);
			System.out.println(text);

			return resultInfo;
		}

		resultInfo.setErrorCode(2);
		resultInfo.setErrorMsg("密码错误");

		return resultInfo;
	}

	/**
	 * 统计注册人数
	 * @param data
	 * @return
	 */
	@POST
	@Path("count")
	@Produces(value = { "application/json" })
	@Consumes(value = { "application/json" })
	public int countUser(String data) {

		List<User> userList = userDao.findAll();

		return userList.size();
	}
	
	
	/**
	 * 将用户做的题目写进日志文件
	 * @param data
	 */
	
	@POST
	@Path("log")
	@Produces(value = { "application/json" })
	@Consumes(value = { "application/json" })
	public void writeLog(String data) {
		
		JSONObject jsonObject = JSONObject.fromObject(data);

		String str_itemInfo = jsonObject.getString("str_itemInfo");
		String userName = jsonObject.getString("userName");
		
		System.out.println(str_itemInfo + "::" + userName);
		
		SITI_LogDebug.LogText("=====START=====\r\n"+str_itemInfo,userName);
		
	}
	
	/**
	 * 判断用户的答案是否正确，并写进日志文件
	 * @param data
	 */
	
	@POST
	@Path("answer")
	@Produces(value = { "application/json" })
	@Consumes(value = { "application/json" })
	public void writeAnswer(String data) {
		
		JSONObject jsonObject = JSONObject.fromObject(data);
		
		String str_itemInfo = jsonObject.getString("str_itemInfo");
		String userName = jsonObject.getString("userName");
		
		//System.out.println(str_itemInfo + "::" + userName);
		
		SITI_LogDebug.LogText(str_itemInfo+"\n=====END=====\r\n",userName);
		
	}
	
	@POST
	@Path("file")
	@Produces(value = { "application/json" })
	@Consumes(value = { "application/json" })
	public void getTestFile(String data) {
		
		JSONObject jsonObject = JSONObject.fromObject(data);
		
		String userName = jsonObject.getString("userName");
		
		//System.out.println(str_itemInfo + "::" + userName);
		
	}
	
	
	/**
	 * 获取所有注册用户
	 * @param data
	 * @return
	 * @throws JSONException
	 */
	@POST
	@Path("get")
	@Produces(value = { "application/json" })
	@Consumes(value = { "application/json" })
	public ResultInfo<JSONArray> getAllUser(String data) throws JSONException {

		ResultInfo<JSONArray> resultInfo = new ResultInfo<JSONArray>();

		List<User> list = userDao.findAll();

		JSONArray resultJSONArray = new JSONArray();

		for (User user : list) {
			JSONObject jsonObject = new JSONObject();
			if (!"root".equals(user.getUserName())) {
				jsonObject.put("userName", user.getUserName());
				jsonObject.put("gender", user.getGender());
				jsonObject.put("birthday", user.getBirthday());
				jsonObject.put("isVerified", user.getIsVerified());
				jsonObject.put("school", user.getSchool());
				
				String lastLoginTime = user.getLastLoginTime();
				
				if("".equals(lastLoginTime) || lastLoginTime == null){
					lastLoginTime = "还未登录过";
				}
				
				jsonObject.put("lastLoginTime", lastLoginTime);
				resultJSONArray.add(jsonObject);
			}
		}

		resultInfo.setErrorCode(0);
		resultInfo.setResult(resultJSONArray);
		return resultInfo;
	}
	
	/**
	 * 授权用户
	 * @param data
	 * @throws JSONException
	 */
	@POST
	@Path("authorize")
	@Produces(value = { "application/json" })
	@Consumes(value = { "application/json" })
	public void AuthorizeUser(String data) throws JSONException {
		
		JSONObject jsonObject = JSONObject.fromObject(data);
		list = new ArrayList<VerifiedUser>();
		
		System.out.println("jsonObject:"+jsonObject);
		
		String listArray = jsonObject.getString("list");
		
		JSONArray jsonArray = JSONArray.fromObject(listArray);
		System.out.println("jsonArray:"+jsonArray);
		System.out.println("jsonArray length:"+jsonArray.size());
		
		String userName;
		int isVerified;
		
		for(int i = 0; i < jsonArray.size(); i++){

			userName = jsonArray.getJSONObject(i).getString("userName");
			isVerified = jsonArray.getJSONObject(i).getBoolean("isVerified")?1:0;
			
			//System.out.println("userName:"+userName+" isVerified:"+isVerified);
			
			userDao.update(userName, isVerified);
		}
		
	}
	
}