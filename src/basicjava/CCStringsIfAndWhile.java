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
		if(str==""||chars=="") {
			return 0;
		}
		char[] strArray = str.toLowerCase().toCharArray();
		char[] charArray = chars.toLowerCase().toCharArray();
		int matches = 0;
		
		for(char s:strArray) {
			for(char c:charArray) {
				if(s==c) {
					matches++;
				}
			}
		}
		
		return matches;
	}
	
	public static int smallestDigit(int num) {
		int smallest = num%10;
		while(num!=0) {
			if(num%10<smallest) {
				smallest = num%10;
			}
			num/=10;
		}
		if (smallest<0) {
			smallest+=10;
		}
		return smallest;
	}
}