package com.eq.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil
{
	public static String getTimeStr(Timestamp ts)
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return df.format(ts);
	}

	public static String getDateStr(Timestamp ts)
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(ts);
	}
	public static Date getDate(String date)
	{
		

		try
		{
			if(date.contains("-"))
			{
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				return df.parse(date);
			}
			else if(date.contains("/"))
			{
				SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
				return df.parse(date);
			}
		}
		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	public static Timestamp getTimestamp(String ts) {
		try
		{
			if(ts.contains(":")) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Date date = df.parse(ts);
				return new Timestamp(date.getTime());
			}
			if(ts.contains("-"))
			{
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date date = df.parse(ts);
				return new Timestamp(date.getTime());
			}
			else if(ts.contains("/"))
			{
				SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
				Date date = df.parse(ts);
				return new Timestamp(date.getTime());
			}
		}
		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static Timestamp getNowTime() {
		return new Timestamp(Calendar.getInstance().getTimeInMillis());
	}
}
