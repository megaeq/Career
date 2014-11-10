package com.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;

public class UrlTest
{
	// 一个public方法，返回字符串，错误则返回"error open url"  
	private static final String CHARSET = "gb2312";
	  
    public static String getContent(String strUrl) {  
  
        try {  
  
            URL url = new URL(strUrl);  
  
            BufferedReader br = new BufferedReader(new InputStreamReader(url  
  
                    .openStream()));  
  
            String s = "";  
  
            StringBuffer sb = new StringBuffer("");  
  
            while ((s = br.readLine()) != null) {  
  
                sb.append(s + "/r/n");  
  
            }  
  
            br.close();  
  
            return sb.toString();  
  
        } catch (Exception e) {  
  
            return "error open url:" + strUrl;  
  
        }  
  
    }  
  
    //代理服务器设置
    @Deprecated
    public static void initProxy(String host, int port, final String username,  
  
            final String password) {  
  
        Authenticator.setDefault(new Authenticator() {  
  
            protected PasswordAuthentication getPasswordAuthentication() {  
  
                return new PasswordAuthentication(username,  
  
                        new String(password).toCharArray());  
  
            }  
  
        });  
  
        System.setProperty("http.proxyType", "4");  
  
        System.setProperty("http.proxyPort", Integer.toString(port));  
  
        System.setProperty("http.proxyHost", host);  
  
        System.setProperty("http.proxySet", "true");  
  
    }  
  
  
  
    public static void main(String[] args) throws IOException {  
  
         //String url = "http://java.sun.com";  
    	//String url = "http://www.okooo.com/jingcai/shuju/betfa/";
    	String url = "http://www.okooo.com/livecenter/jingcai/";
         //String proxy = "http://192.168.22.81";  
  
         //int port = 80;  
  
        // String username = "username";  
  
         //String password = "password";  
  
         String curLine = "";  
  
         String content = "";  
  
         URL server = new URL(url);  
  
         //initProxy(proxy, port, username, password);  
  
         HttpURLConnection connection = (HttpURLConnection) server  
  
         .openConnection();  
  
         connection.connect();  
  
         InputStream is = connection.getInputStream();  
  
         BufferedReader reader = new BufferedReader(new  
         InputStreamReader(is,CHARSET));  
  
         while ((curLine = reader.readLine()) != null) {  
  
        System.out.println(curLine);
  
         }  
  
         //System.out.println("content= " + content);  
  
         is.close();  
  
         //System.out.println(getContent(url));  
  
    }  
  
}
