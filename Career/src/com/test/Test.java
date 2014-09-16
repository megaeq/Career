package com.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = ",,,1";
		String s2 = "1,,,";
		System.out.println(s.contains(""));
		System.out.println(s.split(",").length);
		System.out.println(s2.split(",").length);
	}

}
