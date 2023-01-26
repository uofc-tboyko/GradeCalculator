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
    void calculateGrade(ActionEvent event) {
    	double courseGrade = 0;
    	String projectGrade = projectGradeTextField.getText();
    	double quizGrade = quizSlider.getValue();
    	int chalPassed = challengesPassedChoiceBox.getValue();
    	int optPassed = optPassedChoiceBox.getValue();
    	
    	double totalChalGrade = (optPassed + chalPassed)*5;//gives the total challenge grade as a percent

    	courseGrade += Double.parseDouble(projectGrade)*.4;//adds half of the project grade to the final grade
    	courseGrade += (quizGrade*10)*.3;//adds a quarter of the quiz grade to the final
    	courseGrade += (totalChalGrade)*.3;//adds again a quarter of the challenge grade to the final
    	
    	textGradeDisplay.setText(String.format("Your overall course grade is: %.2f", courseGrade));
    }

}