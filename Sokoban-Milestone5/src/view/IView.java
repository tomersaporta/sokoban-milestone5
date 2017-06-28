package view;

import java.util.List;

import db.Level;
import db.Record;
import javafx.beans.property.StringProperty;
import javafx.stage.Stage;

public interface IView {
	
	public void displayGUI(Level level);
	
	public void createBindSteps(StringProperty Counter);
	
	public void setPrimaryStage(Stage primaryStage);
	
	public void exitPrimaryStage();
	
	public void displayError(String error);

	public void setSecondStage(Stage secondStage);
	
	public void showRecords(List<Record> records);
}
