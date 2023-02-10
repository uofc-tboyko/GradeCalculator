package application;

import java.util.ArrayList;
import java.util.Collections;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GradeCalculatorController {
	Stage applicationStage;

	@FXML
	private ChoiceBox<Integer> optionalQuizzesCompletedChoiceBox;
	
	@FXML
	private ChoiceBox<Integer> requiredQuizzesCompletedChoiceBox;
	
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
    private Label requiredQuizLabel;
    
    @FXML
    private Label optionalQuizLabel;
    
    @FXML
    private Label averageQuizLabel;
    
    double averageQuizGrade = 0.0;
    double optionalQuizGrade =0.0;
    double requiredQuizGrade = 0.0;

    @FXML
    void getOptionalQuizGrades(ActionEvent getOptionalQuizGrades) {
    	getQuizGrades(true, optionalQuizzesCompletedChoiceBox.getValue());
    }
    @FXML
    void getRequiredQuizGrades(ActionEvent getRequiredQuizGrades) {
    	getQuizGrades(false, 15);
    }
    void getQuizGrades(boolean quizzesOptional, int numberOfQuizzes) {
    	//define vars
    	Scene mainScene = applicationStage.getScene();
    	
    	int rowsCreated = 0;
    	VBox quizGradeContainer = new VBox();
    	if(quizzesOptional) {
			quizGradeContainer.getChildren().add(new Label("Optional Quiz Grades"));
    	}
    	else {
    		quizGradeContainer.getChildren().add(new Label("Required Quiz Grades"));
    	}
    	
    	//create the proper number of widgets and add them to the scene, with the text fields being added to an arraylist.
    	ArrayList<TextField> quizGradeTextFields = new ArrayList<TextField>();
    	while(rowsCreated<numberOfQuizzes) {
			HBox rowContainer = new HBox();
			Label quizGradeLabel = new Label("Quiz Grade #"+(rowsCreated+1)+":");
			TextField quizGradeTextField = new TextField();

			quizGradeTextFields.add(quizGradeTextField);
			rowContainer.getChildren().addAll(quizGradeLabel,quizGradeTextField);
			rowsCreated++;
			
			quizGradeContainer.getChildren().addAll(rowContainer);
    	}
    	
    	//add done button and add to scene.
		Button doneButton = new Button("Done");
		doneButton.setOnAction(doneEvent -> calculateAverageQuizGrade(mainScene, quizGradeTextFields, quizzesOptional));
		quizGradeContainer.getChildren().add(doneButton);
    	
		Scene quizGradesScene = new Scene(quizGradeContainer);
		applicationStage.setScene(quizGradesScene);
    }
    
    void calculateAverageQuizGrade(Scene mainScene, ArrayList<TextField> quizGradeTextFields, boolean quizzesOptional) {
		applicationStage.setScene(mainScene);
		optionalQuizGrade = 0;
		requiredQuizGrade = 0;
		//if the quiz page we've entered is optional, we'll check only for the best 5 of 7 grades.
    	if(quizzesOptional) {
    		ArrayList<Double> optionalGrades = new ArrayList<Double>();
    		
			for (TextField quizGradeTextField : quizGradeTextFields) {
				optionalGrades.add(Double.parseDouble(quizGradeTextField.getText()));
			}
			Collections.sort(optionalGrades,Collections.reverseOrder());
			for(int i = 0; i<=5;i++) {
				optionalQuizGrade+=10*optionalGrades.get(5-i);
			}
			optionalQuizGrade/=5;
			optionalQuizLabel.setText(String.format("Average: %.2f",optionalQuizGrade)+"%");
			
			System.out.println(optionalQuizGrade);
    	} 
    	
    	//for the required quizzes we add all of them to the total and make it out of 15.
    	else {
			for (TextField quizGradeTextField : quizGradeTextFields) {
				requiredQuizGrade += 10*Double.parseDouble(quizGradeTextField.getText());
			}
			requiredQuizGrade/=15;
			requiredQuizLabel.setText(String.format("Average: %.2f", requiredQuizGrade)+"%");
    	}
    	//calculate and update average quiz grades which will be done regardless.
    	averageQuizGrade = (requiredQuizGrade*.75 + optionalQuizGrade *.25);
    	averageQuizLabel.setText(String.format("Overall quiz average: %.2f", averageQuizGrade)+"%");
    }

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
    		projectErrorLabel.setText("Error: Project Grade should be between 0% and 100%");
    		projectGrade = 0;
    	} 
    	
    	return projectGrade;
    }
    
    @FXML
    void calculateGrade(ActionEvent event) {
    	projectErrorLabel.setText("");
    	double courseGrade = 0;
    	//retrieve the project grade and add 50% to the course grade
    	double projectGrade = getProjectGrade(projectGradeTextField.getText());
    	courseGrade += projectGrade*.5;
    	System.out.println("Project Grade: "+projectGrade);
    	
    	//retrieve the quiz grade and add 25% to the course grade

    	courseGrade += (averageQuizGrade)*.25;
    	System.out.println("Avg Quiz Grade: " +averageQuizGrade);
    	
    	//retrieve the choice box values for the quizzes and add 25% of the total percent to the final grade.
    	double totalChalGrade = (optPassedChoiceBox.getValue() + challengesPassedChoiceBox.getValue())*5;
    	courseGrade += (totalChalGrade)*.25;
    	
    	textGradeDisplay.setText(String.format("Your overall course grade is: %.2f", courseGrade));//formatting and outputting string
    }

}