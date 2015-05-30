package com.eq.service.lottory;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
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
@RequestMapping("chip")
public class ChipManage extends BaseAction {
	@Autowired
	private BillImpl			billImpl;
	@Autowired
	private GameAndBillImpl		gbImpl;
	@Autowired
	private GameImpl			gameImpl;
	@Autowired
	private AccountHistoryImpl	historyImpl;
	@Autowired
	private AccountImpl			accountImpl;

	@ResponseBody
	@RequestMapping("chipin")
	public void chipin(@RequestParam Map<String, Object> params) {
		this.params = params;
		System.out.println(getInt("money") + " " + getString("chip"));
		String[] chips = getString("chip").split(";");
		List<GameAndBill> gbList = new ArrayList<GameAndBill>();
		float m = 1f;
		for (String gs : chips) {
			GameAndBill gb = new GameAndBill();
			String[] gss = gs.split(",");
			gb.setGameId(Integer.parseInt(gss[0]));
			Game game = gameImpl.selectOne(gb.getGameId());
			gb.setBet(Integer.parseInt(gss[1]));
			if (3 == gb.getBet()) {
				m *= game.getWinRate();
			} else if (1 == gb.getBet()) {
				m *= game.getDrawRate();
			} else {
				m *= game.getLoseRate();
			}
			gbList.add(gb);
		}
		Bill bill = new Bill();
		bill.setBetAmount(getInt("money"));
		bill.setCluster(chips.length);
		bill.setSp(m);
		bill.setTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		Account account = accountImpl.selectOne(getInt("accountId"));
		if (account.getIsReal() == 2) {
			bill.setType("virtual");
		} else {
			bill.setType("real");
		}
		bill.setFlag(0);
		bill.setIsDel(0);
		bill.setAccountId(getInt("accountId"));
		billImpl.add(bill);
		for (GameAndBill gb : gbList) {
			gb.setBillId(bill.getId());
			gbImpl.add(gb);
		}
	}

	@ResponseBody
	@RequestMapping("addincome")
	public void add(@RequestParam Map<String, Object> params) {
		this.params = params;
		AccountHistory history = new AccountHistory();
		history.setIncome(getFloat("income"));
		history.setCost(getFloat("cost"));
		if (getTimestamp("date") != null) {
			history.setCreateTime(getTimestamp("date"));
		} else {
			history.setCreateTime(DateUtil.getNowTime());
		}

		history.setMemo(getString("memo"));
		history.setUsages("彩票投注");
		history.setAccountId(getInt("accountId"));
		historyImpl.add(history);
		// 修改账户余额
		Account account1 = accountImpl.selectOne(getInt("accountId"));
		account1.setBalance(account1.getBalance() + getFloat("income")
				- getFloat("cost"));
		accountImpl.updateWithoutPwd(account1);
		// 修改源账户记录
		AccountHistory history2 = new AccountHistory();
		history2.setAccountId(account1.getDestinationId());
		history2.setCost(getFloat("income"));
		history2.setIncome(getFloat("cost"));
		if (getTimestamp("date") != null) {
			history2.setCreateTime(getTimestamp("date"));
		} else {
			history2.setCreateTime(DateUtil.getNowTime());
		}
		history2.setMemo(getString("memo"));
		history2.setUsages("彩票投注");
		historyImpl.add(history2);
		// 修改源账户余额
		Account account2 = accountImpl.selectOne(account1.getDestinationId());
		account2.setBalance(account2.getBalance() - getFloat("income")
				+ getFloat("cost"));
		accountImpl.updateWithoutPwd(account2);
	}

}
