package oosequence;
import java.util.Date;

public class Flight {
	Date departure;
	Date arrival;
	
	Flight(Date departureDate, Date arrivalDate){
		if(departureDate==null || arrivalDate==null||departureDate.before(arrivalDate)) {
			departure = departureDate;
			arrival = arrivalDate;
		}
	}
	Flight(Flight old){
		arrival = old.arrival;
		departure = old.departure;
	}
	long length() {
		if(arrival==null||departure==null) {
			return 0;
		}
		return (arrival.getTime()-departure.getTime())/60000;
	}
}
