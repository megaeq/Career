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
	public static boolean delAllFile(String dirName)//删除指定文件夹下所有文件 
	{  
	   boolean flag=false;  
	   //如果dir不以文件分隔符结尾，自动添加文件分隔符 
	     if(!dirName.endsWith(File.separator)) 
	     { 
	      dirName = dirName + File.separator; 
	      
	     }    
	     File dirFile = new File(dirName); 
	     //如果dir对应的文件不存在，或者不是一个文件夹则退出 
	     if(!dirFile.exists() || (!dirFile.isDirectory())){ 
	      System.out.println("List失败！找不到目录："+dirName); 
	      return false; 
	     } 
	    
	     /* 
	     * list方法返回该目录下的所有文件（包括目录）的文件名，文件名不含路径信息 
	     * 
	        String[] files = dirFile.list(); 
	      for(int i = 0; i < files.length; i++){ 
	       System.out.println(files[i]); 
	      } 
	     */    
	     //列出文件夹下所有的文件,listFiles方法返回目录下的所有文件（包括目录）的File对象 
	     File[] files = dirFile.listFiles();     
	     for(int i = 0; i < files.length; i++) 
	     { 
	      if(files[i].isFile()) 
	      { 
	       Long nowMills = System.currentTimeMillis();
	       
	       Long modifiedMills = files[i].lastModified();
	       //删除创建时间超过10分钟的文件
	       if(nowMills-modifiedMills>600000) {
	    	   if(files[i].delete()==false) 
		       { 
		      System.out.print(files[i].getAbsolutePath()+"删除失败\n"); 
		       } 
		       else 
		       { 
		      System.out.println(files[i].getAbsolutePath() + " 删除成功\n"); 
		       }      
		       flag=true; 
	       }
	      } 
	      else if (files[i].isDirectory()) 
	      { 
	       System.out.println(files[i].getAbsolutePath() + " 是目录！"); 
	       //ListFileUtil.listAllFiles(files[i].getAbsolutePath()); 
	      } 
	     } 
	     return flag; 
	} 
	
	private static Long getMills(String fileName) {
		String mills = fileName.substring(0, 11);
		return 0l;
	}
}
