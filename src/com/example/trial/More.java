package com.example.trial;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class More extends AsyncTask<Context, Integer, String>

{

	GlobalActivity c;

	public More(GlobalActivity googleActivity) {
		// TODO Auto-generated constructor stub
		c = googleActivity;
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		System.out.println("result is  " + result);
		c.morelinks(result);
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(Context... arg0) {
		// TODO Auto-generated method stub
		String Keyword, json = null;
		InputStream is = null;
		try {
			Keyword = GlobalActivity.esearch.getText().toString().trim();
			// defaultHttpClient
			DefaultHttpClient httpClient = new DefaultHttpClient();
			String urlencode = URLEncoder.encode(Keyword, "UTF-8");
			HttpGet httpPost = new HttpGet(
					"http://ajax.googleapis.com/ajax/services/search/web?rsz=8&start=9&v=1.0&q="
							+ urlencode);

			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			is = httpEntity.getContent();

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			json = sb.toString();
		} catch (Exception e) {
			Log.e("Buffer Error", "Error converting result " + e.toString());
		}
		System.out.println(" asdfgbhn   " + json);
		return json;
	}

}
