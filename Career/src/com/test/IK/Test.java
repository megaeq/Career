package com.test.IK;

import java.util.ArrayList;
import java.util.Collection;

import org.wltea.expression.ExpressionEvaluator;
import org.wltea.expression.datameta.Variable;
import org.wltea.expression.function.FunctionLoader;

public class Test
{
	public static void main(String[] args)
	{
		System.out.println("(1+2)*3="+ExpressionEvaluator.evaluate("(1+2)*3"));
		System.out.println("(1+2)*3/2.3="+ExpressionEvaluator.evaluate("(1+2)*3/2.3"));
		Collection<Variable> col = new ArrayList<Variable>();
		col.add(Variable.createVariable("b", 2));
		col.add(Variable.createVariable("a", 1));
		col.add(Variable.createVariable("c", 3));
		col.add(Variable.createVariable("d", 4));
		System.out.println("(1+2)*3="+ExpressionEvaluator.evaluate("(a+b)*c",col));
		System.out.println("(1+2)*3的逆波兰表达式为："+ExpressionEvaluator.compile("(a+b)*c",col));
		System.out.println("(1+2)*3的逆波兰表达式为："+ExpressionEvaluator.compile("(1+2)*3"));
		System.out.println("系统当前日期："+ExpressionEvaluator.evaluate("$SYSDATE()"));
		System.out.println("根号（4）="+ExpressionEvaluator.evaluate("$开方((2d+2)*3)"));
	}
}
