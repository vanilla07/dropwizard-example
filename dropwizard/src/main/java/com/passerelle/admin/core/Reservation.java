package com.passerelle.admin.core;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "reservations")
@NamedQueries({
    @NamedQuery(name = "com.passerelle.admin.core.Reservation.findAll",
            query = "select e from Reservation e"),
    @NamedQuery(name = "com.passerelle.admin.core.Reservation.findByName",
            query = "select e from Reservation e "
            + "where e.name like :name "),
    @NamedQuery(name = "com.passerelle.admin.core.Reservation.findByRoom",
            query = "select e from Reservation e "
            + "where e.room = :room ")
})
public class Reservation {

    /**
     * Entity's unique identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    /**
     * room id.
     */
    private int room;
    /**
     * client name.
     */
    private String name;
    /**
     * client email address.
     */
    private String email;
    /**
     * client phone number.
     */
    private String telephone;
    /**
     * number of guests.
     */
    @Column(name = "guests_number")
    private int guestsNumber;
    /**
     * Arrival date.
     */
    @Column(name = "date_in")
    private Date dateIn;
    /**
     * Check-out date.
     */
    @Column(name = "date_out")
    private Date dateOut;
    /**
     * Reservation date.
     */
    @Column(name = "date_reservation")
    private Date dateReservation;
    /**
     * status of reservation.
     */
    private int status;
    /**
     * Notes.
     */
    private String notes;
    /**
     * Reservation channel.
     */
    private int channel;

    /**
     * A no-argument constructor.
     */
    public Reservation() {
    }

    public Reservation(
    		int room, 
    		String name, 
    		String email,
    		int guestsNumber,
    		Date dateIn, 
    		Date dateOut, 
    		Date dateReservation,
    		int status,
    		int channel
    		) {
        this.room = room;
        this.name = name; 
        this.email = email;
        this.guestsNumber = guestsNumber;
		this.dateIn = dateIn;
		this.dateOut = dateOut;
		this.dateReservation = dateReservation;
		this.status = status;
		this.channel = channel;
    }

	public Reservation(int room, String name, String email,
			String telephone, int guestsNumber, Date dateIn, Date dateOut,
			Date dateReservation, int status, String notes, int channel) {
		this.room = room;
		this.name = name;
		this.email = email;
		this.telephone = telephone;
		this.guestsNumber = guestsNumber;
		this.dateIn = dateIn;
		this.dateOut = dateOut;
		this.dateReservation = dateReservation;
		this.status = status;
		this.notes = notes;
		this.channel = channel;
	}
	
	@Override
	public String toString() {
		return "Reservation to add [Name=" + this.name + ", email=" + this.email
				+ ", room=" + this.room + ", dateIn="
				+ this.dateIn + ", dateOut=" + this.dateOut
				+ ", guestsNumber=" + this.guestsNumber + ", notes=" + this.notes + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + channel;
		result = prime * result + ((dateIn == null) ? 0 : dateIn.hashCode());
		result = prime * result + ((dateOut == null) ? 0 : dateOut.hashCode());
		result = prime * result
				+ ((dateReservation == null) ? 0 : dateReservation.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + guestsNumber;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		result = prime * result + room;
		result = prime * result + status;
		result = prime * result
				+ ((telephone == null) ? 0 : telephone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservation other = (Reservation) obj;
		if (channel != other.channel)
			return false;
		if (dateIn == null) {
			if (other.dateIn != null)
				return false;
		} else if (!dateIn.equals(other.dateIn))
			return false;
		if (dateOut == null) {
			if (other.dateOut != null)
				return false;
		} else if (!dateOut.equals(other.dateOut))
			return false;
		if (dateReservation == null) {
			if (other.dateReservation != null)
				return false;
		} else if (!dateReservation.equals(other.dateReservation))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (guestsNumber != other.guestsNumber)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		if (room != other.room)
			return false;
		if (status != other.status)
			return false;
		if (telephone == null) {
			if (other.telephone != null)
				return false;
		} else if (!telephone.equals(other.telephone))
			return false;
		return true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getGuestsNumber() {
		return guestsNumber;
	}

	public void setGuestsNumber(int guestsNumber) {
		this.guestsNumber = guestsNumber;
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

	public Date getDateReservation() {
		return dateReservation;
	}

	public void setDateReservation(Date dateReservation) {
		this.dateReservation = dateReservation;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

  }
