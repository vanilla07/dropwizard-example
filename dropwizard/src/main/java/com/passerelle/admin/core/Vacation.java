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
@Table(name = "vacation")
@NamedQueries({
	// All vacations
    @NamedQuery(name = "com.passerelle.admin.core.Vacation.findAll",
            query = "select e from Vacation e"),
    // All vacations starting after the date or including the date
    @NamedQuery(name = "com.passerelle.admin.core.Vacation.findAllByDate",
            query = "select e from Vacation e "
            		+ "where e.dateStart >= :date "
            		+ "or (dateStart < :date and dateEnd > :date) "
            		+ "order by dateStart asc"
    		),
    // All vacations containing this words in the name
    @NamedQuery(name = "com.passerelle.admin.core.Vacation.findByName",
            query = "select e from Vacation e "
            + "where e.name like :name "),
    // All vacations for this room
    @NamedQuery(name = "com.passerelle.admin.core.Vacation.findByRoom",
            query = "select e from Vacation e "
            + "where e.room = :room "),
    // All vacations of this room starting after the date or including the date sorted and limited to a dateEnd
 	@NamedQuery(name = "com.passerelle.admin.core.Vacation.findByRoomByDate",
 	        query = "select e from Vacation e "
 	        		+ "where e.room = :roomid "
	        		+ "and "
	        		+ "(e.dateStart between :date and :dateend "
	        		+ "or e.dateStart < :date and e.dateEnd > :date) "
	        		+ "order by e.dateStart asc"
 			),
     // Delete a vacation by ID
     @NamedQuery(name = "com.passerelle.admin.core.Vacation.delete",
     			query = "delete Vacation e where e.id = :id ")
})
public class Vacation {

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
     * Starting date.
     */
    @Column(name = "date_start")
    private Date dateStart;
    /**
     * Ending date.
     */
    @Column(name = "date_end")
    private Date dateEnd;
    /**
     * Notes.
     */
    private String notes;

    /**
     * A no-argument constructor.
     */
    public Vacation() {
    }

	public Vacation(int room, String name, Date dateStart, Date dateEnd, String notes) {
		this.room = room;
		this.name = name;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.notes = notes;
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

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateEnd == null) ? 0 : dateEnd.hashCode());
		result = prime * result
				+ ((dateStart == null) ? 0 : dateStart.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
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
		Vacation other = (Vacation) obj;
		if (dateEnd == null) {
			if (other.dateEnd != null)
				return false;
		} else if (!dateEnd.equals(other.dateEnd))
			return false;
		if (dateStart == null) {
			if (other.dateStart != null)
				return false;
		} else if (!dateStart.equals(other.dateStart))
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
		return true;
	}
}
