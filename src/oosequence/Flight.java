package oosequence;

import java.util.Date;

public class Flight extends TripComponent{
	private String departureAirport;
	private String arrivalAirport;
	
	Flight(Date startDate, Date endDate, String departure, String arrival){
		setDepartureAirport(departure);
		setArrivalAirport(arrival);
		setStart(startDate);
		setEnd(endDate);
	}
	
	Flight(Flight toCopy){
		super(toCopy);
		setDepartureAirport(toCopy.departureAirport);
		setArrivalAirport(toCopy.arrivalAirport);
	}
	
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
	
	String getStart() {
		return  (departureAirport + " " + super.getStart());
	}
	
	String getEnd() {
		return (arrivalAirport + " " + super.getEnd());
	}
	
	String getDuration() {
		return this.lengthInSeconds()/60+" minutes";
	}
}
