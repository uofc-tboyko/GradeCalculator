package basicjava;
import java.util.Arrays;

public class CCArrays {
	public static void replace(char[] charArray, char toReplace, char replaceWith) {
		for(int i = 0; i<charArray.length;i++) {
			if(Character.toLowerCase(charArray[i])==Character.toLowerCase(toReplace)) {
				charArray[i]=replaceWith;
			}
		}
	}
	public static void sortAlphabetic(String[] strArray) {
		//Error currently: the final string in our list is longer than the rest, and the final for loop is trying to access
		//the 4th letter in a 3 letter string.
		/**for(int i =0; i<strArray.length;i++) {
			for(int j = 1; j<strArray.length-i;j++) {
				for (int c = 0; c<strArray[j].length();c++) {
					if(strArray[j].length() <= strArray[j-1].length()&&c<strArray[j].charAt(c)) {
					int comp = Character.compare(Character.toLowerCase(strArray[j-1].charAt(c)),Character.toLowerCase(strArray[j].charAt(c)));
						if(comp>0){
							j=strArray.length+1;
							String temp = strArray[j-1];
							strArray[j-1]=strArray[j];
							strArray[j] = temp;
						}
					}
				}
			}
		}
		*/
		Arrays.sort(strArray,String.CASE_INSENSITIVE_ORDER);
	}
}
