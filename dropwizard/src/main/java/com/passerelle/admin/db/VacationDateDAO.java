package com.passerelle.admin.db;

import java.util.List;

import org.hibernate.SessionFactory;

import com.google.common.base.Optional;
import com.passerelle.admin.core.VacationDate;

import io.dropwizard.hibernate.AbstractDAO;

public class VacationDateDAO extends AbstractDAO<VacationDate> {

    /**
     * Constructor.
     *
     * @param sessionFactory Hibernate session factory.
     */
    public VacationDateDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<VacationDate> findAll() {
        return list(namedQuery("com.passerelle.admin.core.VacationDate.findAll"));
    }
    
    public List<VacationDate> findByRoom(int roomId) {
        return list(namedQuery("com.passerelle.admin.core.VacationDate.findByRoom")
                .setParameter("room", roomId)
        );
    }
    
    public void deleteByVacation(long vacId) {
        namedQuery("com.passerelle.admin.core.VacationDate.deleteByVacation")
                .setParameter("vacation", vacId);
    }

    public Optional<VacationDate> findById(long id) {
        return Optional.fromNullable(get(id));
    }
    
    public  void addDate(VacationDate resa) {
    	persist(resa);
    }
}
