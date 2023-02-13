package application;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class GradeCalculatorController {

	@FXML
	private Slider quizSlider;
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
    
    
    
    @FXML
    void calculateGrade(ActionEvent event) {
    	projectErrorLabel.setText("");
    	double courseGrade = 0;
    	//retrieve the project grade and add 50% to the course grade
    	
    	//retrieve the quiz grade and add 25% to the course grade
    	double quizGrade = quizSlider.getValue();
    	courseGrade += (quizGrade*10)*.25;
    	
    	//retrieve the choice box values for the quizzes and add 25% of the total percent to the final grade.
    	int chalPassed = challengesPassedChoiceBox.getValue();
    	int optPassed = optPassedChoiceBox.getValue();
    	double totalChalGrade = (optPassed + chalPassed)*5;
    	courseGrade += (totalChalGrade)*.25;
    	
    	textGradeDisplay.setText(String.format("Your overall course grade is: %.2f", courseGrade));//formatting and outputting string
    }

}