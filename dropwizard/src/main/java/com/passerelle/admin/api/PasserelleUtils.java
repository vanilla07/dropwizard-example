package com.passerelle.admin.api;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.passerelle.admin.core.Reservation;
import com.passerelle.admin.core.Vacation;

public class PasserelleUtils {

	public static List<Date> getReservationDates(Reservation resa) {
		return getDaysBetweenDates(resa.getDateIn(), resa.getDateOut());
	}
	
	public static List<Date> getVacationDates(Vacation vac) {
		return getDaysBetweenDates(vac.getDateStart(), vac.getDateEnd());
	}
	
	public static List<Date> getDaysBetweenDates(Date startdate, Date enddate)
	{
	    List<Date> dates = new ArrayList<Date>();
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(startdate);

	    while (calendar.getTime().before(enddate))
	    {
	        Date result = new Date(calendar.getTime().getTime());
	        dates.add(result);
	        calendar.add(Calendar.DATE, 1);
	    }
	    return dates;
	}
	
}
