package oosequence;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.*;
import java.util.Calendar;
import java.util.Date;

public class FlightTest {
	private Date getDate(int year, int month, int day, int hour, int minute) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(year, month, day, hour, minute, 0);
		return cal.getTime();
	}
		
	@Test
	public void test_getter_setter_arrivalAirport_validAirport(){
		

		TripComponent c = new TripComponent();
		c.setArrivalAirport("BAC");
		assertEquals("Set arrival airport to BAC", "BAC", c.getArrivalAirport());
	}

	@Test
	public void test_getter_setter_departureAirport_Invalid_TooShort(){
		

		TripComponent c = new TripComponent();
		c.setDepartureAirport("YY");
		assertEquals("Set departure airport to YY", "", c.getDepartureAirport());
	}
	
	@Test
	public void test_getter_setter_setTwice() {
		TripComponent c = new TripComponent();
		c.setDepartureAirport("YYY");
		c.setDepartureAirport("WXYZ");
		assertEquals("Changed departure airport from YYY to WXYZ", "", c.getDepartureAirport());
	}
	
	
	@Test
	public void test_getter_setter_arrivalAirport_Invalid_TooLong(){
		TripComponent c = new TripComponent();
		c.setArrivalAirport("BABA");
		assertEquals("Set arrival airport to BABA", "", c.getArrivalAirport());
	}
	
	@Test
	public void test_getter_setter_departureAirport_Invalid_null(){
		TripComponent c = new TripComponent();
		c.setDepartureAirport(null);
		assertEquals("Set departure airport to null", "", c.getDepartureAirport());
	}
	
	@Test
	public void test_getter_setter_arrivalAirport_Invalid_null(){
		TripComponent c = new TripComponent();
		c.setArrivalAirport(null);
		assertEquals("Set arrival airport to null", "", c.getArrivalAirport());
	}
	
	@Test
	public void test_getDuration_startOneHourAndTenMinutesBeforeEnd() {
		
		TripComponent c = new TripComponent();
		c.setStart(getDate(2018,11,28,10,20));
		c.setEnd(getDate(2018,11,28,11,30));
		String expectedDuration = "70 minutes";
		assertEquals("Flight is one hour and 10 minutes long", expectedDuration, c.getDuration());		
	}
}