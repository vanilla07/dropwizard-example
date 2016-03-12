package com.passerelle.admin.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.IntParam;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.common.base.Optional;
import com.passerelle.admin.core.Reservation;
import com.passerelle.admin.core.Room;
import com.passerelle.admin.core.RoomEnum;
import com.passerelle.admin.core.Vacation;
import com.passerelle.admin.db.ReservationDAO;
import com.passerelle.admin.db.VacationDAO;

@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
public class RoomResource {

	private List<Room> rooms;
	private ReservationDAO reservationDAO;
	private VacationDAO vacationDAO;

	private void initRooms() {
		rooms = new ArrayList<Room>();
		for (RoomEnum r : RoomEnum.values()) {
			rooms.add(new Room(r));
		}
	}

	private void fillRooms(Room room, List<Reservation> reservations, List<Vacation> vacations) {
			// empty the lists
			room.cleanDates();
			// add reservations
			for (Reservation res : reservations) {
				room.addBooking(res);					
			}
			//add vacations	
			for (Vacation vac : vacations) {
				room.addVacation(vac);
		}
	}

	public RoomResource(ReservationDAO reservationDAO, VacationDAO vacationDAO) {
		super();
		initRooms();
		this.reservationDAO = reservationDAO;
		this.vacationDAO = vacationDAO;
	}

	@GET
	@UnitOfWork
	public List<Room> getJsonRooms(@QueryParam("date") Optional<Date> date) {
		if (date.isPresent()) {
			for (Room r : rooms) {
				fillRooms(r, reservationDAO.findByRoomByDate(r.getId(), date.get()), 
						vacationDAO.findByRoomByDate(r.getId(), date.get()));
			}
		}
		return this.rooms;
	}

	@GET
	@Path("/{id}")
	@UnitOfWork
	public Room getJsonRoomsById(
			@PathParam("id") IntParam roomId,
			@QueryParam("date") Optional<Date> date
			) {
		for (Room r : rooms) {
			if ( r.getId() == roomId.get()) {
				if (date.isPresent()) {
					fillRooms(r, reservationDAO.findByRoomByDate(r.getId(), date.get()), 
							vacationDAO.findByRoomByDate(r.getId(), date.get()));
				}
				return r;   
			}
		}
		// TODO : la chambre n'existe pas : remvoyer un message d'erreur
		return null;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public ReservationDAO getReservationDAO() {
		return reservationDAO;
	}

	public void setReservationDAO(ReservationDAO reservationDAO) {
		this.reservationDAO = reservationDAO;
	}

	public VacationDAO getVacationDAO() {
		return vacationDAO;
	}

	public void setVacationDAO(VacationDAO vacationDAO) {
		this.vacationDAO = vacationDAO;
	}

}
