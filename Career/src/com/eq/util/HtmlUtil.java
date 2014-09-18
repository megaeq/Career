package com.eq.util;

public class HtmlUtil
{
	public static String getButton(String onclick,String name) {
		return "<button data-dojo-type=\"dijit/form/Button\" onclick=\""+onclick+"()\">"+name+"</button>";
	}
}
