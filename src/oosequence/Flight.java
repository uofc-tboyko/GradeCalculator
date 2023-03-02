package oosequence;
import java.util.Date;

public class Flight {
	private Date departure;
	private Date arrival;
	
	Flight(Date departureDate, Date arrivalDate){
		if(departureDate==null || arrivalDate==null||departureDate.before(arrivalDate)) {
			departure = departureDate;
			arrival = arrivalDate;
		}
	}
	
	boolean nullFlight() {
		return (arrival==null||departure==null);
	}
	
	Flight(Flight old){
		arrival = old.arrival;
		departure = old.departure;
	}

	Date getDeparture() {
		return departure;
	}

	Date getArrival() {
		return arrival;
	}

	void setDeparture(Date depSet) {
		if(depSet==null) {
			return;
		}
		if(this.nullFlight()) {
			departure=depSet;
		}else if(depSet.before(arrival)) {
			departure = depSet;
		}
	}
	
	void setArrival(Date arrSet) {
		if(arrSet==null) {
			return;
		}
		if(this.nullFlight()) {
			arrival=arrSet;
		}else if(departure.before(arrSet)) {
			arrival=arrSet;
		}
		
	}

	long length() {
		if(arrival==null||departure==null) {
			return 0;
		}
		return (arrival.getTime()-departure.getTime())/60000;
	}
}
