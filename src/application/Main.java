package application;
	
import java.io.FileInputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

<<<<<<< HEAD
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
=======

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {

>>>>>>> branch 'master' of https://github.com/uofc-tboyko/GradeCalculator/
			FXMLLoader loader = new FXMLLoader();
			VBox root = loader.load(new FileInputStream("src/application/GradeCalculatorView.fxml"));
			
			Scene scene = new Scene(root,400,400);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Thomas' Grade Calculator");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}
