package com.eq.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class FileUtils
{
	private static List<String> filelist = new ArrayList<String>();
	// ͨ���ļ����ȡ�ļ�
	@SuppressWarnings("finally")
	public static String readFileByLines(String fileName, String CHARSET)
	{

		StringBuilder sb = new StringBuilder("");
		File file = new File(fileName);
		BufferedReader reader = null;
		try
		{
			// reader = new BufferedReader(new FileReader(file),"UTF-8");
			reader = new java.io.BufferedReader(new InputStreamReader(new FileInputStream(file), CHARSET));
			String tempString = null;
			int line = 1;
			// һ�ζ���һ�У�ֱ������nullΪ�ļ�����

			while ((tempString = reader.readLine()) != null)
			{
				sb.append(tempString);
			}
			reader.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(reader != null)
			{
				try
				{
					reader.close();
				}
				catch (IOException e1)
				{
				}
			}

			return sb.toString();
		}

	}

	public static List<String> refreshFileList(String strPath)
	{
		File dir = new File(strPath);
		File[] files = dir.listFiles();

		if(files == null) return null;
		for (int i = 0; i < files.length; i++)
		{
			if(files[i].isDirectory())
			{
				refreshFileList(files[i].getAbsolutePath());
			}
			else
			{
				String strFileName = files[i].getAbsolutePath().toLowerCase();
				System.out.println("---" + strFileName);
				filelist.add(files[i].getAbsolutePath());
			}
		}
		return filelist;
	}

	public static void inputstreamToFile(InputStream input, String path, String name)
	{
		FileOutputStream os = null;
		try
		{
			try
			{
				File f = new File(new URI("file:///" + path + name));
				System.out.println(f.createNewFile());
				os = new FileOutputStream(f);
				int c;
				while ((c = input.read()) != -1)
				{
					os.write(c);
				}
			}
			catch (URISyntaxException e)
			{
				e.printStackTrace();
			}
			finally
			{
				input.close();
				os.close();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
