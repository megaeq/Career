package com.test.IK;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.wltea.expression.ExpressionEvaluator;
import org.wltea.expression.datameta.Variable;

public class Test1
{
	public static void main(String[] args)
	{
		String [] cols = {"C02","A01","D02","A02"};
		String [] params = {"A01","A02","A03","C01","C02","C03"};
		String expression = "(A01||A02||A03)&&(C01||C02||C03)";
		System.out.println(contains(cols, params,3, expression));
		List<String> list = split(expression);
		for(int i=0;i<list.size();i++) {
			System.out.println(i+":"+list.get(i));
		}
	}
	
	public static Boolean contains(String[] cols,String [] params,Integer minSize,String expression) {
		Collection<Variable> col = new ArrayList<Variable>();
		Integer size = 0;
		for(int i=0;i<params.length;i++) {
			boolean contains = false;
			for(int j=0;j<cols.length;j++) {
				if(cols[j].equals(params[i])) {
					contains = true;
					size++;
					break;
				}
			}
			col.add(Variable.createVariable(params[i], contains));
		}
		if(size>=minSize) {
			Object result = ExpressionEvaluator.evaluate(expression,col);
			return Boolean.parseBoolean(result.toString());
		} else {
			return false;
		}
		
	}
	
	public static List<String> split(String expression) {
		List<String> list = new ArrayList<String>();
		expression = expression.replace("(", " ").replace(")", " ").replace("||", " ").replace("&&", " "); 
		String[] ss = expression.split(" ");
		for(int i=0;i<ss.length;i++) {
			if(StringUtils.isNotBlank(ss[i])) {
				boolean contain = false;
				for(int j=0;j<list.size();j++) {
					if(list.get(j).equals(ss[i])) {
						contain = true;
						break;
					}
				}
				if(!contain) {
					list.add(ss[i]);
				}
			}
		}
		return list;
	} 
}
