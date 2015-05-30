package com.eq.service.lottory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eq.dao.entity.lottory.Bill;
import com.eq.dao.entity.lottory.Game;
import com.eq.dao.entity.lottory.GameAndBill;
import com.eq.dao.entity.myinfo.Account;
import com.eq.dao.entity.myinfo.AccountHistory;
import com.eq.dao.impl.lottory.BillImpl;
import com.eq.dao.impl.lottory.GameAndBillImpl;
import com.eq.dao.impl.lottory.GameImpl;
import com.eq.dao.impl.myinfo.AccountHistoryImpl;
import com.eq.dao.impl.myinfo.AccountImpl;
import com.eq.util.BaseAction;
import com.eq.util.DateUtil;

@Controller
@RequestMapping("bill")
public class BillManage extends BaseAction {
	@Autowired
	private BillImpl		impl;
	@Autowired
	private GameAndBillImpl	gbImpl;
	@Autowired
	private GameImpl		gameImpl;
	@Autowired
	private AccountImpl		accountImpl;
	@Autowired
	private AccountHistoryImpl accountHistoryImpl;

	@ResponseBody
	@RequestMapping("getList")
	public List<Bill> getList(@RequestParam Map<String, Object> params) {
		this.params = params;
		Map<String, Object> pps = new HashMap<String, Object>();
		pps.put("accountId", getInt("accountId"));
		Map<String, Object> pps2 = impl.selectPageList(pps, currentPage, pageSize);
		response.setHeader("Content-Range", rangeStr+pps2.get("count"));
		return (List<Bill>)pps2.get("list");
	}

	// 结算
	@ResponseBody
	@RequestMapping("clearing")
	public String clearing(@RequestParam Map<String, Object> params) {
		this.params = params;
		Map<String, Object> pps = new HashMap<String, Object>();
		pps.put("billId", getInt("billId"));
		List<GameAndBill> gbList = gbImpl.selectList(pps);
		boolean success = true;
		boolean complete = true;
		for (GameAndBill gb : gbList) {
			Game game = gameImpl.selectOne(gb.getGameId());
			if (game.getHomeScore() >= 0 && game.getGuestScore() >= 0) {
				if (game.getHomeScore() == game.getGuestScore()) {
					if (1 != gb.getBet()) {
						success = false;
						break;
					}
				} else if (game.getHomeScore() > game.getGuestScore()) {
					if (3 != gb.getBet()) {
						success = false;
						break;
					}
				} else {
					if (0 != gb.getBet()) {
						success = false;
						break;
					}
				}
			} else {
				complete = false;
				break;
			}
		}
		// 结算与否
		if (complete && success) {
			Bill bill = impl.selectOne(getInt("billId"));
			Account account = accountImpl.selectOne(bill.getAccountId());
			Account account2 = accountImpl
					.selectOne(account.getDestinationId());
			Float income = bill.getSp() * bill.getBetAmount();
			account2.setBalance(account2.getBalance() + income);
			accountImpl.updateWithoutPwd(account2);
			bill.setIncome(income);
			bill.setFlag(1);
			impl.update(bill);
			//添加账户历史记录
			AccountHistory history = new AccountHistory();
			history.setIncome(income);
			history.setCreateTime(DateUtil.getNowTime());
			history.setMemo("投注额："+bill.getBetAmount()+";sp:"+bill.getSp());
			history.setUsages("彩票中奖");
			history.setAccountId(account2.getId());
			accountHistoryImpl.add(history);
			return "success";
		} else if (complete && !success) {
			Bill bill = impl.selectOne(getInt("billId"));
			bill.setFlag(1);
			bill.setIncome(0);
			impl.update(bill);
			return "success,no profit";
		} else {
			return "game not finish,can not clearing";
		}
	}
	@ResponseBody
	@RequestMapping("clearAll")
	public String clearAll(int accountId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		params.put("flag", 0);
		List<Bill> billList = impl.selectList(params);
		String response = "";
		for(int i=0;i<billList.size();i++) {
			params.clear();
			params.put("billId", billList.get(i).getId());
			List<GameAndBill> gbList = gbImpl.selectList(params);
			boolean success = true;
			boolean complete = true;
			for (GameAndBill gb : gbList) {
				Game game = gameImpl.selectOne(gb.getGameId());
				if (game.getHomeScore() >= 0 && game.getGuestScore() >= 0) {
					if (game.getHomeScore() == game.getGuestScore()) {
						if (1 != gb.getBet()) {
							success = false;
							break;
						}
					} else if (game.getHomeScore() > game.getGuestScore()) {
						if (3 != gb.getBet()) {
							success = false;
							break;
						}
					} else {
						if (0 != gb.getBet()) {
							success = false;
							break;
						}
					}
				} else {
					complete = false;
					break;
				}
			}
			// 结算与否
			if (complete && success) {
				Bill bill = impl.selectOne(billList.get(i).getId());
				Account account = accountImpl.selectOne(bill.getAccountId());
				Account account2 = accountImpl
						.selectOne(account.getDestinationId());
				Float income = bill.getSp() * bill.getBetAmount();
				account2.setBalance(account2.getBalance() + income);
				accountImpl.updateWithoutPwd(account2);
				bill.setIncome(income);
				bill.setFlag(1);
				impl.update(bill);
				//添加账户历史记录
				AccountHistory history = new AccountHistory();
				history.setIncome(income);
				history.setCreateTime(DateUtil.getNowTime());
				history.setMemo("投注额："+bill.getBetAmount()+";sp:"+bill.getSp());
				history.setUsages("彩票中奖");
				history.setAccountId(account2.getId());
				accountHistoryImpl.add(history);
				response+=billList.get(i).getId()+ "success;";
			} else if (complete && !success) {
				Bill bill = impl.selectOne(billList.get(i).getId());
				bill.setFlag(1);
				bill.setIncome(0);
				impl.update(bill);
				response+=billList.get(i).getId()+ "success,no profit;";
			} else {
				response+=billList.get(i).getId()+ "game not finish,can not clearing;";
			}
		}
		return response;
	}

	@ResponseBody
	@RequestMapping("detail")
	public List<Map<String, Object>> detail(int billId) {
		Map<String, Object> pps = new HashMap<String, Object>();
		pps.put("billId", billId);
		List<GameAndBill> gbList = gbImpl.selectList(pps);
		List<Map<String, Object>> response = new ArrayList<Map<String, Object>>();
		for (GameAndBill gb : gbList) {
			Game game = gameImpl.selectOne(gb.getGameId());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("homeTeam", game.getHomeTeam());
			map.put("guestTeam", game.getGuestTeam());
			map.put("homeScore", game.getHomeScore());
			map.put("guestScore", game.getGuestScore());
			map.put("bet", gb.getBet());
			int res = 0;
			if (game.getHomeScore() > game.getGuestScore()) {
				res = 3;
				map.put("sp", game.getWinRate());
			} else if (game.getHomeScore() == game.getGuestScore()) {
				res = 1;
				map.put("sp", game.getDrawRate());
			} else {
				map.put("sp", game.getLoseRate());
			}
			if (gb.getBet() == res) {
				map.put("win", true);
			} else {
				map.put("win", false);
			}
			response.add(map);
		}
		return response;
	}
	@ResponseBody
	@RequestMapping("sum")
	public Map<String, Object> sum(int accountId) {
		return impl.sum(accountId);
	}
}
