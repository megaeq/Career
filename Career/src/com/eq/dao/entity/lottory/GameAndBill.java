package com.eq.dao.entity.lottory;

import com.eq.dao.entity.BaseEntity;

public class GameAndBill extends BaseEntity {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private int					id;
	private int					gameId;
	private int					billId;
	// 310
	private int					bet;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public int getBet() {
		return bet;
	}

	public void setBet(int bet) {
		this.bet = bet;
	}

}
