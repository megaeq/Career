package com.eq.dao.entity;

public class Country extends BaseEntity {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private Integer				id;
	private String				country;
	private String				leagueMatches;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLeagueMatches() {
		return leagueMatches;
	}

	public void setLeagueMatches(String leagueMatches) {
		this.leagueMatches = leagueMatches;
	}
}
