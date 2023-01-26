<<<<<<< HEAD
module TBoykoGradeCalculator {
	requires javafx.controls;
	requires javafx.fxml;
=======
module ThomasBoykoGradeCalculator {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.desktop;
	requires javafx.base;
>>>>>>> branch 'Validating_User_Input' of https://github.com/uofc-tboyko/GradeCalculator/
	
	opens application to javafx.graphics, javafx.fxml;
}
