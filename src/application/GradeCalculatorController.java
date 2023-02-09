package application;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;

public class GradeCalculatorController {
	Stage applicationStage;

	@FXML
	private ChoiceBox<Integer> quizzesCompletedChoiceBox;
    @FXML
    private ChoiceBox<Integer> optPassedChoiceBox;

    @FXML
    private TextField projectGradeTextField;

    @FXML
    private ChoiceBox<Integer> challengesPassedChoiceBox;

    @FXML
    private Label textGradeDisplay;
    
    @FXML
    private Label projectErrorLabel;
    
    /*
     * Convert the value entered from a string to a double. The value returned will be 0 if the string contains non-digit
     * values or if the value after conversion to double is out of the range 0-100%
     * 
     * @param valueEntered a string that holds the value entered by the user to become the project grade
     * @return projectGrade a double that holds the value of the project grade if it is valid; otherwise will be 0.
     */
    double getProjectGrade(String valueEntered) {
    	
    	//checking for valid numbers in the text field
    	boolean projectGradeValid = true;
    	boolean periodUsed = false;
    	double projectGrade = 0.0;
    	
    	for(char c : valueEntered.toCharArray()) {
    		//check if the current character is a digit.
    		if(!(Character.isDigit(c)||c=='-')) {
    			if(periodUsed||c!='.') {
    				projectGradeValid = false;
    				projectErrorLabel.setText("Error: Project Grade contains "+c+". Make sure to enter a number");
    				projectGrade = 0;
    			}
    			else if(!periodUsed&&c=='.') {
    				periodUsed=true;
    			}
    		}
    	}
    	
    	//convert the input to double if the input is valid. Otherwise it will be 0
    	if (projectGradeValid) {
    		projectGrade = Double.parseDouble(valueEntered);
    	}
    	
    	//return an error if the project grade is not out of 100
    	//include in the computation if it's valid.
    	if(projectGrade<0.0||projectGrade>100.0) {
    		projectErrorLabel.setText("Error: Project Grade should be between 0 and 100.");
    		projectGrade = 0;
    	} 
    	
    	return projectGrade;
    }
    @FXML
    void getQuizGrades(ActionEvent event) {
    	Scene quizGradesScene = new Scene(new Label("Placeholder"));
    	applicationStage.setScene(quizGradesScene);
    }
    
    @FXML
    void calculateGrade(ActionEvent event) {
    	projectErrorLabel.setText("");
    	double courseGrade = 0;
    	//retrieve the project grade and add 50% to the course grade
    	double projectGrade = getProjectGrade(projectGradeTextField.getText());
    	courseGrade += projectGrade*.5;
    	
    	//retrieve the quiz grade and add 25% to the course grade
    	double quizGrade = quizzesCompletedChoiceBox.getValue();
    	courseGrade += (quizGrade*10)*.25;
    	
    	//retrieve the choice box values for the quizzes and add 25% of the total percent to the final grade.
    	int chalPassed = challengesPassedChoiceBox.getValue();
    	int optPassed = optPassedChoiceBox.getValue();
    	double totalChalGrade = (optPassed + chalPassed)*5;
    	courseGrade += (totalChalGrade)*.25;
    	
    	textGradeDisplay.setText(String.format("Your overall course grade is: %.2f", courseGrade));//formatting and outputting string
    }

}