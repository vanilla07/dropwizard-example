package com.passerelle.admin.db;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.hibernate.SessionFactory;

import com.google.common.base.Optional;
import com.passerelle.admin.core.Reservation;

import io.dropwizard.hibernate.AbstractDAO;

public class ReservationDAO extends AbstractDAO<Reservation> {

    /**
     * Constructor.
     *
     * @param sessionFactory Hibernate session factory.
     */
    public ReservationDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Reservation> findAll() {
        return list(namedQuery("com.passerelle.admin.core.Reservation.findAll"));
    }

    public List<Reservation> findAllByDate(Date date) {
    	return list(namedQuery("com.passerelle.admin.core.Reservation.findAllByDate")
    			.setParameter("date", date)    	
    	);
    }
    
    public List<Reservation> findByRoom(int roomId) {
        return list(namedQuery("com.passerelle.admin.core.Reservation.findByRoom")
                .setParameter("room", roomId)
        );
    }

    public List<Reservation> findByName(String name) {
        StringBuilder builder = new StringBuilder("%");
        builder.append(name).append("%");
        return list(
                namedQuery("com.passerelle.admin.core.Reservation.findByName")
                .setParameter("name", builder.toString())
        );
    }

    public Optional<Reservation> findById(long id) {
        return Optional.fromNullable(get(id));
    }
    
    // add and update
    public void saveReservation(Reservation resa) {
    	persist(resa);
    }
    
    public void deleteReservation(long id) {
    	namedQuery("com.passerelle.admin.core.Reservation.delete")
    			.setParameter("id", id);
    }
    
    public List<Reservation> findByRoomByDate(int roomId, Date date) {
    	Calendar c = Calendar.getInstance(); 
    	c.setTime(date); 
    	c.add(Calendar.DATE, 30);
    	Date dateEnd = new Date(c.getTimeInMillis());
        return list(
                namedQuery("com.passerelle.admin.core.Reservation.findByRoomByDate")
                .setParameter("roomid", roomId)
                .setParameter("date", date)
                .setParameter("dateend", dateEnd)
        );
    }
}
