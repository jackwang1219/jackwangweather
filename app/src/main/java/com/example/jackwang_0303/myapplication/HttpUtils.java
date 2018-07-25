package com.example.jackwang_0303.myapplication;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
public class HttpUtils {
	public static String getJson(String path){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
			conn.connect();
			InputStream is = conn.getInputStream();
			int hasRead = 0;
			byte[]buf = new byte[1024];
			while ((hasRead = is.read(buf))!=-1) {
				baos.write(buf, 0, hasRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return baos.toString();
	}

}
