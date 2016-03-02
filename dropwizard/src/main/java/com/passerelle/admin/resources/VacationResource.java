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
import com.passerelle.admin.core.Vacation;
import com.passerelle.admin.core.VacationDate;
import com.passerelle.admin.db.VacationDAO;
import com.passerelle.admin.db.VacationDateDAO;

@Path("/vacation")
@Produces(MediaType.APPLICATION_JSON)
public class VacationResource {

	private VacationDAO vacationDAO;
	private VacationDateDAO vacationDateDAO;

	public VacationDAO getVacationDAO() {
		return vacationDAO;
	}

	public void setVacationDAO(VacationDAO vacationDAO) {
		this.vacationDAO = vacationDAO;
	}

	public VacationDateDAO getVacationDateDAO() {
		return vacationDateDAO;
	}

	public void setVacationDateDAO(VacationDateDAO vacationDateDAO) {
		this.vacationDateDAO = vacationDateDAO;
	}

	public VacationResource(VacationDAO vacationDAO, VacationDateDAO vacationDateDAO) {
		this.vacationDAO = vacationDAO;
		this.vacationDateDAO = vacationDateDAO;
	}

	@GET
	@UnitOfWork
	public List<Vacation> findByName(
			@QueryParam("name") Optional<String> name
			) {
		if (name.isPresent()) {
			return vacationDAO.findByName(name.get());
		} else {
			return vacationDAO.findAll();
		}
	}

	@GET
	@Path("/{id}")
	@UnitOfWork
	public Optional<Vacation> findById(@PathParam("id") LongParam id) {
		return vacationDAO.findById(id.get());
	}
	
	@POST
	@UnitOfWork
	@Consumes(MediaType.APPLICATION_JSON)
	public void closeDate( @Valid Vacation request) throws Exception {
		//System.out.println(request.toString());
		vacationDAO.addVacation(request);
		List<Date> dates = PasserelleUtils.getVacationDates(request);
		for (Date date : dates){
			vacationDateDAO.addDate(new VacationDate(request.getId(), request.getRoom(), date));
		}
	}
}