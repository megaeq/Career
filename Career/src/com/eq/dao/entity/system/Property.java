package com.eq.dao.entity.system;

import com.eq.dao.entity.BaseEntity;

public class Property extends BaseEntity {

	/**
	 * @fields serialVersionUID : TODO
	 */
	private static final long	serialVersionUID	= 1L;
	private String				property;
	private String				value;

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
