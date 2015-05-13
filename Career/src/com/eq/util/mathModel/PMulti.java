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

import com.eq.dao.entity.lottory.Game;

/**
 * @className PMulti
 * @description TODO
 * @author Mega.Yan
 * @date 2015-5-13 上午10:38:31
 */
public class PMulti implements MathModel<Game>
{

	@Override
	public Float getResult(Game t)
	{
		if(t.getWinRate()!=null&&t.getDrawRate()!=null&&t.getLoseRate()!=null) {
			return t.getWinRate()*t.getDrawRate()*t.getLoseRate();
		} else {
			return -1f;
		}
		
	}

}
