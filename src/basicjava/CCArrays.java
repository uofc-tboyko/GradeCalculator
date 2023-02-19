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

	public static void sortAlphabetic(String[]strArray) {
		Arrays.sort(strArray);

	}
}
