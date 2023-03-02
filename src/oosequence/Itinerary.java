package oosequence;

import java.util.ArrayList;
import java.util.Date;

public class Itinerary {
	private ArrayList<TripComponent> flights;
	private String name;
	
	Itinerary(String nameIn){
		name=nameIn;
		flights = new ArrayList<TripComponent>();
	}
	ArrayList<TripComponent> getFlights(){
		return flights;
	}
	String getName() {
		return name;
	}
	
	//hard part goes below here
	public void addFlight(TripComponent flight) {
	    for (TripComponent f : flights) {
	        if ((flight.getArrival().after(f.getDeparture()))&&flight.getDeparture().before(f.getArrival())) {
	        	return;
	        } 
	    }
	    int i;
	    for (i = 0; i < flights.size(); i++) {
	        if (flight.getArrival().before(flights.get(i).getArrival())) {
	            break;
	        }
	    }
	    flights.add(i, flight);
	}
	
	long getTotalLayover() {
		if(flights.size()<=1) {
			return 0;
		}
		long lay = (flights.get(flights.size()-1).getArrival().getTime()-flights.get(0).getDeparture().getTime())/60000;
		for(TripComponent f :flights) {
			lay-=f.length();
		}
		return lay;
	}

}
