package com.passerelle.admin.resources;

import java.sql.Date;
import java.util.List;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

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
	
	@POST
	@UnitOfWork
	@Consumes(MediaType.APPLICATION_JSON)
	public void reservation( @Valid Reservation request) throws Exception {
		//System.out.println(request.toString());
		reservationDAO.addReservation(request);
		List<Date> dates = PasserelleUtils.getReservationDates(request);
		for (Date date : dates){
			occupiedDateDAO.addDate(new OccupiedDate(request.getId(), request.getRoom(), date));
		}
	}
}