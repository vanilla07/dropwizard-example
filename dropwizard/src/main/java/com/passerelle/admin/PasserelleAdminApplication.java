package com.passerelle.admin;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import com.passerelle.admin.auth.GreetingAuthenticator;
import com.passerelle.admin.auth.MyAuthorizer;
import com.passerelle.admin.core.OccupiedDate;
import com.passerelle.admin.core.Reservation;
import com.passerelle.admin.core.User;
import com.passerelle.admin.core.Vacation;
import com.passerelle.admin.core.VacationDate;
import com.passerelle.admin.db.OccupiedDateDAO;
import com.passerelle.admin.db.ReservationDAO;
import com.passerelle.admin.db.VacationDAO;
import com.passerelle.admin.db.VacationDateDAO;
import com.passerelle.admin.resources.CalendarResource;
import com.passerelle.admin.resources.HelloResource;
import com.passerelle.admin.resources.ReservationsResource;
import com.passerelle.admin.resources.RoomResource;
import com.passerelle.admin.resources.VacationResource;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;


public class PasserelleAdminApplication extends Application<PasserelleAdminConfiguration> {

    public static void main(final String[] args) throws Exception {
        new PasserelleAdminApplication().run(args);
    }

    /**
     * Hibernate bundle.
     */
    private final HibernateBundle<PasserelleAdminConfiguration> hibernateBundle = new HibernateBundle<PasserelleAdminConfiguration>(Reservation.class, OccupiedDate.class, Vacation.class, VacationDate.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(PasserelleAdminConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };
    
    @Override
    public String getName() {
        return "Passerelle Admin";
    }

    @Override
    public void initialize(final Bootstrap<PasserelleAdminConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public void run(final PasserelleAdminConfiguration configuration,
    		final Environment environment) {
    	
    	// reservations
    	final ReservationDAO reservationDAO = new ReservationDAO(hibernateBundle.getSessionFactory());
    	final OccupiedDateDAO occupiedDateDAO = new OccupiedDateDAO(hibernateBundle.getSessionFactory());
    	
    	// closing dates
    	final VacationDAO vacationDAO = new VacationDAO(hibernateBundle.getSessionFactory());
    	final VacationDateDAO vacationDateDAO = new VacationDateDAO(hibernateBundle.getSessionFactory());
    	
    	final BasicCredentialAuthFilter<User> userBasicCredentialAuthFilter =
    			new BasicCredentialAuthFilter.Builder<User>()
    			.setAuthenticator(new GreetingAuthenticator(configuration.getLogin(),
    					configuration.getPassword()))
    					.setRealm("SUPER SECRET STUFF")
    					.setAuthorizer(new MyAuthorizer())
    					.buildAuthFilter();
    	
    	environment.jersey().register(RolesAllowedDynamicFeature.class);
    	environment.jersey().register(new AuthDynamicFeature(userBasicCredentialAuthFilter));
    	environment.jersey().register(new AuthValueFactoryProvider.Binder(User.class));
    	
    	//Register resources.
        environment.jersey().register(new HelloResource());
        environment.jersey().register(new CalendarResource(occupiedDateDAO, vacationDateDAO, reservationDAO, vacationDAO));
        environment.jersey().register(new ReservationsResource(reservationDAO, occupiedDateDAO));
        environment.jersey().register(new VacationResource(vacationDAO, vacationDateDAO));
        environment.jersey().register(new RoomResource(reservationDAO, vacationDAO));
        
        // Enable CORS headers
        final FilterRegistration.Dynamic cors =
            environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }

}
