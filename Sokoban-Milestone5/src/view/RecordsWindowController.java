package view;

import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import db.Record;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class RecordsWindowController extends Observable implements Initializable{
	
	@FXML
	TableView<RecordRow>recordTable;
	
	ObservableList<RecordRow> data;
	
	TableColumn<RecordRow, Integer>recordId;
	TableColumn<RecordRow, String>levelId;
	TableColumn<RecordRow, String>userName;
	TableColumn<RecordRow, Integer>steps;
	TableColumn<RecordRow, String>time;
	
	@FXML 
	private TextField userSearch;
	@FXML 
	private TextField levelSearch;
	@FXML 
	private Button searchButton;

	@FXML 
	private ToggleGroup group;
	
	@FXML 
	private AnchorPane anchorpane;
	
	private String userParam;
	private String levelParam;
	private String orderByParam;
	
	private String currentLevelID;
	
	private Stage stage;
	
	
	public RecordsWindowController() {
		this.currentLevelID=null;
		initParams();
		
	}
	
	public void initParams(){
		
		this.orderByParam="steps";
		this.userParam="";
		this.levelParam="";	
	}
	
	public String getLevelParam() {
		return levelParam;
	}

	public void setLevelParam(String levelParam) {
		this.levelParam = levelParam;
	}
	
	public String getCurrentLevelID() {
		return currentLevelID;
	}

	public void setCurrentLevelID(String currentLevelID) {
		this.currentLevelID = currentLevelID;
	}

	@SuppressWarnings("unchecked")
	public void showRecordsTable(List<Record> records, Stage stage){
		
		this.stage=stage;
		convertRecordToRecordRow(records);
		
		this.recordId=new TableColumn<RecordRow, Integer>("Record ID");
		this.recordId.setCellValueFactory(new PropertyValueFactory<RecordRow, Integer>("recordId"));
		this.recordId.setPrefWidth(120);
		
		this.levelId=new TableColumn<RecordRow, String>("Level");
		this.levelId.setCellValueFactory(new PropertyValueFactory<RecordRow, String>("levelId"));
		this.levelId.setPrefWidth(120);
		
		this.userName=new TableColumn<RecordRow, String>("User Name");
		this.userName.setCellValueFactory(new PropertyValueFactory<RecordRow, String>("userName"));
		this.userName.setPrefWidth(120);
		
		this.steps=new TableColumn<RecordRow, Integer>("Steps");
		this.steps.setCellValueFactory(new PropertyValueFactory<RecordRow, Integer>("steps"));
		this.steps.setPrefWidth(120);
		
		this.time=new TableColumn<RecordRow, String>("Time");
		this.time.setCellValueFactory(new PropertyValueFactory<RecordRow, String>("time"));
		this.time.setPrefWidth(120);
		
		this.recordTable.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				RecordRow r=recordTable.getSelectionModel().getSelectedItem();
				userParam=r.getUserName();
				search();

			}
		});
		
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				recordTable.setEditable(true);
				recordTable.getColumns().setAll(recordId, levelId, userName,steps,time);
				recordTable.setItems(data);				
			}
		});

		exitStage();
	}

	public void search(){
		/*if(this.currentLevelID!=null){
			this.levelParam=this.currentLevelID;
		}*/
		setChanged();
		System.out.println("Query "+this.levelParam+" "+this.userParam+" "+this.orderByParam);
		notifyObservers("Query "+this.levelParam+" "+this.userParam+" "+this.orderByParam);
		
	}
	
	public void searchParams(){
		this.userParam=userSearch.getText();
		this.levelParam=levelSearch.getText();
	}
	
	public void orderParams(){
		//Tomer said:
		RadioButton rb=(RadioButton)group.getSelectedToggle();
		this.orderByParam=rb.getText();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	//convert records to recordRow
	public void convertRecordToRecordRow(List<Record> records){
		
		this.data=FXCollections.observableArrayList();
		for(Record rec:records){
			this.data.add(new RecordRow(rec.getRecordId(), rec.getLevelId(), rec.getUserName(), rec.getSteps(), rec.getTime()));
		}
	}
	
	public void refresh(){
		initParams();
		search();
	}
	
	public void exitStage() {
		this.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				System.out.println("redX");
				refresh();
			}
		});
	}
	
}
