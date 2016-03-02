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
@Table(name = "vacation_dates")
@NamedQueries({
    @NamedQuery(name = "com.passerelle.admin.core.VacationDate.findAll",
            query = "select e from VacationDate e"),
    @NamedQuery(name = "com.passerelle.admin.core.VacationDate.findByRoom",
            query = "select e from VacationDate e "
            + "where e.room = :room ")
})
public class VacationDate {

    /**
     * Entity's unique identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "id_vacation")
    private long idVacation;
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
    public VacationDate() {
    }

	public VacationDate(long idVacation, int room, Date date) {
		this.room = room;
		this.date = date;
		this.idVacation = idVacation;
	}

	public long getIdVacation() {
		return idVacation;
	}

	public void setIdVacation(long idVacation) {
		this.idVacation = idVacation;
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
				+ (int) (idVacation ^ (idVacation >>> 32));
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
		VacationDate other = (VacationDate) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (idVacation != other.idVacation)
			return false;
		if (room != other.room)
			return false;
		return true;
	}
	
}
