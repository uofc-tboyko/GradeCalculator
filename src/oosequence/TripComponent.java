package oosequence;
import java.util.Date;

public class TripComponent {
	private Date start;
	private Date end;
	
	TripComponent(Date startDate, Date endDate){
		if(startDate!=null) {
			start= new Date(startDate.getTime());
		}
		
		if(endDate == null) {
			
		}else if(start!=null && (endDate.before(startDate)||endDate.equals(startDate))) { 

		} else {
			end = new Date(endDate.getTime());
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
	
	String getStart() {
		if(start!=null) {
			return start.toString();
		}
		return "";
	}

	String getEnd() {
		if(end!=null) {
			return end.toString();
		}
		return "";
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
	
	public boolean isBefore(TripComponent other) {
        return this.start.before(other.start);
    }
	
	public boolean isAfter(TripComponent other) {
		return !isBefore(other);
	}
	
	boolean overlapsWith(TripComponent other) {
		if(other.nullFlight()||this.nullFlight()) {
			return false;
		}
		
		return (this.end.after(other.start)&&other.end.after(this.start));
	}

	protected long lengthInSeconds() {
		if(end==null||start==null) {
			return 0;
		}
		return (end.getTime()-start.getTime())/1000;
	}
}
