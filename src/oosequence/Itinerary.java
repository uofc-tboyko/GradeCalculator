package oosequence;

import java.util.ArrayList;

public class Itinerary {
	private ArrayList<TripComponent> components;
	private String name;
	
	Itinerary(String nameIn){
		name=nameIn;
		components = new ArrayList<TripComponent>();
	}
	
	public String getName() {
		return name;
	}
	
	//hard part goes below here
	public void addTripComponent(TripComponent component) {
		int index = 0;
        for (TripComponent c : components) {
            if (component.overlapsWith(c)) {
                return;
            }

            if (component.isBefore(c)) {
                break;
            }
            index++;
        }

        components.add(index, component);
	}
	
	public ArrayList<TripComponent> getTripComponents(){
		return components;
	}
	
	public String toString() {
		String output=name+"\n";
		String TAB = "\t";
		for (TripComponent c :components) {
			output += components.indexOf(c)+TAB+c.getStart()+TAB+c.getEnd()+"\n";
		}
		
		return output;
	}
}
