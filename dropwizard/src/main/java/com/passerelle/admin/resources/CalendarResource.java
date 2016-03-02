package com.passerelle.admin.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.IntParam;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.passerelle.admin.core.CalendarDate;
import com.passerelle.admin.core.OccupiedDate;
import com.passerelle.admin.core.VacationDate;
import com.passerelle.admin.db.OccupiedDateDAO;
import com.passerelle.admin.db.ReservationDAO;
import com.passerelle.admin.db.VacationDAO;
import com.passerelle.admin.db.VacationDateDAO;

@Path("/calendar")
@Produces(MediaType.APPLICATION_JSON)
public class CalendarResource {

	private OccupiedDateDAO occupiedDateDAO;
	private VacationDateDAO vacationDateDAO;
	private ReservationDAO reservationDAO;
	private VacationDAO vacationDAO;

	public CalendarResource(OccupiedDateDAO occupiedDateDAO,
			VacationDateDAO vacationDateDAO, ReservationDAO reservationDAO,
			VacationDAO vacationDAO) {
		this.occupiedDateDAO = occupiedDateDAO;
		this.vacationDateDAO = vacationDateDAO;
		this.reservationDAO = reservationDAO;
		this.vacationDAO = vacationDAO;
	}

	public CalendarResource() {
		super();
	}

	public List<CalendarDate> generateCalendar(List<OccupiedDate> reservationDates, List<VacationDate> vacationDates){
		List<CalendarDate> calendar = new ArrayList<CalendarDate>();
		for (OccupiedDate date : reservationDates){
			CalendarDate cal = new CalendarDate(date.getDate().toString(), false);
			cal.setReservation(reservationDAO.findById(date.getIdReservation()).get());
			calendar.add(cal);
		}
		for (VacationDate date : vacationDates){
			CalendarDate cal = new CalendarDate(date.getDate().toString(), true);
			cal.setVacation(vacationDAO.findById(date.getIdVacation()).get());
			calendar.add(cal);
		}
		return calendar;
	}
	
	@GET
	@Path("/{room_id}")
	@UnitOfWork
	public List<CalendarDate> getJsonCalendar(@PathParam("room_id") IntParam roomId) {
        return generateCalendar(occupiedDateDAO.findByRoom(roomId.get()), vacationDateDAO.findByRoom(roomId.get()));		
	}

	public OccupiedDateDAO getOccupiedDateDAO() {
		return occupiedDateDAO;
	}

	public void setOccupiedDateDAO(OccupiedDateDAO occupiedDateDAO) {
		this.occupiedDateDAO = occupiedDateDAO;
	}

	public VacationDateDAO getVacationDateDAO() {
		return vacationDateDAO;
	}

	public void setVacationDateDAO(VacationDateDAO vacationDateDAO) {
		this.vacationDateDAO = vacationDateDAO;
	}

	
}
