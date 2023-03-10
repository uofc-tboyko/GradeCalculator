package oosequence;
import java.util.Date;

public class TripComponent {
	private Date start;
	private Date end;
	
	TripComponent(Date startDate, Date endDate){
		if(startDate==null || endDate==null || startDate.before(endDate)) {
			start = startDate;
			end = endDate;
		} else {
			start =startDate;
			end=null;
		}
	}
	
	TripComponent(TripComponent old){
		end = old.end;
		start = old.start;
	}
	
	TripComponent(){
		start = new Date();
		end = new Date();
		end.setTime(end.getTime()+3600000);
	}

	boolean nullFlight() {
		return (end==null||start==null);
	}
	
	Date getStart() {
		return start;
	}

	Date getEnd() {
		return end;
	}

	void setStart(Date depSet) {
		if(depSet==null) {
			return;
		}
		if(this.nullFlight()) {
			start=depSet;
		}else if(depSet.before(end)) {
			start = depSet;
		}
	}
	
	void setEnd(Date arrSet) {
		if(arrSet==null) {
			return;
		}
		if(this.nullFlight()) {
			end=arrSet;
		}else if(start.before(arrSet)) {
			end=arrSet;
		}
		
	}

	long lengthInSeconds() {
		if(end==null||start==null) {
			return 0;
		}
		return (end.getTime()-start.getTime())/1000;
	}
}
