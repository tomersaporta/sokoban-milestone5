package model;

import java.util.List;

import db.Level;
import db.Record;

public interface IModel {
	
	public Level getCurrentLevel();
	public void LoadLevel(String filepath);
	public void SaveLevel(String filepath);
	public void move(String moveInput);
	public int getSteps();
	public List<Record> getRecordsList();
	public void dbQuery(String params);
	public void addUser(String userName);
	public void addRecord(String recordValues);
	
	public void restart();
	public void getHint();
	public void getSolution();
	
}
