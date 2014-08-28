package com.test;

import java.sql.Date;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String date = "1991-01-01";
		Date date1 = Date.valueOf(date);
		System.out.println(date1.toString());
	}

}
