package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Observable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import commands.Commands;
import db.CompressedLevel;
import db.Level;
import db.QueryParams;
import db.Record;
import db.User;
import model.data.handle.LevelLoader;
import model.data.handle.LevelLoaderFactory;
import model.data.handle.LevelSaver;
import model.data.handle.LevelSaverFactory;
import model.policy.ISokobanPolicy;
import model.policy.MySokobanPolicy;
import model.policy.moveType.IMoveType;
import model.policy.moveType.MoveTypeFactory;

public class MyModel extends Observable implements IModel {
	
	Level theLevel;
	LevelLoaderFactory LevelLoaderFactory;
	LevelSaverFactory LevelSaverFactory;
	MoveTypeFactory MoveTypeFactory;
	String serverIp;
	int serverPort;
	private Gson json;
	private ModelClient modelClient;
	
	//DB
	List<Record> recordes;
	
	public MyModel(String serverIp, int serverPort) {
		this.theLevel=null;
		this.LevelLoaderFactory = new LevelLoaderFactory();
		this.LevelSaverFactory=new LevelSaverFactory();
		this.MoveTypeFactory=new MoveTypeFactory();
		this.serverIp=serverIp;
		this.serverPort=serverPort;
		GsonBuilder jsonBuilder = new GsonBuilder();
		this.json = jsonBuilder.create();
		this.modelClient=new ModelClient(this.serverIp, this.serverPort);
		
	}
	
	public Level getTheLevel() {
		return theLevel;
	}

	public void setTheLevel(Level theLevel) {
		this.theLevel = theLevel;
	}

	@Override
	public Level getCurrentLevel() {
		return this.theLevel;
	}

	@Override
	public void LoadLevel(String filepath) {

		Thread t= new Thread(new Runnable() {	
			@Override
			public void run() {
				
				LevelLoader levelLoader= LevelLoaderFactory.create(filepath.substring(filepath.length()-3).toLowerCase());
				
				if (levelLoader==null){//extension not valid
					setChanged();
					notifyObservers("Error Invalid file!");
					return;
				}
					
				try {
					setTheLevel(levelLoader.loadLevel(new FileInputStream(new File(filepath))));
					setChanged();
					notifyObservers("changed");
					
					//add the level to the DB
					Commands command = Commands.ADD_LEVEL;
					
					CompressedLevel cl=new CompressedLevel(theLevel.getLevelID(), theLevel.getLevelBored());
					
					String levelJson=json.toJson(cl);
					
					modelClient.createServerConnection(command, levelJson);
					
				} catch (ClassNotFoundException | IOException e) {//file not found
					setChanged();
					notifyObservers("Error Invalid file!");
					return;
				}
			}
		});
		t.start();
		
		try {
			t.join();
		} catch (InterruptedException e) {
			setChanged();
			notifyObservers("Error "+e.getMessage());
		}
		
	}

	@Override
	public void SaveLevel(String filepath) {
		
		if(this.theLevel==null){
			setChanged();
			notifyObservers("Error You need to load level first!");
			return;
		}
		
		Thread t =new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				LevelSaver levelSaver=LevelSaverFactory.create(filepath.substring(filepath.length()-3).toLowerCase());
				if(levelSaver==null){//extension not valid
					setChanged();
					notifyObservers("Error Invalid file!");
					return;
				}
				try {
					levelSaver.saveLevel(getTheLevel(), new FileOutputStream(new File(filepath)));
				} catch (IOException e) {
					setChanged();
					notifyObservers("Error Invalid file!");
					return;
				}				
			}
		});
		t.start();
	}

	@Override
	public void move(String moveInput) {
		
		if(this.theLevel==null){
			setChanged();
			notifyObservers("Error You need to load level first!");
			return;
		}

		ISokobanPolicy policy=new MySokobanPolicy(this.theLevel);
		IMoveType moveType=this.MoveTypeFactory.create(moveInput.toUpperCase());
		
		//moveType didn't created
		if (moveType==null){
			setChanged();
			notifyObservers("Error Invalid move type!");
			return;
		}
		
		policy.checkPolicy(this.theLevel.getListPlayer().get(0), moveType);
		setChanged();
		notifyObservers("changed");
	}

	@Override
	public int getSteps() {
		if(this.theLevel!=null)
			return this.theLevel.getSteps();
		return 0;
	}

	@Override
	public List<Record> getRecordsList() {
		return this.recordes;
	}

	@Override
	public void dbQuery(String params) {
		//creating the QueryParams from the params
		String[] parametrs=params.split(" ");
		QueryParams queryParams=new QueryParams(parametrs[0], parametrs[1], parametrs[2]);
		
		Commands command = Commands.DB_QUERY;
		String queryJson=this.json.toJson(queryParams);
		
		String result=this.modelClient.createServerConnection(command, queryJson);

		Type classType=new TypeToken<List<Record>>(){}.getType();
		//send the request to the server
		this.recordes=this.json.fromJson(result, classType);
		setChanged();
		notifyObservers("showQueryResults");
	}

	@Override
	public void addUser(String userName) {
		
		Commands command = Commands.ADD_USER;
		User user=new User(userName);
		
		String userJson=this.json.toJson(user);
		//send the request to the server
		this.modelClient.createServerConnection(command, userJson);
		
	}

	@Override
	public void addRecord(String recordValues) {
		
		Commands command = Commands.ADD_RECORD;
		
		String [] s=recordValues.split(" ");
		Record record=new Record(this.theLevel.getLevelID(), s[0], this.theLevel.getSteps(), s[1]);
		
		String recordJson=this.json.toJson(record);
		
		this.modelClient.createServerConnection(command, recordJson);
	}
}
