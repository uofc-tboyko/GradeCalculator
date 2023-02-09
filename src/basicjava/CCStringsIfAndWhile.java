package basicjava;

public class CCStringsIfAndWhile {
	public static boolean isDigit(char aChar) {
		char[] digits = {'1','2','3','4','5','6','7','8','9','0'};
		for(char digit:digits) {
			if(digit==aChar) {
				return true;
			}
		}
		return false;
	}
	
	public static int count(String str, String chars) {
		char strArray[] = str.toCharArray();
		char charArray[] = chars.toCharArray();
		int matches = 0;
		
		for(char a : strArray) {
			for(char b : charArray) {
				if(a!=b) {
					break;
				}
				matches+=1;
			}
		}
		
		return matches;
	}
	
	public static int smallestDigit(int num) {
		int smallest = 9;
		if (num<0) {
			num*=-1;
		}
		while(num>-1) {
			if(num%10<smallest) {
				smallest = num%10;
			}
			num/=10;
		}
		return smallest;
	}
}