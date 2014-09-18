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
import com.eq.dao.impl.lottory.BillImpl;
import com.eq.dao.impl.lottory.GameAndBillImpl;
import com.eq.dao.impl.lottory.GameImpl;
import com.eq.util.BaseAction;

@Controller
@RequestMapping("page/lottory/chip")
public class ChipManage extends BaseAction {
	@Autowired
	private BillImpl		billImpl;
	@Autowired
	private GameAndBillImpl	gbImpl;
	@Autowired
	private GameImpl		gameImpl;

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
		bill.setType("virtual");
		billImpl.add(bill);
		for (GameAndBill gb : gbList) {
			gb.setBillId(bill.getId());
			gbImpl.add(gb);
		}
	}
}
