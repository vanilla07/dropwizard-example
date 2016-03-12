package com.passerelle.admin.db;

import java.util.List;

import org.hibernate.SessionFactory;

import com.google.common.base.Optional;
import com.passerelle.admin.core.OccupiedDate;
import io.dropwizard.hibernate.AbstractDAO;

public class OccupiedDateDAO extends AbstractDAO<OccupiedDate> {

    /**
     * Constructor.
     *
     * @param sessionFactory Hibernate session factory.
     */
    public OccupiedDateDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<OccupiedDate> findAll() {
        return list(namedQuery("com.passerelle.admin.core.OccupiedDate.findAll"));
    }
    
    public List<OccupiedDate> findByRoom(int roomId) {
        return list(namedQuery("com.passerelle.admin.core.OccupiedDate.findByRoom")
                .setParameter("room", roomId)
        );
    }

    public void deleteByReservation(long resId) {
        list(namedQuery("com.passerelle.admin.core.OccupiedDate.deleteByReservation")
                .setParameter("reservation", resId)
        );
    }
    
    public Optional<OccupiedDate> findById(long id) {
        return Optional.fromNullable(get(id));
    }
    
    public  void addDate(OccupiedDate resa) {
    	persist(resa);
    }
}
