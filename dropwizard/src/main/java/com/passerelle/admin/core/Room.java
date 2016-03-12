package com.passerelle.admin.core;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Room {

	private int id ;
	private String name;
	private double price;
	private List<PeriodOff> bookings; 
	private List<PeriodOff> vacations;
	
	public Room(int id, String name, double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.bookings = new ArrayList<PeriodOff>();
		this.vacations = new ArrayList<PeriodOff>();
	}
	
	public Room(RoomEnum room) {
		super();
		this.id = room.getId();
		this.name = room.getName();
		this.price = room.getPrice();
		this.bookings = new ArrayList<PeriodOff>();
		this.vacations = new ArrayList<PeriodOff>();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<PeriodOff> getBookings() {
		return bookings;
	}

	public void addBooking(long id, Date dateIn, Date dateOut) {
		this.bookings.add(new PeriodOff(id, dateIn, dateOut));
	}
	
	public void addBooking(Reservation res) {
		this.bookings.add(new PeriodOff(res.getId(), res.getDateIn(), res.getDateOut()));
	}
	
	public List<PeriodOff> getVacations() {
		return vacations;
	}
	
	public void addVacation(long id, Date dateIn, Date dateOut) {
		this.vacations.add(new PeriodOff(id, dateIn, dateOut));
	}
	
	public void addVacation(Vacation vac) {
		this.vacations.add(new PeriodOff(vac.getId(), vac.getDateStart(), vac.getDateEnd()));
	}
	
	public void cleanDates() {
		this.bookings = new ArrayList<PeriodOff>();
		this.vacations = new ArrayList<PeriodOff>();
	}

}
