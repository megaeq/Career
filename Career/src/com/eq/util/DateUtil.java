package com.eq.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DateUtil {
	public static String getFormatDate(Timestamp ts) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return df.format(ts);
	}

	public static String getFormatDate2(Timestamp ts) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(ts);
	}
}
