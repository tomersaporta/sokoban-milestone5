package model.data.handle;

import java.util.HashMap;

/**
 *<h1>Level Saver Factory</h1> 
 *create levelSaver according to the string type
 */

public class LevelSaverFactory {
	
	private interface Creator{
		public LevelSaver create();
	}
	
	private class MyTextCreator implements Creator{
		@Override
		public LevelSaver create() {
			return new MyTextLevelSaver();
		}
	}
	
	private class MyXMLCreator implements Creator{
		@Override
		public LevelSaver create() {
			return new MyXMLLevelSaver();
		}
	}
	
	private class MyObjCreator implements Creator{
		@Override
		public LevelSaver create() {
			return new MyObjectLevelSaver();
		}
	}
	
	HashMap<String,Creator> levelSaverCreator;
	
	public LevelSaverFactory() {
		this.levelSaverCreator= new HashMap<String, Creator>();
		this.levelSaverCreator.put("txt", new MyTextCreator());
		this.levelSaverCreator.put("xml", new MyXMLCreator());
		this.levelSaverCreator.put("obj", new MyObjCreator());
	}
	
	/**
	 * create LevelSaver by the type string
	 * @param type This is the key in the HashMap who map between the string(key)and the suitable creator(value)
	 * @return new LevelSaver
	 */
	public LevelSaver create(String type){
		Creator c=levelSaverCreator.get(type);
		
		if (c!=null)return c.create();
		return null;
	}
}
