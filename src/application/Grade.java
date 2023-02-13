package application;

public class Grade {
	double value;
	int maxValue;
	double weight;
	/*
     * Convert the value entered from a string to a double. The value returned will be 0 if the string contains non-digit
     * values or if the value after conversion to double is out of the range 0-100%
     * 
     * @param valueEntered a string that holds the value entered by the user to become the project grade
     * @return value a double that holds the value of the project grade if it is valid; otherwise will be 0.
     */
    String setValue(String valueAsString) {
    	
    	//checking for valid numbers in the text field
    	boolean valueValid = true;
    	boolean periodUsed = false;
    	String errorMessage = "";
    	
    	for(char c : valueAsString.toCharArray()) {
    		//check if the current character is a digit.
    		if(!(Character.isDigit(c)||c=='-')) {
    			if(periodUsed||c!='.') {
    				valueValid = false;
    				errorMessage = "Error: Do not use "+c+". Make sure to enter a number";
    				value = 0;
    			}
    			else if(!periodUsed&&c=='.') {
    				periodUsed=true;
    			}
    		}
    	}
    	
    	//convert the input to double if the input is valid. Otherwise it will be 0
    	if (valueValid) {
    		value = Double.parseDouble(valueAsString);
    	}
    	
    	//return an error if the project grade is not out of 100
    	//include in the computation if it's valid.
    	if(value<0.0||value>maxValue) {
    		errorMessage = String.format("Error: Grade should be between 0 and %d",maxValue);
    		value = 0;
    	} 
    	return errorMessage;
    }

}
