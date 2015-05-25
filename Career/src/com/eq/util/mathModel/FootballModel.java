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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wltea.expression.ExpressionEvaluator;
import org.wltea.expression.datameta.Variable;

import com.eq.dao.entity.lottory.Game;
import com.eq.dao.entity.mathModel.MathData;
import com.eq.dao.entity.mathModel.MathDataRef;
import com.eq.dao.entity.mathModel.MathModelRef;
import com.eq.dao.impl.mathModel.MathDataImpl;
import com.eq.dao.impl.mathModel.MathDataRefImpl;
import com.eq.dao.impl.mathModel.MathModelImpl;
import com.eq.dao.impl.mathModel.MathModelRefImpl;

/**
 * @className PMulti
 * @description TODO
 * @author Mega.Yan
 * @date 2015-5-13 上午10:38:31
 */
@Component
public class FootballModel implements MathModel<Game>
{
	@Autowired
	private MathModelImpl mathModelImpl;
	@Autowired
	private MathModelRefImpl mathModelRefImpl;
	@Override
	public Float getResult(Game t,Integer mathModelId)
	{
		Collection<Variable> col = new ArrayList<Variable>();
		col.add(Variable.createVariable("Rd", t.getDrawRate()));
		col.add(Variable.createVariable("Rw", t.getWinRate()));
		col.add(Variable.createVariable("Rl", t.getLoseRate()));
		col.add(Variable.createVariable("Pw", t.getPw()));
		col.add(Variable.createVariable("Pd", t.getPd()));
		col.add(Variable.createVariable("Pl", t.getPl()));
		Float Pmax = t.getPw()>=t.getPd()&&t.getPw()>=t.getPl()?t.getPw():(t.getPd()>=t.getPl()?t.getPd():t.getPl());
		Float Pmin = t.getPw()<=t.getPd()&&t.getPw()<=t.getPl()?t.getPw():(t.getPd()<=t.getPl()?t.getPd():t.getPl());
		Float Pmid = t.getPw()!=Pmin&&t.getPw()!=Pmax?t.getPw():(t.getPd()!=Pmin&&t.getPd()!=Pmax?t.getPd():t.getPl());
		col.add(Variable.createVariable("Pmax", Pmax));
		col.add(Variable.createVariable("Pmid", Pmid));
		col.add(Variable.createVariable("Pmin", Pmin));
		com.eq.dao.entity.mathModel.MathModel model = mathModelImpl.selectOne(mathModelId);
		Object result = ExpressionEvaluator.evaluate(model.getExpression(),col);
		return Float.parseFloat(result.toString());
	}
	@Override
	public void add(Game t,Integer mathModelId)
	{
		float result = getResult(t,mathModelId);
		MathModelRef modelRef = new MathModelRef();
		modelRef.setGameId(t.getId());
		modelRef.setMathModelId(mathModelId);
		modelRef.setResult(result);
		mathModelRefImpl.add(modelRef);
	}

}
