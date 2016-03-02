package com.passerelle.admin.db;

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
    
    public  void addReservation(Reservation resa) {
    	persist(resa);
    }
}
