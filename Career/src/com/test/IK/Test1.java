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
		String expression = "(A01||A02||A03)&&(C01||C02||C03)*(D01||D02||D03)";
		String minSizes = "3*1";
		List<String> list = split(expression);
		System.out.println(containsMulti(cols, expression, minSizes));
		
	}
	public static Boolean containsMulti(String[] cols,String expressions,String minSizes) {
		String[] expressionss = replaces(expressions).split("\\*");
		String[] minSizess = minSizes.split("\\*");
		String newExpression = "true";
		for(int i=0;i<expressionss.length;i++) {
			List<String> params = split(expressionss[i]);
			newExpression+="&&"+containsone(cols,params,Integer.parseInt(minSizess[i]),expressionss[i]);
		}
		Object result = ExpressionEvaluator.evaluate(newExpression);
		return Boolean.parseBoolean(result.toString());
	}
	
	public static String replaces(String expression) {
		expression = expression.replace("（", "(");
		expression = expression.replace("）", ")");
		return expression;
	}
	
	
	public static Boolean containsone(String[] cols,List<String> params,Integer minSize,String expression) {
		Collection<Variable> col = new ArrayList<Variable>();
		Integer size = 0;
		for(int i=0;i<params.size();i++) {
			boolean contains = false;
			for(int j=0;j<cols.length;j++) {
				if(cols[j].equals(params.get(i))) {
					contains = true;
					size++;
					break;
				}
			}
			col.add(Variable.createVariable(params.get(i), contains));
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
