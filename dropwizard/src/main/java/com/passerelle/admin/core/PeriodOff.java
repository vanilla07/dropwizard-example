package com.passerelle.admin.core;

import java.sql.Date;

public class PeriodOff {
	
	private long id;
	private Date dateIn;
	private Date dateOut;
	
	public PeriodOff (long id, Date dateIn, Date dateOut){
		super();
		this.id = id;
		this.dateIn = dateIn;
		this.dateOut = dateOut;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getDateIn() {
		return dateIn;
	}
	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}
	public Date getDateOut() {
		return dateOut;
	}
	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}
}
