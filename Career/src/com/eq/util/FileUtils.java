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
	// Í¨ï¿½ï¿½ï¿½Ä¼ï¿½ï¿½ï¿½ï¿½È¡ï¿½Ä¼ï¿½
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
			// Ò»ï¿½Î¶ï¿½ï¿½ï¿½Ò»ï¿½Ð£ï¿½Ö±ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½nullÎªï¿½Ä¼ï¿½ï¿½ï¿½ï¿½ï¿½

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
	public static boolean delAllFile(String dirName)//å é¤æå®æä»¶å¤¹ä¸æææä»¶ 
	{  
	   boolean flag=false;  
	   //å¦ædirä¸ä»¥æä»¶åéç¬¦ç»å°¾ï¼èªå¨æ·»å æä»¶åéç¬¦ 
	     if(!dirName.endsWith(File.separator)) 
	     { 
	      dirName = dirName + File.separator; 
	      
	     }    
	     File dirFile = new File(dirName); 
	     //å¦ædirå¯¹åºçæä»¶ä¸å­å¨ï¼æèä¸æ¯ä¸ä¸ªæä»¶å¤¹åéåº 
	     if(!dirFile.exists() || (!dirFile.isDirectory())){ 
	      System.out.println("Listå¤±è´¥ï¼æ¾ä¸å°ç®å½ï¼"+dirName); 
	      return false; 
	     } 
	    
	     /* 
	     * listæ¹æ³è¿åè¯¥ç®å½ä¸çæææä»¶ï¼åæ¬ç®å½ï¼çæä»¶åï¼æä»¶åä¸å«è·¯å¾ä¿¡æ¯ 
	     * 
	        String[] files = dirFile.list(); 
	      for(int i = 0; i < files.length; i++){ 
	       System.out.println(files[i]); 
	      } 
	     */    
	     //ååºæä»¶å¤¹ä¸ææçæä»¶,listFilesæ¹æ³è¿åç®å½ä¸çæææä»¶ï¼åæ¬ç®å½ï¼çFileå¯¹è±¡ 
	     File[] files = dirFile.listFiles();     
	     for(int i = 0; i < files.length; i++) 
	     { 
	      if(files[i].isFile()) 
	      { 
	       Long nowMills = System.currentTimeMillis();
	       
	       Long modifiedMills = files[i].lastModified();
	       //å é¤åå»ºæ¶é´è¶è¿10åéçæä»¶
	       if(nowMills-modifiedMills>600000) {
	    	   if(files[i].delete()==false) 
		       { 
		      System.out.print(files[i].getAbsolutePath()+"å é¤å¤±è´¥\n"); 
		       } 
		       else 
		       { 
		      System.out.println(files[i].getAbsolutePath() + " å é¤æå\n"); 
		       }      
		       flag=true; 
	       }
	      } 
	      else if (files[i].isDirectory()) 
	      { 
	       System.out.println(files[i].getAbsolutePath() + " æ¯ç®å½ï¼"); 
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
