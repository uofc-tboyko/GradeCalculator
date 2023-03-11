package oosequence;
import java.util.Date;

public class Flight {
	private Date departure;
	private Date arrival;
	
	//Constructor with 2 dates, sets each date to the input
	Flight(Date departureDate, Date arrivalDate){
		//check if the input departure is before the arrival and that they are not the same
		if(departureDate == null &&arrivalDate!=null) {
			departure = null;
			setArrival(arrivalDate);
			return;
		}
		if(arrivalDate ==null && departureDate != null) {
			arrival=null;
			setDeparture(departureDate);
			return;
		}
		if(arrivalDate==null && departureDate == null) {
			return;
		}
		if(departureDate.before(arrivalDate)&& !departureDate.equals(arrivalDate)) {
			
			setDeparture(departureDate);
			setArrival(arrivalDate);
		}
	}
	
	Flight(Flight old){
		this(old.departure,old.arrival);
	}
	
	boolean nullFlight() {
		return (departure ==null ||arrival == null);
	}

	Date getDeparture() {
		if(departure == null) {
			return null;
		}
		return new Date(departure.getTime());
	}

	Date getArrival() {
		if(arrival == null) {
			return null;
		}
		return new Date(arrival.getTime());
	}

	void setDeparture(Date depSet) {
		//we will only change the departure if:
		//the flight has a null arrival or departure, or there is no overlap between the arrival or departure.
		if(nullFlight()||(!depSet.equals(arrival)&&depSet.before(arrival))) {
			departure = new Date(depSet.getTime());
		}
	}
	
	void setArrival(Date arrSet) {
		if(nullFlight()||(!arrSet.equals(departure)&&arrSet.after(departure))) {
			arrival = new Date(arrSet.getTime());
		}
		
	}

	long length() {
		if(arrival==null||departure==null) {
			return 0;
		}
		return (arrival.getTime()-departure.getTime())/60000;
	}
}
