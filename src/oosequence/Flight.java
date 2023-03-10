package oosequence;

public class Flight extends TripComponent{
	private String departureAirport;
	private String arrivalAirport;
	
	String getDepartureAirport() {
		return departureAirport;
	}
	
	void setDepartureAirport(String airport) {
		if(airport==null) {
			departureAirport="";
		}
		else if(airport.length()==3) {
			departureAirport = airport;
		}else {
			departureAirport="";
		}
	}
	
	String getArrivalAirport() {
		return arrivalAirport;
	}
	
	void setArrivalAirport(String airport) {
		if(airport==null) {
			arrivalAirport="";
		}
		else if(airport.length()==3) {
			arrivalAirport = airport;
		}else {
			arrivalAirport="";
		}
	}
	
	String getDuration() {
		return this.lengthInSeconds()/60+" minutes";
	}
}
