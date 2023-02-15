package basicjava;

import java.util.ArrayList;

public class CCArrayList {
	public static int indexOfIgnoreCase(ArrayList<String> strs, String toFind) {
		for(int i = 0; i < strs.size();i++) {
			if(strs.get(i).toLowerCase().equals(toFind.toLowerCase())){
				return i;
			}
		}
		return -1;
	}
	public static void insert(ArrayList<Double> nums, double numToInsert, int insertAtIndex) {
		if(nums.size()==0) {
			return;
		}
		nums.add(insertAtIndex,numToInsert);
	}
}
