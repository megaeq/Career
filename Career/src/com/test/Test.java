package com.test;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = "123.htm";
		String[] ss = s.split("\\.");
		System.out.println(ss[0]);
	}

}
