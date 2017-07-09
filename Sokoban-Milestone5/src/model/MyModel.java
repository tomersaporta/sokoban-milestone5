package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

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
/**
 *<h1>MyModel</h1> 
 *Implements the methods IModel defines
 *The specific model to our Sokoban game 
 */
public class MyModel extends Observable implements IModel {
	
	Level theLevel;
	LevelLoaderFactory LevelLoaderFactory;
	LevelSaverFactory LevelSaverFactory;
	MoveTypeFactory MoveTypeFactory;
	String serverIp;
	int serverPort;
	private Gson json;
	private ModelClient modelClient;
	private List<String> hintSolution;
	private boolean isOnHintPath;
	
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
		this.hintSolution=new ArrayList<>();
		this.isOnHintPath=false;
	}
	
	public Level getTheLevel() {
		return theLevel;
	}

	/**
	 * 
	 * @param theLevel
	 */
	public void setTheLevel(Level theLevel) {
		this.theLevel = theLevel;
	}

	@Override
	/**
	 * Return the current Level
	 * @return - the current Level
	 */
	public Level getCurrentLevel() {
		return this.theLevel;
	}


	/**
	 * Load level from the filepath
	 * @param filepath- path to the location of the file we want to load
	 */
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

	
	/**
	 * save level to the filepath
	 * @param filepath- path to the location of the file we want to save
	 */
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

	
	/**
	 * Move the player according to the moveInput 
	 * The action fulfillment if the move is legal(according to the policy)
	 * @param moveImput- the type of the move- up/down/left/right
	 */
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
		
		if(policy.checkPolicy(this.theLevel.getListPlayer().get(0), moveType)){
			setChanged();
			notifyObservers("changed");
			if(!this.hintSolution.isEmpty()){
				if(!(moveInput.toUpperCase().equals(this.hintSolution.get(0))))
					this.isOnHintPath=false;
				else
					this.hintSolution.remove(0);
			}
			
		}
		
	}

	/**
	 * Returns the steps that the player did in the current level
	 */
	@Override
	public int getSteps() {
		if(this.theLevel!=null)
			return this.theLevel.getSteps();
		return 0;
	}

	/**
	 * Returns the list of the records- according to the current query
	 */
	@Override
	public List<Record> getRecordsList() {
		return this.recordes;
	}

	/**
	 * Ask the Sokoban server to  a query according to the params 
	 */
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

	
	/**
	 * Ask the Sokoban server to add a new user to the DB
	 */
	@Override
	public void addUser(String userName) {
		
		Commands command = Commands.ADD_USER;
		User user=new User(userName);
		
		String userJson=this.json.toJson(user);
		//send the request to the server
		this.modelClient.createServerConnection(command, userJson);
		
	}
	
	
	/**
	 * Ask the Sokoban server to add a new record to the DB
	 */
	@Override
	public void addRecord(String recordValues) {
		
		Commands command = Commands.ADD_RECORD;
		
		String [] s=recordValues.split(" ");
		Record record=new Record(this.theLevel.getLevelID(), s[0], this.theLevel.getSteps(), s[1]);
		
		String recordJson=this.json.toJson(record);
		
		this.modelClient.createServerConnection(command, recordJson);
	}

	/**
	 * Restart the current Level
	 */
	@Override
	public void restart() {
		CompressedLevel c=new CompressedLevel(this.theLevel.getLevelID(), this.theLevel.getInitBoard());
		setTheLevel(c.decompressLevel());
		setChanged();
		notifyObservers("changed");
	}

	/**
	 * Ask the Sokoban server for solution to the current Level
	 */
	@Override
	public void getSolution() {
		Commands command = Commands.GET_SOLUTION;
		CompressedLevel cl=new CompressedLevel(theLevel.getLevelID(), theLevel.getLevelBored());
		String levelJson=json.toJson(cl);
		String solJson=modelClient.createServerConnection(command, levelJson);
		String sol=this.json.fromJson(solJson, String.class);
		
		
		List<String> solution=decompressedSolution(sol);
		Timer timer=new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				if(!solution.isEmpty()){
					setChanged();
					notifyObservers(solution.get(0));
					solution.remove(0);
				}
			}
		}, 0,500);
	}
	
	private List<String> decompressedSolution(String sol){
		List<String> solution=new ArrayList<>();
		
		StringBuilder sb=new StringBuilder(sol);
		for(int i=0;i<sb.length();i++){
			switch(sb.charAt(i)){
			case 'r': solution.add("Move right");
					  break;
			case 'l': solution.add("Move left");
			  		  break;
			case 'u': solution.add("Move up");
	  		          break;
			case 'd': solution.add("Move down");
	  		   		  break;
			}
		}
		return solution;
	}
	
	/**
	 * Ask the Sokoban server for a hint to the current Level
	 */
	@Override
	public void getHint() {
		if(!this.isOnHintPath){
			Commands command = Commands.GET_HINT;
			CompressedLevel cl=new CompressedLevel(theLevel.getLevelID(), theLevel.getLevelBored());
			String levelJson=json.toJson(cl);
			
			String solJson=modelClient.createServerConnection(command, levelJson);
			String sol=this.json.fromJson(solJson, String.class);
			
			System.out.println("THE SOL: "+sol);

			this.hintSolution=decompressedSolution(sol);
			
			if(!this.hintSolution.isEmpty()){
				setChanged();
				notifyObservers(this.hintSolution.get(0));
				this.hintSolution.remove(0);
				this.isOnHintPath=true;
			}
			
		}
		else{
			if(!this.hintSolution.isEmpty()){
				setChanged();
				notifyObservers(this.hintSolution.get(0));
				this.hintSolution.remove(0);
			}
			
		}
		
		
	}
}
