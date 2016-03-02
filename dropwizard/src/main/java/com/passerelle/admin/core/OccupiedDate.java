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
@Table(name = "occupied_dates")
@NamedQueries({
    @NamedQuery(name = "com.passerelle.admin.core.OccupiedDate.findAll",
            query = "select e from OccupiedDate e"),
    @NamedQuery(name = "com.passerelle.admin.core.OccupiedDate.findByRoom",
            query = "select e from OccupiedDate e "
            + "where e.room = :room ")
})
public class OccupiedDate {

    /**
     * Entity's unique identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "id_reservation")
    private long idReservation;
    /**
     * room id.
     */
    private int room;
    /**
     * occupied date.
     */
    private Date date;
    /**
     * A no-argument constructor.
     */
    public OccupiedDate() {
    }

	public OccupiedDate(long idReservation, int room, Date date) {
		this.room = room;
		this.date = date;
		this.idReservation = idReservation;
	}

	public long getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(long idReservation) {
		this.idReservation = idReservation;
	}

	public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ (int) (idReservation ^ (idReservation >>> 32));
		result = prime * result + room;
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
		OccupiedDate other = (OccupiedDate) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (idReservation != other.idReservation)
			return false;
		if (room != other.room)
			return false;
		return true;
	}
	
}
