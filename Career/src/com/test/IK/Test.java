package com.test.IK;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.SortedSet;

import org.apache.log4j.Logger;
import org.wltea.expression.ExpressionEvaluator;
import org.wltea.expression.datameta.Variable;
import org.wltea.expression.function.FunctionLoader;

public class Test
{
	public static void main(String[] args)
	{
		String [] cols = {"C02","A01","D02","A02"};
		String [] params = {"A01","A02","A03","C01","C02","C03"};
		String expression = "(A01||A02||A03)&&(C01||C02||C03)";
		System.out.println(contains(cols, params,3, expression));
		/*Logger logger = Logger.getLogger(Test.class);
		logger.debug("111");
		logger.info("111");
		Collection<Variable> col = new ArrayList<Variable>();
		col.add(Variable.createVariable("Rd", 1.11f));
		col.add(Variable.createVariable("Rw", 1.21f));
		col.add(Variable.createVariable("Rl", 2.33f));
		col.add(Variable.createVariable("Pw", 4.554f));
		col.add(Variable.createVariable("Pd", 7.544f));
		col.add(Variable.createVariable("Pl", 9.55f));
		col.add(Variable.createVariable("P1", 2.0f));*/
		//System.out.println(ExpressionEvaluator.evaluate("2*4"));
		//Object result = ExpressionEvaluator.evaluate("Pw*Pd*Pl",col);
		//System.out.println(result);
		//System.out.println("(1+2)*3="+ExpressionEvaluator.evaluate("(1+2)*3"));
		//System.out.println("(1+2)*3/2.3="+ExpressionEvaluator.evaluate("(1+2)*3/2.3"));
		//Collection<Variable> col = new ArrayList<Variable>();
		//col.add(Variable.createVariable("Pw", 4f));
		//col.add(Variable.createVariable("Pl", 2f));
		//col.add(Variable.createVariable("Pd", 1f));
		//col.add(Variable.createVariable("c", 3));
		//System.out.println("(1+2)*3="+ExpressionEvaluator.evaluate("P1==2?1:2",col));
		//System.out.println("(1+2)*3="+ExpressionEvaluator.evaluate("Pw*Pl*Pd",col));
		//System.out.println("(1+2)*3="+ExpressionEvaluator.evaluate("a*b*c",col));
		//System.out.println("(1+2)*3的逆波兰表达式为："+ExpressionEvaluator.compile("(a+b)*c",col));
		//System.out.println("(1+2)*3的逆波兰表达式为："+ExpressionEvaluator.compile("(1+2)*3"));
		//System.out.println("系统当前日期："+ExpressionEvaluator.evaluate("$SYSDATE()"));
		//System.out.println("根号（4）="+ExpressionEvaluator.evaluate("$SQRT((2d+2)*3)"));
		//System.out.println("2^5="+Math.pow(2d, 5d));
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
}
