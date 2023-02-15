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
		System.out.println(nums);
		if(nums.size()<insertAtIndex) {
			return;
		}
		double prev = nums.get(insertAtIndex);
		double last = nums.get(nums.size()-1);
		nums.set(insertAtIndex, numToInsert);
		for(int i = insertAtIndex+1; i<nums.size();i++) {
			nums.set(i, prev);
			prev = nums.get(i);
		}
		nums.add(last);
		System.out.println(nums);
	}
}
