package com.test;

public class JsonT
{
	private String a;
	private String b;
	public String getA()
	{
		return a;
	}
	public void setA(String a)
	{
		this.a = a;
	}
	public String getB()
	{
		b="{\"test\":\""+a+"\"}";
		return b;
	}
	public void setB(String b)
	{
		this.b = b;
	}
	
}
