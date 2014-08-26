package com.eq.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtils
{
	
	// ͨ���ļ�����ȡ�ļ�
		@SuppressWarnings("finally")
		public static String readFileByLines(String fileName,String CHARSET)
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
}
