package com.passerelle.admin.db;

import java.util.List;

import org.hibernate.SessionFactory;

import com.google.common.base.Optional;
import com.passerelle.admin.core.Vacation;

import io.dropwizard.hibernate.AbstractDAO;

public class VacationDAO extends AbstractDAO<Vacation> {

    /**
     * Constructor.
     *
     * @param sessionFactory Hibernate session factory.
     */
    public VacationDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Vacation> findAll() {
        return list(namedQuery("com.passerelle.admin.core.Vacation.findAll"));
    }
    
    public List<Vacation> findByRoom(int roomId) {
        return list(namedQuery("com.passerelle.admin.core.Vacation.findByRoom")
                .setParameter("room", roomId)
        );
    }

    public List<Vacation> findByName(String name) {
        StringBuilder builder = new StringBuilder("%");
        builder.append(name).append("%");
        return list(
                namedQuery("com.passerelle.admin.core.Vacation.findByName")
                .setParameter("name", builder.toString())
        );
    }

    public Optional<Vacation> findById(long id) {
        return Optional.fromNullable(get(id));
    }
    
    public  void addVacation(Vacation resa) {
    	persist(resa);
    }
}
