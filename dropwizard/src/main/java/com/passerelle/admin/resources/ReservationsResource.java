package com.passerelle.admin.resources;

import java.sql.Date;
import java.util.List;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
//import io.dropwizard.servlets.assets.ResourceNotFoundException;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.common.base.Optional;
import com.passerelle.admin.api.PasserelleUtils;
import com.passerelle.admin.core.OccupiedDate;
import com.passerelle.admin.core.Reservation;
import com.passerelle.admin.db.OccupiedDateDAO;
import com.passerelle.admin.db.ReservationDAO;

@Path("/bookings")
@Produces(MediaType.APPLICATION_JSON)
public class ReservationsResource {

	private ReservationDAO reservationDAO;
	private OccupiedDateDAO occupiedDateDAO;

	public ReservationDAO getReservationDAO() {
		return reservationDAO;
	}

	public void setReservationDAO(ReservationDAO reservationDAO) {
		this.reservationDAO = reservationDAO;
	}

	public OccupiedDateDAO getOccupiedDateDAO() {
		return occupiedDateDAO;
	}

	public void setOccupiedDateDAO(OccupiedDateDAO occupiedDateDAO) {
		this.occupiedDateDAO = occupiedDateDAO;
	}

	public ReservationsResource(ReservationDAO reservationDAO, OccupiedDateDAO occupiedDateDAO) {
		this.reservationDAO = reservationDAO;
		this.occupiedDateDAO = occupiedDateDAO;
	}

	@GET
	@UnitOfWork
	public List<Reservation> findByName(
			@QueryParam("name") Optional<String> name
			) {
		if (name.isPresent()) {
			return reservationDAO.findByName(name.get());
		} else {
			return reservationDAO.findAll();
		}
	}

	@GET
	@Path("/{id}")
	@UnitOfWork
	public Optional<Reservation> findById(@PathParam("id") LongParam id) {
		return reservationDAO.findById(id.get());
	}
	
	@DELETE
	@Path("/{id}")
	@UnitOfWork
	public Response delete( @PathParam("id") LongParam id) {
		Optional<Reservation> res = reservationDAO.findById(id.get());
        if (!res.isPresent()) {
            //throw new ResourceNotFoundException(new Throwable("Impossible to remove the reservation "+ id.get() + ": Resource not found."));
            Response.status(Status.NOT_FOUND);
        }
		reservationDAO.deleteReservation(res.get());
		return Response.ok().build();
	}
	
	@POST
	@UnitOfWork
	@Consumes(MediaType.APPLICATION_JSON)
	public void addReservation( @Valid Reservation request) {
		
		// TODO : tester la validité des dates
		// TODO : tester la présence des champs obligatoires
		// TODO : tester la  validité de l'adresse email
		
		addOrUpdate(request);
	}
	
	@PUT
	@Path("/{id}")
	@UnitOfWork
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateReservation( @Valid Reservation request) {
		
		// TODO : tester la validité des dates
		// TODO : tester la présence des champs obligatoires
		// TODO : tester la  validité de l'adresse email
		
		addOrUpdate(request);
	}

	private void addOrUpdate(Reservation request) {
		reservationDAO.saveReservation(request);
		List<Date> dates = PasserelleUtils.getReservationDates(request);
		
		// Remove old occupied dates
		List<OccupiedDate> datesToRemove = occupiedDateDAO.findByReservation(request.getId());
		for (OccupiedDate d : datesToRemove){
			occupiedDateDAO.deleteDate(d);
		}
		// Add new occupied dates
		for (Date date : dates){
			occupiedDateDAO.addDate(new OccupiedDate(request.getId(), request.getRoom(), date));
		}
	}
}