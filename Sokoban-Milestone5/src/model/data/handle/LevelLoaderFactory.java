package model.data.handle;

import java.util.HashMap;
/**
 *<h1>Level Loader Factory</h1> 
 *create levelLoader according to the string type
 */


public class LevelLoaderFactory {
	private interface Creator{
		public LevelLoader create();
	}
	
	private class MyTextCreator implements Creator{
		@Override
		public LevelLoader create() {
			return new MyTextLevelLoader();
		}
	}
	
	private class MyXMLCreator implements Creator{
		@Override
		public LevelLoader create() {
			return new MyXMLLevelLoader();
		}
	}
	
	private class MyObjCreator implements Creator{
		@Override
		public LevelLoader create() {
			return new MyObjectLevelLoader();
		}
	}
	
	HashMap<String,Creator> levelLoaderCreator;
	
	public LevelLoaderFactory() {
		this.levelLoaderCreator= new HashMap<String, Creator>();
		this.levelLoaderCreator.put("txt", new MyTextCreator());
		this.levelLoaderCreator.put("xml", new MyXMLCreator());
		this.levelLoaderCreator.put("obj", new MyObjCreator());
	}
	
	/**
	 * create LevelLoader by the type string
	 * @param type This is the key in the HashMap who map between the string(key)and the suitable creator(value)
	 * @return new LevelLoader
	 */
	public LevelLoader create(String type){
		Creator c=levelLoaderCreator.get(type);
		
		if (c!=null)return c.create();
		return null;
	}
}
