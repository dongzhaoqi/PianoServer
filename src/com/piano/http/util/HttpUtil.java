package com.piano.http.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;

import sun.rmi.runtime.Log;

public class HttpUtil {

	public static String post(String api, JSONObject jsonObject)
			throws Exception, IOException {
		// CloseableHttpClient mCloseableHttpClient =
		// HttpClients.createDefault();
		// HttpResponse mHttpResponse = new
		// BasicHttpResponse(HttpVersion.HTTP_1_1,HttpStatus.SC_OK,"OK");
		// BasicHeader mBasicHeader = new BasicHeader(null, null);mBasicHeader.
		// mBasicHeader.addHeader("Request-Line",
		// "GET /app/stat/stat.php?source=6&opt=10303&sid=null&cid=null&cfields=code:120141 HTTP/1.1");
		// mHttpRequest.addHeader("Accept",
		// "image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/xaml+xml, application/x-ms-xbap, application/x-ms-application, */*");
		// mHttpRequest.addHeader("Accept-Encoding", "gzip, deflate");
		// mHttpRequest.addHeader("Accept-Language", "zh-cn");
		// mHttpRequest.addHeader("Connection", "Keep-Alive");
		// mHttpRequest.addHeader("Cookie", "HOST_DBank=61.147.89.247");
		// mHttpRequest.addHeader("Host", "dbank.vmall.com");
		// mHttpRequest.addHeader("Referer", "http://dl.vmall.com/c0o5ltoabp");
		// mHttpRequest.addHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET4.0C; .NET4.0E)");
		// mCloseableHttpClient.execute(new HttpU"101.71.77.103",mHttpRequest);
		String result = null;
		HttpClient mHttpClient = new DefaultHttpClient();

		HttpPost mHttpPost = new HttpPost(ContantValues.urlRoot + api);
		System.out.println("======:" + ContantValues.urlRoot + api);
		/*
		 * List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		 * nvps.add(new BasicNameValuePair("id", "001")); nvps.add(new
		 * BasicNameValuePair("firstName", "18101928491")); nvps.add(new
		 * BasicNameValuePair("lastName", "18101928491"));
		 * mHttpPost.setEntity(new UrlEncodedFormEntity(nvps));
		 */

		// System.out.println(jsonObject.toString());
		StringEntity entity = new StringEntity(jsonObject.toString(), "utf-8");// 解决中文乱码问题
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		mHttpPost.setEntity(entity);

		HttpResponse mHttpResponse = mHttpClient.execute(mHttpPost);

		HttpEntity entityResponse = mHttpResponse.getEntity();
		// System.out.print(entityResponse.getContentEncoding());
		// entityResponse.getContentType();
		if (entityResponse != null) {
			InputStream instreams = entityResponse.getContent();
			result = convertStreamToString(instreams);
			// System.out.println("Do something");
			System.out.println((new String(result.getBytes(), "utf-8")));
			// Do not need the rest
			// Tranlate(str);
			mHttpPost.abort();
		}
		return new String(result.getBytes(), "utf-8");

	}

	public static String get(String url) {
		InputStream inputStream = null;
		String result = "";
		try {

			// create HttpClient
			HttpClient httpclient = new DefaultHttpClient();

			HttpGet httpGet = new HttpGet(ContantValues.urlRoot+url);
			
			// make GET request to the given URL
			HttpResponse httpResponse = httpclient.execute(httpGet);

			// receive response as inputStream
			inputStream = httpResponse.getEntity().getContent();

			// convert inputstream to string
			if (inputStream != null)
				result = convertStreamToString(inputStream);
			else
				result = "Did not work!";

		} catch (Exception e) {
			// Log.i("InputStream", e.getLocalizedMessage());
		}

		return result;
	}

	public static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}