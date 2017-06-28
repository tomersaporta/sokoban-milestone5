package view;

import java.sql.Time;
import java.util.List;

import db.Record;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RecordRow {

	private final SimpleIntegerProperty recordId;
	private final SimpleStringProperty levelId;
	private final SimpleStringProperty userName;
	private final SimpleIntegerProperty steps;
	private final SimpleStringProperty time;
	
	
	public RecordRow(SimpleIntegerProperty recordId, SimpleStringProperty levelId, SimpleStringProperty userName,
			SimpleIntegerProperty steps, SimpleStringProperty time) {
		this.recordId = recordId;
		this.levelId = levelId;
		this.userName = userName;
		this.steps = steps;
		this.time = time;
	}

	public RecordRow(Integer recordId, String levelId, String userName, Integer steps, String time) {
		this.recordId =new SimpleIntegerProperty(recordId);
		this.levelId =new SimpleStringProperty(levelId);
		this.userName = new SimpleStringProperty(userName);
		this.steps = new SimpleIntegerProperty(steps);
		this.time = new SimpleStringProperty(time);
	}

	public Integer getRecordId() {
		return recordId.get();
	}


	public String getLevelId() {
		return levelId.get();
	}


	public String getUserName() {
		return userName.get();
	}


	public Integer getSteps() {
		return steps.get();
	}


	public String getTime() {
		return time.get();
	}

	@Override
	public String toString() {
		return "RecordRow [recordId=" + recordId + ", levelId=" + levelId + ", userName=" + userName + ", steps="
				+ steps + ", time=" + time + "]";
	}
	
	
	
	
}
