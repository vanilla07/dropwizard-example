package com.passerelle.admin.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CalendarDate {

	@JsonProperty
	private String date;
	
	@JsonProperty
	private boolean badge;
	
	@JsonProperty
	private String title;
	
	@JsonProperty
	private String body;
	
	@JsonProperty
	private String footer;

	public CalendarDate() {
	}
	
	public CalendarDate(String date, boolean badge) {
		this.date = date;
		this.badge = badge;
	}

	public void setVacation(Vacation vacation) {
		DateFormat df = new SimpleDateFormat("EEEE dd MMMM yyyy", Locale.FRENCH);
		this.title = "Fermeture : " + vacation.getName();
		
		this.body = "<p>Du " + df.format(vacation.getDateStart()) + " au " + df.format(vacation.getDateEnd()) + "</p>";
		if (!vacation.getNotes().isEmpty()){
			this.body += "<p>Notes : " + vacation.getNotes() + "</p>";
		}
		
		this.footer = "<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Fermer</button>";
	}
	
	public void setReservation(Reservation reservation) {
		DateFormat df = new SimpleDateFormat("EEEE dd MMMM yyyy", Locale.FRENCH);
		this.title = "Réservation no : " + reservation.getId();
		
		this.body = "<p>Client : " + reservation.getName()+ "</p>";
		this.body += "<p>Du " + df.format(reservation.getDateIn()) + " au " + df.format(reservation.getDateOut()) + "</p>";
		this.body += "<p>Réservé le " + df.format(reservation.getDateReservation()) + "</p>";
		if (!reservation.getNotes().isEmpty()){
			this.body += "<p>Notes : " + reservation.getNotes() + "</p>";
		}
		
		this.footer = "<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Fermer</button>";
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean isBadge() {
		return badge;
	}

	public void setBadge(boolean badge) {
		this.badge = badge;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getFooter() {
		return footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}
	
}
