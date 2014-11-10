package com.test.compute;

public class PeiLv {
	private static Double shui=1d;
	
	public static void main(String[] args) {
		getShui(4d,3.6,2d);
	}
	
	public static void getX (Double p1) {
		System.out.println("+++++++++++++++");
		System.out.println(p1+" "+(1-p1));
		System.out.println(shui/p1);
		System.out.println(shui/(1-p1));
		System.out.println("-----------------");
	}
	
	public static Double getShui(Double A1,Double A2,Double A3) {
		//System.out.println(A1+" "+A2+" "+A3);
		Double P = 1/(1+A1/A2+A1/A3);
		System.out.println(P+" "+(A1/A2)*P+" "+(A1/A3)*P);
		Double shui = A1*P;
		System.out.println(shui);
		return shui;
	}
	
	
}
