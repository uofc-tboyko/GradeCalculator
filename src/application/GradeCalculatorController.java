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
    Label quizErrorLabel = new Label("");
    
    double optQGrade = 0.0;
    double reqQGrade = 0.0;

    @FXML
    void getOptionalQuizGrades(ActionEvent getoptQGrades) {
    	getQuizGrades(true, optionalQuizzesCompletedChoiceBox.getValue());
    }
    @FXML
    void getRequiredQuizGrades(ActionEvent getreqQGrades) {
    	getQuizGrades(false, requiredQuizzesCompletedChoiceBox.getValue());
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
    	quizGradeContainer.getChildren().add(quizErrorLabel);
    	
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
		String errorMessage = "";
		boolean errorInQuizGrade = false;
		quizErrorLabel.setText("");
		//if the quiz page we've entered is optional, we'll check only for the best 5 of 7 grades.
    	if(quizzesOptional) {
    		optQGrade = 0;
    		ArrayList<Double> optionalGrades = new ArrayList<Double>();
    		Grade optionalGrade = new Grade(0,10,0.2);
    		
			for (TextField quizGradeTextField : quizGradeTextFields) {
				errorMessage = optionalGrade.setValue(quizGradeTextField.getText());
				optionalGrades.add(optionalGrade.getWeightedPercentageGrade());
				if(errorMessage!="") {
					errorInQuizGrade = true;
					quizErrorLabel.setText(errorMessage);
				}
			}
			Collections.sort(optionalGrades,Collections.reverseOrder());

			for(int i=0; i<5&&i<optionalGrades.size();i++) {
				optQGrade+=optionalGrades.get(i);
			}
			optionalQuizLabel.setText(String.format("Average: %.2f",optQGrade)+"%");
    	} 
    	
    	//for the required quizzes we add all of them to the total and make it out of 15.
    	else {
		reqQGrade = 0;
			for (TextField quizGradeTextField : quizGradeTextFields) {
				Grade requiredGrade = new Grade(0,10,(1.0/15.0));
				errorMessage = requiredGrade.setValue(quizGradeTextField.getText());
				reqQGrade += requiredGrade.getWeightedPercentageGrade();
				if(errorMessage!="") {
					errorInQuizGrade = true;
					quizErrorLabel.setText(errorMessage);
				}
			}
			requiredQuizLabel.setText(String.format("Average: %.2f",reqQGrade)+"%");
    	}
    	if(!errorInQuizGrade) {
    		applicationStage.setScene(mainScene);
    	}
    }
    
    @FXML
    void calculateGrade(ActionEvent event) {
    	projectErrorLabel.setText("");
    	
    	//project grade calculations
    	Grade myProjectGrade = new Grade(0.0, 100, 0.5);
    	projectErrorLabel.setText(myProjectGrade.setValue(projectGradeTextField.getText()));
    	
    	Grade optionalQuizGrade = new Grade(optQGrade,100,0.0625);
    	
    	Grade requiredQuizGrade = new Grade(reqQGrade,100,0.1875);
    	
    	Grade codingChallengeGrade = new Grade(optPassedChoiceBox.getValue() + challengesPassedChoiceBox.getValue(),20,0.25);
    	
    	double courseGrade = myProjectGrade.getWeightedPercentageGrade()+
    			optionalQuizGrade.getWeightedPercentageGrade()+
    			requiredQuizGrade.getWeightedPercentageGrade()+
    			codingChallengeGrade.getWeightedPercentageGrade();

    	textGradeDisplay.setText(String.format("Your overall course grade is: %.2f", courseGrade));//formatting and outputting string
    }

}