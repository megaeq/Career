/**
 * 
 */
package com.eq.dao.entity.mathModel;

import com.eq.dao.entity.BaseEntity;

/**
 * @author mega
 *
 */
public class MathModelRef extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer gameId;
	private Integer mathModelId;
	private Float result;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGameId() {
		return gameId;
	}
	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}
	public Integer getMathModelId() {
		return mathModelId;
	}
	public void setMathModelId(Integer mathModelId) {
		this.mathModelId = mathModelId;
	}
	public Float getResult() {
		return result;
	}
	public void setResult(Float result) {
		this.result = result;
	}

}
