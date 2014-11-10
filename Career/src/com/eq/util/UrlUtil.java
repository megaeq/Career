package com.eq.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlUtil
{

	public static String getContent(String url, String charset)
	{

		String curLine = "";

		String content = "";

		try
		{
			URL server = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) server

			.openConnection();

			connection.connect();

			InputStream is = connection.getInputStream();

			BufferedReader reader = new BufferedReader(new InputStreamReader(is, charset));

			while ((curLine = reader.readLine()) != null)
			{
				content += curLine;
			}
			is.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return content;
	}
}
