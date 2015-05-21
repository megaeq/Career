/**
 * Copyright(C) 2011-2012 BillionHealth Software Technology LTD. All Rights Reserved.
 * @title PMulti.java
 * @package com.eq.util.mathModel
 * @compiler JDK1.6
 * @description TODO
 * @author Mega.Yan
 * @date 2015-5-13 上午10:38:31
 * @version V1.0  
 */
package com.eq.util.mathModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.wltea.expression.ExpressionEvaluator;
import org.wltea.expression.datameta.Variable;

import com.eq.dao.entity.lottory.Game;
import com.eq.dao.entity.mathModel.MathData;
import com.eq.dao.impl.mathModel.MathDataImpl;
import com.eq.dao.impl.mathModel.MathModelImpl;

/**
 * @className PMulti
 * @description TODO
 * @author Mega.Yan
 * @date 2015-5-13 上午10:38:31
 */
public class FootballModel implements MathModel
{
	private int mathModelId;
	private Game t;
	@Autowired
	private MathDataImpl mathDataImpl;
	@Autowired
	private MathModelImpl mathModelImpl;
	public FootballModel(int mathModelId,Game t) {
		this.mathModelId = mathModelId;
		this.t = t;
	}
	@Override
	public Float getResult()
	{
		Collection<Variable> col = new ArrayList<Variable>();
		col.add(Variable.createVariable("Rd", t.getDrawRate()));
		col.add(Variable.createVariable("Rw", t.getWinRate()));
		col.add(Variable.createVariable("Rl", t.getLoseRate()));
		col.add(Variable.createVariable("Pw", t.getPw()));
		col.add(Variable.createVariable("Pd", t.getPd()));
		col.add(Variable.createVariable("Pl", t.getPl()));
		com.eq.dao.entity.mathModel.MathModel model = mathModelImpl.selectOne(mathModelId);
		Object result = ExpressionEvaluator.evaluate(model.getExpression(),col);
		return Float.parseFloat(result.toString());
	}
	@Override
	public void add()
	{
		float result = getResult();
		Map<String, Object> params = new HashMap<String, Object>();
		if(-1!=result) {
			params.put("mathModelId", mathModelId);
			params.put("result", result);
			List<MathData> list = mathDataImpl.selectList(params);
			if(list.isEmpty()) {
				MathData data = new MathData();
				data.setMathModelId(mathModelId);
				data.setResult(result);
				data.setScoreAverage(t.getScore());
				data.setScoreSum(t.getScore());
				data.setTimes(1);
				mathDataImpl.add(data);
			} else {
				MathData data = list.get(0);
				Float scoreSum = data.getScoreSum();
				Float scoreAverage = data.getScoreAverage();
				Integer times = data.getTimes();
				scoreSum += t.getScore();
				scoreAverage = scoreSum/(1+times);
				data.setScoreSum(scoreSum);
				data.setScoreAverage(scoreAverage);
				data.setTimes(times+1);
				mathDataImpl.update(data);
			}
			com.eq.dao.entity.mathModel.MathModel model = mathModelImpl.selectOne(mathModelId);
			model.setMaxId(t.getId());
			mathModelImpl.update(model);
		}
	}

}
