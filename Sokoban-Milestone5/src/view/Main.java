package view;
	
import java.util.List;

import controller.SokobanController;
import controller.server.SokobanClientHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.MyModel;


public class Main extends Application {
	

	@Override
	public void start(Stage primaryStage) {
		
		
		try {
			//BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
			String ip="127.0.0.1";
			int sPort=8888;
			
			FXMLLoader loader=new FXMLLoader(getClass().getResource("MainWindow.fxml"));
			BorderPane root = (BorderPane)loader.load();
			MainWindowController view=loader.getController();
			MyModel model=new MyModel(ip,sPort);
			
			SokobanController theController;

			//get the args from the main
			
			List<String>args=getParameters().getRaw();
			
			
			//condition for run with server
			if(args.size()>0&&args.get(0).toLowerCase().equals("-server")){
				int port = Integer.parseInt(args.get(1));
				SokobanClientHandler server=new SokobanClientHandler();
				theController= new SokobanController(view, model,server ,port);				
				server.addObserver(theController);
			}
			
			else//without server
				theController= new SokobanController(view, model);
			
			//connect the observers and observable
			model.addObserver(theController);
			view.addObserver(theController);
			
			view.setPrimaryStage(primaryStage);
			
			Scene scene = new Scene(root,700,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("SOKOBAN");
			
			//records
			Stage secondStage = new Stage();
			
			FXMLLoader loaderRec=new FXMLLoader(getClass().getResource("NewRecords.fxml"));
			//AnchorPane pane = (AnchorPane)FXMLLoader.load(getClass().getResource("RecordsWindow.fxml"));
			AnchorPane pane = (AnchorPane)loaderRec.load();
			RecordsWindowController recordsView=loaderRec.getController();
			
			recordsView.addObserver(view);
			view.setRecordsWindow(recordsView);
			
			Scene secondScene = new Scene(pane,650,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			secondStage.setScene(secondScene);
			secondStage.setTitle("High Records");
			view.setSecondStage(secondStage);
			
			primaryStage.show();

			
		} catch(Exception e) {
			e.printStackTrace();
		}
	
	}
	
	/**
	 * create the connection between the Model View Controller
	 */	
	public static void main(String[] args) {
		
		//System.out.println(args[0]+" "+args[1]);
		launch(args);
		
	}
}
