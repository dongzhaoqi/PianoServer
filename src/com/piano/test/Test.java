package com.piano.test;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class Test {
	OkHttpClient client = new OkHttpClient();

	public void run(String url) throws IOException {
		Request request = new Request.Builder().url(url).build();

		client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Response response) throws IOException {
				if (!response.isSuccessful())
					throw new IOException("Unexpected code " + response);

				Headers responseHeaders = response.headers();
				for (int i = 0; i < responseHeaders.size(); i++) {
					System.out.println(responseHeaders.name(i) + ": "
							+ responseHeaders.value(i));
				}

				System.out.println(response.headers());
			}

			@Override
			public void onFailure(Request arg0, IOException arg1) {
				
			}
		});
	}

	public static void main(String[] args) throws IOException {
		Test example = new Test();
		//example.run("http://publicobject.com/helloworld.txt");
		example.run("http://www.baidu.com");
	}
}