package com.passerelle.admin.resources;

import java.sql.Date;
import java.util.List;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

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
	
	@DELETE
	@Path("/{id}")
	@UnitOfWork
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") LongParam id) {
		Optional<Vacation> vac = vacationDAO.findById(id.get());
        if (!vac.isPresent()) {
            //throw new ResourceNotFoundException(new Throwable("Impossible to remove the reservation "+ id.get() + ": Resource not found."));
            Response.status(Status.NOT_FOUND);
        }
		vacationDAO.deleteVacation(vac.get());
		return Response.ok().build();
	}
	
	@POST
	@UnitOfWork
	@Consumes(MediaType.APPLICATION_JSON)
	public void addVacation( @Valid Vacation request) throws Exception {
		
		// TODO : tester la validité des dates
		// TODO : tester la présence des champs obligatoires		
		
		addOrUpdate(request);
	}
	
	@PUT
	@Path("/{id}")
	@UnitOfWork
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateVacation( @Valid Vacation request) throws Exception {
		
		// TODO : tester la validité des dates
		// TODO : tester la présence des champs obligatoires		
		
		addOrUpdate(request);
	}

	private void addOrUpdate(Vacation request) {
		vacationDAO.saveVacation(request);
		List<Date> dates = PasserelleUtils.getVacationDates(request);
		
		// Update table of vacation dates
		// Remove old vacation dates
		List<VacationDate> datesToRemove = vacationDateDAO.findByVacation(request.getId());
		for (VacationDate d : datesToRemove){
			vacationDateDAO.deleteDate(d);
		}
		// Add new vacation dates
		for (Date date : dates){
			vacationDateDAO.addDate(new VacationDate(request.getId(), request.getRoom(), date));
		}
	}
}