package com.lzg.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

public class HttpRequestMethod {
	public static final String CHARSET = "UTF-8";
	private static final CloseableHttpClient httpClient;

	static {
		RequestConfig config = RequestConfig.custom().setConnectTimeout(7000).setSocketTimeout(6000).build();
		httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
	}

	public static String doPost(String url, String params) {
		return doPost(url, params, CHARSET);
	}

	/**
	 * HTTP Post 获取内容
	 * 
	 * @param url
	 *            请求的url地址 ?之前的地址
	 * @param params
	 *            请求的参数
	 * @param charset
	 *            编码格式
	 * @return 页面内容
	 */
	public static String doPost(String url, String params, String charset) {

		try {
			/*
			 * List<NameValuePair> pairs = null; if(params != null &&
			 * !params.isEmpty()){ pairs = new
			 * ArrayList<NameValuePair>(params.size());
			 * for(Map.Entry<String,String> entry : params.entrySet()){ String
			 * value = entry.getValue(); if(value != null){
			 * System.out.println(value); pairs.add(new
			 * BasicNameValuePair(entry.getKey(),value)); } } }
			 */
			HttpPost httpPost = new HttpPost(url);

			httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
			if (params != null) {
				StringEntity s = new StringEntity(params, "utf-8");
				s.setContentEncoding(charset);
				s.setContentType("application/json");
				// httpPost.setEntity(new UrlEncodedFormEntity(pairs,CHARSET));
				httpPost.setEntity(s);
			}
			CloseableHttpResponse response = httpClient.execute(httpPost);

			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				httpPost.abort();
				throw new RuntimeException("HttpClient,error status code :" + statusCode);
			}
			HttpEntity entity = response.getEntity();
			String result = null;
			if (entity != null) {
				result = EntityUtils.toString(entity, "utf-8");
			}
			EntityUtils.consume(entity);
			response.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String post(String path, String params) throws Exception {
		HttpURLConnection httpConn = null;
		BufferedReader in = null;
		DataOutputStream out = null;
		try {
			URL url = new URL(path);
			httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setConnectTimeout(5000);
			httpConn.setReadTimeout(6000);
			httpConn.setDoInput(true);
			httpConn.setDoOutput(true);
			httpConn.setRequestMethod("POST");
			httpConn.setUseCaches(false);
			httpConn.setInstanceFollowRedirects(true);
			httpConn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
			httpConn.connect();

			// 发送post请求参数
			out = new DataOutputStream(httpConn.getOutputStream());
			out.writeBytes(params);
			out.flush();
			out.close();

			// 读取响应
			if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				StringBuffer content = new StringBuffer();
				String tempStr = "";
				in = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
				while ((tempStr = in.readLine()) != null) {
					content.append(tempStr);
				}

				return content.toString();
			} else {

				throw new Exception("请求出现了问题!:" + httpConn.getResponseCode());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null)
				in.close();
			if (out != null)
				out.close();
			if (httpConn != null)
				httpConn.disconnect();

		}
		return null;
	}

	/**
	 * 根据请求地址，获得返回信息
	 * 
	 * @param requestUrl
	 * @return 返回一个hashMap对象
	 */
	@SuppressWarnings("unchecked")
	public static HashMap<String, Object> getHashMap(String requestUrl) {
		Gson gson = new Gson();
		StringBuffer buffer = new StringBuffer();
		HttpsURLConnection httpUrlConn = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		try {
			URL url = new URL(requestUrl);
			httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			httpUrlConn.setRequestMethod("GET");
			httpUrlConn.connect();

			// 将返回的输入流转换成字符串
			inputStream = httpUrlConn.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			return gson.fromJson(buffer.toString(), HashMap.class);
		} catch (ConnectException ce) {
			ce.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null)
					bufferedReader.close();
				if (inputStreamReader != null)
					inputStreamReader.close();
				if (inputStream != null) {
					inputStream.close();
					inputStream = null;
				}
				httpUrlConn.disconnect();
			} catch (IOException io) {
				io.printStackTrace();
			}
		}
		return null;
	}

	public static String readContentFromGet(String url, String str) throws IOException {
		if (str != null && str.length() > 0) {
			url = url + "?" + str;
		}
		URL getUrl = new URL(url);

		// 根据拼凑的URL，打开连接，URL.openConnection()函数会根据
		// URL的类型，返回不同的URLConnection子类的对象，在这里我们的URL是一个http，因此它实际上返回的是HttpURLConnection

		HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
		connection.setRequestMethod("GET");
		// 建立与服务器的连接，并未发送数据
		connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
		connection.connect();

		// 发送数据到服务器并使用Reader读取返回的数据

		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));

		String lines;
		StringBuffer sb = new StringBuffer();
		while ((lines = reader.readLine()) != null) {
			sb.append(lines);
		}
		reader.close();
		// 断开连接
		connection.disconnect();

		return sb.toString();
	}

	public static String getRandom() {
		// 源始的内容
		String str = "0123456789";
		// 存放随机数
		char[] rands = new char[11];
		StringBuffer sbu = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < 11; i++) {
			int index = random.nextInt(10);
			rands[i] = str.charAt(index);
			sbu.append(str.charAt(index));

		}
		return sbu.toString();
	}

	public static void main(String[] args) throws Exception {
		/*
		 * Random r = new Random(); List<HashMap<String,String>> listmap = new
		 * ArrayList<HashMap<String,String>>();
		 * 
		 * for(int i=0;i<200;i++){ HashMap<String,String > hmp = new
		 * HashMap<String,String>(); hmp.put("phones",
		 * HttpRequestMethod.getRandom()); listmap.add(hmp); } for(HashMap mpa :
		 * listmap){ Map<String,String> hpam = new HashMap<String,String>();
		 * System.out.println(mpa.get("phones").toString()); hpam.put("phone",
		 * mpa.get("phones").toString()); hpam.put("type", "create"); Gson g =
		 * new Gson(); //String str =HttpRequestMethod.post(
		 * "http://test.wang-guanjia.com/coupon/coupon/autoGiveOut",
		 * g.toJson(hpam)); String str =HttpRequestMethod.doPost(
		 * "http://test.wang-guanjia.com/coupon/coupon/autoGiveOut",
		 * g.toJson(hpam)); System.out.println(str); }
		 */
		// String ss =
		// "http://test.wang-guanjia.com/coupon/coupon/consumeTestByGoods";
		// System.out.println(ss.substring(0, ss.indexOf("coupon")));
		String url = "http://192.168.1.115:8080/background-manage/goods/es/search";
		Gson g = new Gson();
		Map<String, Object> map = new HashMap<>();
		map.put("regionId", "r1");
		map.put("queryStr", "咖啡");
		map.put("type", "market");
		String str = HttpRequestMethod.doPost(url, g.toJson(map));
	}
}
