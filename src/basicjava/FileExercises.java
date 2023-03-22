package basicjava;

import java.io.*;

public class FileExercises {

	public static int counting(String toCount) throws IOException{
		int count = 0;
		BufferedReader buff = new BufferedReader(new FileReader("example.txt"));
		char[] readArr = toCount.toCharArray();
		String currentLine = buff.readLine();
		while(currentLine!=null) {
			char[] charAlley = currentLine.toCharArray();
			for(int i = 0; i<currentLine.length();i++) {
				if(i==0||charAlley[i-1]==' ') {
					for(int j = 0; j<readArr.length;j++) {
						if((Character.toLowerCase(charAlley[i+j])!=Character.toLowerCase(readArr[j]))) {
							break;
						}
						if(j==readArr.length-1) {
							count++;
						}
					}
				}
			}
			currentLine = buff.readLine();
		}
		buff.close();
		return count;
	}
	
	public void toUpper(String inFile, String outFile) throws IOException {
		try (BufferedReader in = new BufferedReader(new FileReader(inFile));
			PrintWriter pw = new PrintWriter(new FileWriter(outFile))) {

			String line;
			//String line = in.readLine();

			/*if(line==null) {
				return;
			}*/

			while((line = in.readLine())!=null) {
				System.out.println(line.toUpperCase());
				pw.println(line.toUpperCase());
				//line = in.readLine();
			}
		}
		catch (IOException e){
			throw new IOException();
		}
	}

}