package com.test.IK;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.wltea.expression.ExpressionEvaluator;
import org.wltea.expression.datameta.Variable;
import org.wltea.expression.function.FunctionLoader;

public class Test
{
	public static void main(String[] args)
	{
		Logger logger = Logger.getLogger(Test.class);
		logger.debug("111");
		logger.info("111");
		Collection<Variable> col = new ArrayList<Variable>();
		col.add(Variable.createVariable("Rd", 1.11f));
		col.add(Variable.createVariable("Rw", 1.21f));
		col.add(Variable.createVariable("Rl", 2.33f));
		col.add(Variable.createVariable("Pw", 4.554f));
		col.add(Variable.createVariable("Pd", 7.544f));
		col.add(Variable.createVariable("Pl", 9.55f));
		System.out.println(ExpressionEvaluator.evaluate("2*4"));
		Object result = ExpressionEvaluator.evaluate("Pw*Pd*Pl",col);
		System.out.println(result);
		//System.out.println("(1+2)*3="+ExpressionEvaluator.evaluate("(1+2)*3"));
		//System.out.println("(1+2)*3/2.3="+ExpressionEvaluator.evaluate("(1+2)*3/2.3"));
		//Collection<Variable> col = new ArrayList<Variable>();
		//col.add(Variable.createVariable("Pw", 4f));
		//col.add(Variable.createVariable("Pl", 2f));
		//col.add(Variable.createVariable("Pd", 1f));
		//col.add(Variable.createVariable("c", 3));
		//System.out.println("(1+2)*3="+ExpressionEvaluator.evaluate("Pw*Pl*Pd",col));
		//System.out.println("(1+2)*3="+ExpressionEvaluator.evaluate("a*b*c",col));
		//System.out.println("(1+2)*3的逆波兰表达式为："+ExpressionEvaluator.compile("(a+b)*c",col));
		//System.out.println("(1+2)*3的逆波兰表达式为："+ExpressionEvaluator.compile("(1+2)*3"));
		//System.out.println("系统当前日期："+ExpressionEvaluator.evaluate("$SYSDATE()"));
		//System.out.println("根号（4）="+ExpressionEvaluator.evaluate("$SQRT((2d+2)*3)"));
		//System.out.println("2^5="+Math.pow(2d, 5d));
	}
}
